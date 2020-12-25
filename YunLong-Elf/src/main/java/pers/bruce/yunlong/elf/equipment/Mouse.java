package pers.bruce.yunlong.elf.equipment;

import java.awt.*;

/**
 * <p>
 * 鼠标
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/25
 */
public interface Mouse {

    /**
     * <p>
     * 光标移动
     * </p>
     * @author BruceLee
     * @date 2020/12/25 16:30
     * @param x 横轴
     * @param y 纵轴
     * @throws AWTException 异常
     */
    void move(Integer x, Integer y) throws AWTException;

    /**
     * <p>
     * 左键点击
     * </p>
     * @author BruceLee
     * @date 2020/12/25 16:56
     * @param count 次数
     * @param delay 间隔
     */
    void clickLeft(Integer count, Integer delay) throws AWTException;

    /**
     * <p>
     * 右键点击
     * </p>
     * @author BruceLee
     * @date 2020/12/25 16:56
     * @param count 次数
     * @param delay 间隔
     */
    void clickRight(Integer count, Integer delay) throws AWTException;

}
