package pers.bruce.yunlong.elf.equipment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.bruce.yunlong.elf.equipment.Mouse;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * <p>
 * 鼠标
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/25
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MouseImpl implements Mouse {

    private final Robot robot;


    /**
     * <p>
     * 光标移动
     * </p>
     *
     * @param x 横轴
     * @param y 纵轴
     * @author BruceLee
     * @date 2020/12/25 16:30
     */
    @Override
    public void move(Integer x, Integer y) {

        int i = 10;
        while(i-- > 0) {
            robot.mouseMove(x, y);
        }
    }

    /**
     * <p>
     * 左键点击
     * </p>
     *
     * @param count 次数
     * @param delay 间隔
     * @author BruceLee
     * @date 2020/12/25 16:56
     */
    @Override
    public void clickLeft(Integer count, Integer delay) {

        count = count != null ? count : 1;
        delay = delay != null ? delay : 0;

        while (count-- > 0) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(100);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(delay);
        }
    }

    /**
     * <p>
     * 右键点击
     * </p>
     *
     * @param count 次数
     * @param delay 间隔
     * @author BruceLee
     * @date 2020/12/25 16:56
     */
    @Override
    public void clickRight(Integer count, Integer delay) {

        count = count != null ? count : 1;
        delay = delay != null ? delay : 0;

        while (count-- > 0) {
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.delay(100);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            robot.delay(delay);
        }
    }
}
