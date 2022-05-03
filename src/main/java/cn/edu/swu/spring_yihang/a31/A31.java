package cn.edu.swu.spring_yihang.a31;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zeyu
 * @date 2022/05/03
 **/
public class A31 {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

//        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
//        resolver.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
//        resolver.afterPropertiesSet();


        ExceptionHandlerExceptionResolver resolver = context.getBean(ExceptionHandlerExceptionResolver.class);
        resolver.afterPropertiesSet();

        HandlerMethod handlerMethod = new HandlerMethod(new Controller5(), Controller5.class.getDeclaredMethod("foo"));
        Exception e = new Exception("e1");

        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));

    }

    static class Controller5{
        public void foo(){}

    }
}
