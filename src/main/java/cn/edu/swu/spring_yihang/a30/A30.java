package cn.edu.swu.spring_yihang.a30;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @author zeyu
 * @date 2022/05/03
 **/
public class A30 {

    public static void main(String[] args) throws NoSuchMethodException {

        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();

        resolver.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
        resolver.afterPropertiesSet();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

//        Method method = Controller1.class.getDeclaredMethod("foo");
//        HandlerMethod handlerMethod = new HandlerMethod(new Controller1(), method);
//
//        Exception e = new ArithmeticException("被0除");
//        resolver.resolveException(request, response, handlerMethod, e);
//
//        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));

        HandlerMethod handlerMethod = new HandlerMethod(new Controller3(), Controller3.class.getDeclaredMethod("foo"));

        Exception e = new Exception("a", new RuntimeException("b", new IOException("e3")));

        resolver.resolveException(request, response, handlerMethod, e);

        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
        //异常处理的参数解析


    }
    static class Controller1 {
        public void foo() {

        }
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(ArithmeticException e) {
            return Map.of("error", e.getMessage());
        }
    }

    static class Controller2 {
        public void foo() {

        }
        @ExceptionHandler
        public ModelAndView handle(ArithmeticException e) {
            return new ModelAndView("test2", Map.of("error", e.getMessage()));
        }
    }

    static class Controller3 {
        public void foo() {

        }
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(IOException e3) {
            return Map.of("error", e3.getMessage());
        }
    }

    static class Controller4 {
        public void foo() {}
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handler(Exception e, HttpServletRequest request) {
            System.out.println(request);
            return Map.of("error", e.getMessage());
        }
    }

}
