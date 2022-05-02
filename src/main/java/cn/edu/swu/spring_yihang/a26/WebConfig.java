package cn.edu.swu.spring_yihang.a26;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class WebConfig {

    @ControllerAdvice
    static class MyControllerAdvice {
        @ModelAttribute("a")
        //解析器是RequestMappingHandlerMapping
        //
        public String aa() {
            return "aa";
        }

    }

    @Controller
    static class Controller1 {
        @ModelAttribute("b")
        //解析器是RequestMappingHandlerMapping
        //
        public String bb() {
            return "bb";
        }

        @ResponseStatus(HttpStatus.OK)
        //首先创造空的对象，将请求参数绑定。如果没有指定“u”,那么以对象小写user存入container
        public ModelAndView foo(@ModelAttribute("u") User user) {
            System.out.println("foo");
            return null;
        }
    }

    static class User {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {

            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
