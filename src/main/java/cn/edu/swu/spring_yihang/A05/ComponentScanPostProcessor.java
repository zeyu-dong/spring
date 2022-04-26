package cn.edu.swu.spring_yihang.A05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {
    //context refresh时候回回调这个方法
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);


            if (componentScan != null) {
                for (String s : componentScan.basePackages()) {
                    //cn.edu.swu.spring_yihang.A05.component-->classpath*:cn/edu/swu/spring_yihang/A05/component/**/*.class
                    String path = "classpath*:" + s.replace('.', '/') + "/**/*.class";

                    CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();


                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                    for (Resource resource : resources) {
                        //                    System.out.println(resource);
                        MetadataReader reader = factory.getMetadataReader(resource);
                        //                    System.out.println("类名" + reader.getClassMetadata().getClassName());
                        //
                        //                    System.out.println("是否加了Component：" + reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
                        //                    System.out.println("是否派生了Compoent:"+reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));

                        AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                        if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())
                                || reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {
                            AbstractBeanDefinition definition = BeanDefinitionBuilder
                                    .genericBeanDefinition(reader.getClassMetadata().getClassName())
                                    .getBeanDefinition();


                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory beanFactory) {


                                String beanName = generator.generateBeanName(definition, beanFactory);
                                //beandefinition假如Bean工厂里面
                                beanFactory.registerBeanDefinition(beanName, definition);
                            }

                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
