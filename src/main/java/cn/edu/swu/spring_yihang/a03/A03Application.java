package cn.edu.swu.spring_yihang.a03;

import cn.edu.swu.spring_yihang.a01.Component1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zeyu
 * @date 2022/04/25
 **/

@SpringBootApplication
public class A03Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A03Application.class, args);
        context.close();
    }
}
