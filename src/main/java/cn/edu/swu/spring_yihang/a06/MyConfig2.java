package cn.edu.swu.spring_yihang.a06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author zeyu
 * @date 2022/04/27
 **/

//Aware接口  InitializingBean接口
public class MyConfig2 implements InitializingBean, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(MyConfig2.class);

    @Override
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

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("初始化");
    }
}
