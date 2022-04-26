package cn.edu.swu.spring_yihang.A05;


import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class A05Application {
    public static void main(String[] args) throws IOException {

        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class);
//
//        context.registerBean(MapperScannerConfigurer.class,bd -> {
//            bd.getPropertyValues().add("basePackage", "cn.edu.swu.spring_yihang.A05.mapper");
//        });

        //初始化容器

        context.registerBean(ComponentScanPostProcessor.class);

        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        context.close();
    }
}
