package cn.edu.swu.spring_yihang.A04;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class DigInAutowired {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        factory.registerSingleton("bean2", new Bean2());
        factory.registerSingleton("bean3", new Bean3());
        factory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        //${}的解析器
//        factory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        //1.查找哪些属性，方法加了@Autowired,称之为InjectionMetadata
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(factory);

//        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);
//        processor.postProcessProperties(null, bean1, "bean1");
//        System.out.println(bean1);

//        Method method = AutowiredAnnotationBeanPostProcessor.class.
//                getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
//        method.setAccessible(true);
//
//        //获取Bean1上加了@Value @Autowired的成员变量包括方法参数的信息
//        InjectionMetadata metadata = (InjectionMetadata) method.
//                invoke(processor, "bean1", Bean1.class, null);
//
//        metadata.inject(bean1, "bean1", null);
//        System.out.println(bean1);

//        如何按类型查找类
//        Field bean3 = Bean1.class.getDeclaredField("bean3");
//        DependencyDescriptor descriptor = new DependencyDescriptor(bean3, false);
//        Object o = factory.doResolveDependency(descriptor, null, null, null);
//
//        System.out.println(o);
//        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
//
//        DependencyDescriptor descriptor1 =
//                new DependencyDescriptor(new MethodParameter(setBean2, 0), false);
//
//        Object o1 = factory.doResolveDependency(descriptor1, null, null, null);
//
//        System.out.println(o1);


        Method setHome = Bean1.class.getDeclaredMethod("setHome", String.class);
        DependencyDescriptor descriptor2 = new DependencyDescriptor(new MethodParameter(setHome, 0), true);
        Object o = factory.doResolveDependency(descriptor2, null, null, null);
        System.out.println(o);


    }
}
