package cn.edu.swu.spring_yihang.A04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class DigInAutowired {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        factory.registerSingleton("bean2", new Bean2());
        factory.registerSingleton("bean3", new Bean3());
        factory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());

        //1.查找哪些属性，方法加了@Autowired,称之为InjectionMetadata
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(factory);

//hhhh 我是卖报的小行家        



    }
}
