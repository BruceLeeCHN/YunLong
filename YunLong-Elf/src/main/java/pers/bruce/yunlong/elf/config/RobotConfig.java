package pers.bruce.yunlong.elf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * <p>
 * Robot配置类
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/25
 */
@Configuration
public class RobotConfig {

    @Bean
    Robot robot() throws AWTException {
        return new Robot();
    }

}
