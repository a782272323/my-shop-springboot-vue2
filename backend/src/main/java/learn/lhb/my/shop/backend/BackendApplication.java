package learn.lhb.my.shop.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 00:23
 */
@SpringBootApplication
@MapperScan(basePackages = "learn.lhb.my.shop.backend.mapper")
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
