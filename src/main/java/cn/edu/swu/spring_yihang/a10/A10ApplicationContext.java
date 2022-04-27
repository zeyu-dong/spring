package cn.edu.swu.spring_yihang.a10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
@SpringBootApplication
public class A10ApplicationContext {

    private static final Logger log = LoggerFactory.getLogger(A10ApplicationContext.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A10ApplicationContext.class, args);
//
//            Myservice service = context.getBean(Myservice.class);
//        log.debug("service class{}", service.getClass());

//        service.foo();
//        context.close();
//        new Myservice().foo();

    }
}
