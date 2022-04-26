package cn.edu.swu.spring_yihang.a02;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author zeyu
 * @date 2022/04/24
 **/
public class A02Application {
    public static void main(String[] args) {

//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        String[] names = beanFactory.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
//        System.out.println(">>>>>>>>>>>>>>>>>.");
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        int i = reader.loadBeanDefinitions(new ClassPathResource("b01.xml"));
//        String[] names1 = beanFactory.getBeanDefinitionNames();
//        for (String name : names1) {
//            System.out.println(name);
//        }

//        testFilePathContext();
//        testClassPathXmlContext();
//        testAnnotationConfigApplicationContext();

        testAnnotationConfigServletWebServerApplicationContext();

    }


//基于xml文件
    private static void testFilePathContext(){
        FileSystemXmlApplicationContext context = new
                FileSystemXmlApplicationContext("E:\\study\\spring_yihang\\src\\main\\resources\\b01.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        System.out.println(context.getBean(Bean1.class).getBean2());
    }
//xml
    private static void testClassPathXmlContext(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("b01.xml");

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(context.getBean(Bean1.class).getBean2());


    }

//基于java配置类
    private static void testAnnotationConfigApplicationContext(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
//  基于java配置实现，用于web服务器
    private static void testAnnotationConfigServletWebServerApplicationContext(){
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);


    }




    @Configuration
    static class WebConfig{
        @Bean
        public ServletWebServerFactory servletWebServerFactory(){
            return new TomcatServletWebServerFactory();
        }

        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

        @Bean
        public DispatcherServletRegistrationBean registrationBean(DispatcherServlet servlet){
            return new DispatcherServletRegistrationBean(servlet, "/");
        }

        @Bean("/hello")
        public Controller controller1() {
            return (request, response) -> {
                response.getWriter().print("hello");
                return null;

            };
        }
    }


    @Configuration
    static class Config{

        @Bean
        public Bean1 bean1(Bean2 bean2) {
            Bean1 bean1 = new Bean1();
            bean1.setBean2(bean2);
            return bean1;
        }

         @Bean
         public Bean2 bean2() {
             return new Bean2();
        }
    }

    static class Bean1{
        private Bean2 bean2;
        public Bean2 getBean2(){
            return bean2;
        }

        public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }
    }
    static class Bean2{
    }
}
