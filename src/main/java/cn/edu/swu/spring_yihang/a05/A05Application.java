package cn.edu.swu.spring_yihang.a05;


import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class A05Application {
    public static void main(String[] args) throws IOException {

        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config",Config.class);
//        context.registerBean(ConfigurationClassPostProcessor.class);
//
//        context.registerBean(MapperScannerConfigurer.class,bd -> {
//            bd.getPropertyValues().add("basePackage", "cn.edu.swu.spring_yihang.A05.mapper");
//        });
//        context.registerBean(ComponentScanPostProcessor.class);


        context.registerBean(AtBeanPostProcessor.class);
        context.registerBean(MapperPostProcessor.class);
//初始化容器
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        context.close();
    }
}
