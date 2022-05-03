package cn.edu.swu.spring_yihang.a32;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfig {
    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean // @RequestMapping
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean // 注意默认的 RequestMappingHandlerAdapter 不会带 jackson 转换器
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
        return handlerAdapter;
    }
    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return webServerFactory -> webServerFactory.addErrorPages(new ErrorPage("/error"));
    }

    @Bean
    public ErrorPageRegistrarBeanPostProcessor errorPageRegistrarBeanPostProcessor(){
        return new ErrorPageRegistrarBeanPostProcessor();
    }


    @Controller
    public static class MyController {
        @RequestMapping("test")
        public ModelAndView test() {
            int i = 1 / 0;
            return null;
        }

//        @RequestMapping("/error")
//        @ResponseBody
//        public Map<String, Object> error(HttpServletRequest request) {
//            Throwable ex = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//            return Map.of("error", ex.getMessage());
//        }

    }


    @Bean
    public BasicErrorController basicErrorController(){

        ErrorProperties properties = new ErrorProperties();
        properties.setIncludeException(true);

        return new BasicErrorController(new DefaultErrorAttributes(), properties);
    }

    @Bean
    public View error(){
        return new View() {
            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                System.out.println(model);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("""
                        <h3>服务器内部错误</h3>                   
                        """);
            }
        };
    }

    @Bean
    public ViewResolver viewResolver(){
        return new BeanNameViewResolver();
    }


//    @Bean
//    public View error() {
//        return new View() {
//            @Override
//            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//                System.out.println(model);
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().print("""
//                        <h3>服务器内部错误</h3>
//                        """);
//            }
//        };
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        return new BeanNameViewResolver();
//    }
}
