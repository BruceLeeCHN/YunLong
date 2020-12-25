package pers.bruce.yunlong.elf;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p>
 * 订单系统启动类
 * </p>
 *
 * @author BruceLee
 * @since 2020/6/2
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        log.info("YunLong-Elf End Of Run");
    }


}
