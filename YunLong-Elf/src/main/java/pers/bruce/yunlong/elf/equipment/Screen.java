package pers.bruce.yunlong.elf.equipment;

import pers.bruce.yunlong.elf.model.dto.ImagePosition;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 * 显示器
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/25
 */
public interface Screen {

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
    BufferedImage screenshot(Integer startX, Integer startY, Integer endX, Integer endY);

    /**
     * <p>
     * 截屏
     * 全屏截取
     * </p>
     * @author BruceLee
     * @date 2020/12/28 9:21
     * @return 图片对象
     */
    BufferedImage screenshot();

    /**
     * <p>
     * 区域找图
     * </p>
     * @author BruceLee
     * @date 2020/12/28 15:37
     * @param startX 起点X坐标
     * @param startY 起点Y坐标
     * @param endX 终点X坐标
     * @param endY 终点Y坐标
     * @param sample 样本图片
     * @param accuracy 精确度
     * @return 图片坐标
     */
    ImagePosition imageFind(Integer startX, Integer startY, Integer endX, Integer endY, BufferedImage sample, Integer accuracy);


    /**
     * <p>
     * 区域找图
     * </p>
     * @author BruceLee
     * @date 2020/12/28 15:37
     * @param startX 起点X坐标
     * @param startY 起点Y坐标
     * @param endX 终点X坐标
     * @param endY 终点Y坐标
     * @param samplePath 样本图片地址
     * @param accuracy 精确度
     * @return 图片坐标
     */
    ImagePosition imageFind(Integer startX, Integer startY, Integer endX, Integer endY, String samplePath, Integer accuracy);

    /**
     * <p>
     * 区域找图-全屏查找
     * </p>
     * @author BruceLee
     * @date 2020/12/28 15:37
     * @param samplePath 样本图片地址
     * @param accuracy 精确度
     * @return 图片坐标
     */
    ImagePosition imageFind(String samplePath, Integer accuracy);
}
