package pers.bruce.yunlong.elf.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pers.bruce.yunlong.elf.equipment.Mouse;

/**
 * <p>
 * 测试Demo
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/25
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestDemo implements ApplicationRunner {

    private final Mouse mouse;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 鼠标移动
        mouse.move(200,300);
        // 鼠标右击
        mouse.clickRight(1, 0);

    }
}
