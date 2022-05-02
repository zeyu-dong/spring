package cn.edu.swu.spring_yihang.a24;

import cn.edu.swu.spring_yihang.a23.MyDateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author zeyu
 * @date 2022/05/02
 **/
public class WebConfig {
    @ControllerAdvice
    static class MyControllerAdvice{
        @InitBinder
        public void binder3(WebDataBinder webDataBinder) {
            webDataBinder.addCustomFormatter(new MyDateFormatter(""));
        }
    }

    @Controller
    static class Controller1{
        @InitBinder
        public void binder1(WebDataBinder webDataBinder) {
            webDataBinder.addCustomFormatter(new MyDateFormatter(""));
        }
        public void foo(){}
    }
    @Controller
    static class Controller2{
        @InitBinder
        public void binder2(WebDataBinder webDataBinder) {
            webDataBinder.addCustomFormatter(new MyDateFormatter(""));
        }
        public void foo(){}
    }
}
