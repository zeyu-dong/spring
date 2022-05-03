package cn.edu.swu.spring_yihang.a33;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zeyu
 * @date 2022/05/03
 **/
@Configuration
public class WebConfig {
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        return new TomcatServletWebServerFactory(8080);
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Component
    static class MyHandlerMapping  implements HandlerMapping{

        @Override
        public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
            String requestURI = request.getRequestURI();
            Controller controller = collect.get(requestURI);
            if (controller == null) {
                return null;
            }

            return new HandlerExecutionChain(controller);
        }

        //会进行初始化吧
        @Autowired
        private ApplicationContext context;
        private Map<String, Controller> collect;
        @PostConstruct
        public void init(){
            collect = context.getBeansOfType(Controller.class)
                    .entrySet()
                    .stream()
                    .filter(e -> e.getKey().startsWith("/"))
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
            System.out.println(collect);

        }
    }

    @Component
    static class MyHandlerAdapter implements HandlerAdapter{
        @Override
        public boolean supports(Object handler) {
            return handler instanceof Controller;
        }

        //适配器就是完成控制器方法的调用
        @Override
        public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           if (handler instanceof Controller controller) {
                controller.handleRequest(request, response);
            }
            return null;
        }


        @Override
        public long getLastModified(HttpServletRequest request, Object handler) {
            return -1;
        }
    }


    @Bean
    public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
        return new BeanNameUrlHandlerMapping();
    }

    @Bean
    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {
        return new SimpleControllerHandlerAdapter();
    }

    @Component("/c1")
    public static class Controller1 implements Controller {

        @Override
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.getWriter().print("this is c1");
            return null;
        }
    }

    @Component("c2")
    public static class Controller2 implements Controller {

        @Override
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.getWriter().print("this is c2");
            return null;
        }
    }

    @Component("/c3")
    public static class Controller3 implements Controller{

        @Override
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.getWriter().print("this is c3");
            return null;
        }
    }



}