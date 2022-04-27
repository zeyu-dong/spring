package cn.edu.swu.spring_yihang.A05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class AtBeanPostProcessor implements BeanFactoryPostProcessor  {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader reader = null;
        try {
            reader = factory.getMetadataReader(new ClassPathResource("cn/edu/swu/spring_yihang/A05/Config.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<MethodMetadata> methods =
                reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata method : methods) {
            System.out.println(method);
            String initMethod = method.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            //对于自动模式的装配，或者工厂模式的是这个AUTOWIRE_CONSTRUCTOR
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            builder.setFactoryMethodOnBean(method.getMethodName(), "config");
            if (initMethod.length() > 0) {
                builder.setInitMethodName(initMethod);
                System.out.println(initMethod);
            }
            AbstractBeanDefinition bd = builder.getBeanDefinition();

            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory beanFactory) {
                beanFactory.registerBeanDefinition(method.getMethodName(), bd);
            }
        }
    }
}
