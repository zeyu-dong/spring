package cn.edu.swu.spring_yihang.a31;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;
import java.util.Map;

/**
 * @author zeyu
 * @date 2022/05/03
 **/
@Configuration
public class WebConfig {
    @ControllerAdvice
    public static class MyControllerAdvice {
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handler(Exception e) {
            return Map.of("error", e.getMessage());
        }

        @Bean
        public ExceptionHandlerExceptionResolver get() {
            ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
            resolver.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
            //不需要自己了，因为它实现了InitializingBean接口，容器会对其内置的初始化方法，context会回调
//            resolver.afterPropertiesSet();

            return resolver;
        }

    }
}
