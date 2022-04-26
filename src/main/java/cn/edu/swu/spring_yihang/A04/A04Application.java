package cn.edu.swu.spring_yihang.A04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class A04Application {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(Bean1.class);
        context.registerBean(Bean2.class);
        context.registerBean(Bean3.class);


        //然后需要对@Value进行解析
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        //@AutoWired被解析
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);


        //@Resource @PostConstruct @PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);


        context.registerBean(Bean4.class);
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());//@ConfigurationProperties
        //初始化容器
        context.refresh();

        System.out.println(context.getBean(Bean4.class));


        context.close();
    }

}
