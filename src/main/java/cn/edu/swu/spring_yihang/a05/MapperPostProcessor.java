package cn.edu.swu.spring_yihang.a05;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
        //获取资源
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("cn\\edu\\swu\\spring_yihang\\A05\\mapper/**/*.class");

            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();

            //读取类的元数据
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

            for (Resource resource : resources) {
                MetadataReader reader = factory.getMetadataReader(resource);
                ClassMetadata classMetadata = reader.getClassMetadata();
                if (classMetadata.isInterface()) {
                    //知道哪些类是接口之后，进行生成

                    AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                            //获取接口的名字比如Mapper1 Mapper2
                            .addConstructorArgValue(classMetadata.getClassName())
                            //sqlSessionFactory按照TYPE自动装配，需要是否有SqlSessionFactoryBean
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                            .getBeanDefinition();
                    //就是为了上面那个bd不能区分名字，因为都是一个名字，下面就根据元数据进行定义
                    AbstractBeanDefinition bd2 = BeanDefinitionBuilder.genericBeanDefinition(classMetadata.getClassName()).getBeanDefinition();

                    //现在就可以注册了
                    String beanName = generator.generateBeanName(bd2, beanFactory);
                    beanFactory.registerBeanDefinition(beanName, bd);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
