package pers.bruce.yunlong.elf.equipment.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.bruce.yunlong.elf.equipment.Screen;
import pers.bruce.yunlong.elf.model.dto.ImagePosition;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 显示器
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/25
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScreenImpl implements Screen {

    private final Robot robot;


    /**
     * <p>
     * 截屏
     * 区域截取
     * </p>
     * @author BruceLee
     * @date 2020/12/28 9:21
     * @param startX 起点X坐标
     * @param startY 起点Y坐标
     * @param endX 终点X坐标
     * @param endY 终点Y坐标
     * @return 图片对象
     */
    @Override
    public BufferedImage screenshot(Integer startX, Integer startY, Integer endX, Integer endY) {
        return robot.createScreenCapture(new Rectangle(startX, startY, startX + endX, startY + endY));
    }


    /**
     * <p>
     * 截屏
     * 全屏截取
     * </p>
     *
     * @return 图片对象
     * @author BruceLee
     * @date 2020/12/28 9:21
     */
    @Override
    public BufferedImage screenshot() {
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        return this.screenshot(0, 0, width, height);
    }

    /**
     * <p>
     * 区域找图
     * </p>
     * @param startX   起点X坐标
     * @param startY   起点Y坐标
     * @param endX     终点X坐标
     * @param endY     终点Y坐标
     * @param sample   样本图片
     * @param accuracy 精确度
     * @return 图片坐标
     * @author BruceLee
     * @date 2020/12/28 15:37
     */
    @Override
    public ImagePosition imageFind(Integer startX, Integer startY, Integer endX, Integer endY, BufferedImage sample, Integer accuracy) {
        accuracy = accuracy != null ? accuracy : 10;
        BufferedImage target = screenshot(startX, startY, endX, endY);
        return imageFind(target, sample, accuracy);
    }


    /**
     * <p>
     * 区域找图
     * </p>
     *
     * @param startX     起点X坐标
     * @param startY     起点Y坐标
     * @param endX       终点X坐标
     * @param endY       终点Y坐标
     * @param samplePath 样本图片地址
     * @param accuracy   精确度
     * @return 图片坐标
     * @author BruceLee
     * @date 2020/12/28 15:37
     */
    @Override
    public ImagePosition imageFind(Integer startX, Integer startY, Integer endX, Integer endY, String samplePath, Integer accuracy) {
        try {
            BufferedImage sample = ImageIO.read(new File(samplePath));
            return imageFind(startX, startY, endX, endY, sample, accuracy);
        } catch (IOException e) {
            e.printStackTrace();
            return ImagePosition.notFind();
        }
    }

    /**
     * <p>
     * 区域找图-全屏查找
     * </p>
     *
     * @param samplePath 样本图片地址
     * @param accuracy   精确度
     * @return 图片坐标
     * @author BruceLee
     * @date 2020/12/28 15:37
     */
    @Override
    public ImagePosition imageFind(String samplePath, Integer accuracy) {
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        return imageFind(0, 0, width, height, samplePath, accuracy);
    }

    /**
     * <p>
     * 图片查找
     * 判断样本图片sample，是否存在于目标图片target中
     * </p>
     * @author BruceLee
     * @date 2020/12/28 10:55
     * @param target 目标图片
     * @param sample 样本图片
     * @return 结果起始坐标
     */
    private ImagePosition imageFind(BufferedImage target, BufferedImage sample, int accuracy) {

        // step1: 获取两张图片的像素数组
        int[][] targetRgbArr = getImageRgb(target);
        int[][] sampleRgbArr = getImageRgb(sample);

        if (sampleRgbArr.length == 0 || targetRgbArr.length < sampleRgbArr.length || targetRgbArr[0].length < sampleRgbArr[0].length) {
            return null;
        }

        // 目标纵向长度
        int targetHeightSize = targetRgbArr.length;
        // 目标横向长度
        int targetWidthSize = targetRgbArr[0].length;
        // 样本纵向长度
        int sampleHeightSize = sampleRgbArr.length;
        // 样本横向长度
        int sampleWidthSize = sampleRgbArr[0].length;

        // step2: 获取样本图片的四角RGB值

        // 样本左上角RGB值
        int sampleUpperLeftRgb = sampleRgbArr[0][0];
        // 样本右上角RGB值
        int sampleUpperRightRgb = sampleRgbArr[0][sampleWidthSize - 1];
        // 样本左下角RGB值
        int sampleLowerLeftRgb = sampleRgbArr[sampleHeightSize - 1][0];
        // 样本右下角RGB值
        int sampleLowerRightRgb = sampleRgbArr[sampleHeightSize - 1][sampleRgbArr[sampleHeightSize - 1].length - 1];

        int currentX = 0;
        int currentY = 0;

        // 循环查找
        while (true) {
            // 比较四角RGB值 初步筛选
            if (compare(targetRgbArr[currentY][currentX], sampleUpperLeftRgb, accuracy)
                    && compare(targetRgbArr[currentY][currentX + sampleWidthSize], sampleUpperRightRgb, accuracy)
                    && compare(targetRgbArr[currentY + sampleHeightSize][currentX], sampleLowerLeftRgb, accuracy)
                    && compare(targetRgbArr[currentY + sampleHeightSize][currentX + sampleWidthSize], sampleLowerRightRgb, accuracy)) {

                // 初步匹配成功，进行精准匹配
                Boolean isMatchAll = matchAll(targetRgbArr, sampleRgbArr, accuracy, currentX, currentY);

                if (isMatchAll) {
                    // 精准匹配成功，返回坐标
                    return new ImagePosition(currentX, currentY);
                }
            }

            // 初步匹配失败 横向坐标偏移后未超出，横向坐标偏移
            if (++currentX + sampleWidthSize < targetWidthSize) {
                continue;
            }

            // 纵向坐标自增后下标未越界，纵向坐标偏移，横向坐标初始化
            if (++currentY + sampleHeightSize < targetHeightSize) {
                currentX = 0;
                continue;
            }
            System.out.println(currentY - 1);
            break;
        }

        log.info("未匹配到数据");
        return ImagePosition.notFind();
    }

    /**
     * <p>
     * 精准匹配区域内所有像素
     * </p>
     * @author BruceLee
     * @date 2020/12/28 15:14
     * @param targetRgbArr 目标像素组
     * @param sampleRgbArr 样本像素组
     * @param accuracy 精准度
     * @param initX 起始X
     * @param initY 起始Y
     * @return 匹配结果
     */
    private Boolean matchAll(int[][] targetRgbArr, int[][] sampleRgbArr, int accuracy, int initX, int initY) {

        for (int height = 0; height < sampleRgbArr.length; height++) {

            // 目标Y当前坐标
            int targetCurrentY = height + initY;
            // 目标X当前坐标
            int targetCurrentX = initX;

            for (int width = 0; width < sampleRgbArr[height].length; width++) {
                // 匹配失败 结束循环
                if (!compare(targetRgbArr[targetCurrentY][targetCurrentX++], sampleRgbArr[height][width], accuracy)) {
                    return false;
                }
            }
        }

        // 匹配成功
        return true;
    }

    /**
     * <p>
     * 获取图片的RGB像素点
     * </p>
     * @author BruceLee
     * @date 2020/12/28 10:39
     * @param bfImage 图片数据
     * @return RGB像素二位数组
     */
    private int[][] getImageRgb(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                // 如果为ARGB 转换 RGB，取后24位
                result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
        }
        return result;
    }

    /**
     * <p>
     * 颜色对比
     * </p>
     * @author BruceLee
     * @date 2020/12/28 14:50
     * @param target 参数1
     * @param sample 参数2
     * @param accuracy 精确度
     * @return 比较结果
     */
    private Boolean compare(int target, int sample, int accuracy) {
        return target == sample;
    }

}
