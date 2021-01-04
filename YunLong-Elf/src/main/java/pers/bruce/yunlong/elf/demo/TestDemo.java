package pers.bruce.yunlong.elf.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pers.bruce.yunlong.elf.equipment.Mouse;
import pers.bruce.yunlong.elf.equipment.Screen;
import pers.bruce.yunlong.elf.model.dto.ImagePosition;

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
    private final Screen Screen;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String imagePath = "C:\\Users\\Administrator.PC-202006021304\\Desktop\\temp\\5328F055-38AD-4ff2-ADA6-056853394312.png";

        // 图片查找
        ImagePosition imagePosition = Screen.imageFind(imagePath, 10);

        // 鼠标移动
        mouse.move(imagePosition.getX(),imagePosition.getY());

    }
}
