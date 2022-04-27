package cn.edu.swu.spring_yihang.A07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
@SpringBootApplication
public class A07Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A07Application.class, args);
        context.close();
    }

    @Bean(initMethod = "init3")
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean(initMethod = "destroy3")
    public Bean2 bean2(){
        return new Bean2();
    }
}
