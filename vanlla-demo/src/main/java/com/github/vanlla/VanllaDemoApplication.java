package com.github.vanlla;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 *
 * @author Vanlla
 * @since 1.0
 */
//@ComponentScan({"com.github.vanlla","other packages"})
@SpringBootApplication
public class VanllaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VanllaDemoApplication.class, args);
    }

}
