package cn.edu.swu.spring_yihang.a20;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeyu
 * @date 2022/04/30
 **/

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})
public class WebConfig {
    @Bean
    public TomcatServletWebServerFactory factory(ServerProperties serverProperties){
        return new TomcatServletWebServerFactory(serverProperties.getPort());
    }

    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean registrationBean(
            DispatcherServlet dispatcherServlet,
            WebMvcProperties webMvcProperties){
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        int loadOnStartup = webMvcProperties.getServlet().getLoadOnStartup();
        registrationBean.setLoadOnStartup(loadOnStartup);

        return registrationBean;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        return new RequestMappingHandlerMapping();
    }
    @Bean
    public MyRequestHandlerMappingHandlerAdpter requestMappingHandlerAdapter(){
        TokenArgumentResolver resolver = new TokenArgumentResolver();
        YmlReturnValueHandler ymlReturnValueHandler = new YmlReturnValueHandler();



        MyRequestHandlerMappingHandlerAdpter handlerAdpter = new MyRequestHandlerMappingHandlerAdpter();
        List<HandlerMethodArgumentResolver> list = new ArrayList<>();
        list.add(resolver);
        List<HandlerMethodReturnValueHandler> list1 = new ArrayList<>();
        list1.add(ymlReturnValueHandler);


        handlerAdpter.setCustomArgumentResolvers(list);
        handlerAdpter.setCustomReturnValueHandlers(list1);

        return handlerAdpter;
    }





}
