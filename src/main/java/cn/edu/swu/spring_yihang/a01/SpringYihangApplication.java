package cn.edu.swu.spring_yihang.a01;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;

/**
 * @author dongzeyu123
 */
//@SpringBootApplication
public class SpringYihangApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run( SpringYihangApplication.class, args );
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField( "singletonObjects" );
        field.setAccessible( true );

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
//        System.out.println(context.getMessage("hi", null, Locale.CHINA));
//        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
//        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));
//
//        Resource[] resources = context.getResources("classpath*:/META-INF/spring.factories");
//        for (Resource resource : resources) {
//            System.out.println(resource);
//        }
//
//        System.out.println(context.getEnvironment().getProperty("java_home"));
//        System.out.println(context.getEnvironment().getProperty("server.port"));

//        context.publishEvent(new UserRegisteredEvent(context));

        context.getBean(Component1.class).register();
        context.close();
    }

}
