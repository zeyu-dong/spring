package cn.edu.swu.spring_yihang.a02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author zeyu
 * @date 2022/04/24
 **/
public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

//        添加bean
        AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(Config.class)
                .setScope("singleton").getBeanDefinition();

        beanFactory.registerBeanDefinition("config", definition);

        //给BeanFactory添加后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        //bean.解析
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()
                .stream()
                .forEach(beanFactoryPostProcessor -> {
                    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
                });

        //bean后处理器

        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanPostProcessor -> {
            System.out.println(beanPostProcessor);
            beanFactory.addBeanPostProcessor(beanPostProcessor);

        });


//        System.out.println(beanFactory.getBean(Bean1.class).getBean2());
        System.out.println(">>>>>>>>>>>>>>>>>>>>");

        System.out.println(beanFactory.getBean(Bean1.class).getInter());
    }
   // @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }
    }

    interface Inter {

    }

    static class Bean3 implements Inter {

    }

    static class Bean4 implements Inter {

    }

    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("构造 Bean1()");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        @Autowired
        @Resource(name = "bean4")
        private Inter bean3;

        public Inter getInter() {
            return bean3;
        }
    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("构造 Bean2()");
        }
    }

}
