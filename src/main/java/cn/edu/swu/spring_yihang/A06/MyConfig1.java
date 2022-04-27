package cn.edu.swu.spring_yihang.A06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zeyu
 * @date 2022/04/27
 **/


@Configuration
public class MyConfig1 {
    private static final Logger log = LoggerFactory.getLogger(MyConfig1.class);

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        log.debug("注入applicationContext");
    }

    @PostConstruct
    public void init(){
        log.debug("初始化");
    }

    @Bean
    public BeanFactoryPostProcessor processor1(){
        return beanFactory -> {
            log.debug("执行postprocessor1");

        };
    }
}
