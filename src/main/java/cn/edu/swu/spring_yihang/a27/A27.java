package cn.edu.swu.spring_yihang.a27;

import net.minidev.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.*;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.util.UrlPathHelper;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/*
    目标: 解析控制器方法的返回值
    常见的返回值处理器
        org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler@4c9e38
        org.springframework.web.method.annotation.ModelMethodProcessor@5d1e09bc
        org.springframework.web.servlet.mvc.method.annotation.ViewMethodReturnValueHandler@4bdc8b5d
        org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler@3bcd426c
        org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBodyReturnValueHandler@5f14a673
        org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor@726a17c4
        org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler@5dc3fcb7
        org.springframework.web.servlet.mvc.method.annotation.CallableMethodReturnValueHandler@c4c0b41
        org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler@76911385
        org.springframework.web.servlet.mvc.method.annotation.AsyncTaskMethodReturnValueHandler@5467eea4
        org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor@160396db
        org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor@7a799159
        org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler@40ab8a8
        org.springframework.web.method.annotation.MapMethodProcessor@6ff37443
        org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor@65cc8228
 */
public class A27 {
    private static final Logger log = LoggerFactory.getLogger(A27.class);

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(WebConfig.class);

        // 2. 测试返回值类型为 String 时, 把它当做视图名
        test4(context);

        // 3. 测试返回值添加了 @ModelAttribute 注解时, 此时需找到默认视图名

        // 4. 测试返回值不加 @ModelAttribute 注解且返回非简单类型时, 此时需找到默认视图名

        // 5. 测试返回值类型为 ResponseEntity 时, 此时不走视图流程

        // 6. 测试返回值类型为 HttpHeaders 时, 此时不走视图流程

        // 7. 测试返回值添加了 @ResponseBody 注解时, 此时不走视图流程

        /*
            学到了什么
                a. 每个返回值处理器能干啥
                    1) 看是否支持某种返回值
                    2) 返回值或作为模型、或作为视图名、或作为响应体 ...
                b. 组合模式在 Spring 中的体现 + 1
         */
    }
    private static void test4(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getDeclaredMethod("test4");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);
        HandlerMethod methodHandle = new HandlerMethod(controller, method);


        ModelAndViewContainer container = new ModelAndViewContainer();

        // 1. 测试返回值类型为 ModelAndView
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test4");
        UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);

        ServletWebRequest webRequest = new ServletWebRequest(request, new MockHttpServletResponse());

        //检查是否支持返回值类型
        if (composite.supportsReturnType(methodHandle.getReturnType())) {
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            System.out.println(container.getModel());
            System.out.println(container.getViewName()  );

            renderView(context, container, webRequest);
        }
    }
    private static void test3(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getDeclaredMethod("test3");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);
        HandlerMethod methodHandle = new HandlerMethod(controller, method);


        ModelAndViewContainer container = new ModelAndViewContainer();

        // 1. 测试返回值类型为 ModelAndView
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test3");
        UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);

        ServletWebRequest webRequest = new ServletWebRequest(request, new MockHttpServletResponse());

        //检查是否支持返回值类型
        if (composite.supportsReturnType(methodHandle.getReturnType())) {
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            System.out.println(container.getModel());
            System.out.println(container.getViewName()  );

            renderView(context, container, webRequest);
        }
    }
    private static void test2(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getDeclaredMethod("test2");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);
        HandlerMethod methodHandle = new HandlerMethod(controller, method);


        ModelAndViewContainer container = new ModelAndViewContainer();

        // 1. 测试返回值类型为 ModelAndView
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        ServletWebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());
        //检查是否支持返回值类型
        if (composite.supportsReturnType(methodHandle.getReturnType())) {
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            System.out.println(container.getModel());
            System.out.println(container.getViewName()  );

            renderView(context, container, webRequest);
        }
    }
    private static void test1(AnnotationConfigApplicationContext context) throws Exception {
        Method method = Controller.class.getDeclaredMethod("test1");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller);
        HandlerMethod methodHandle = new HandlerMethod(controller, method);


        ModelAndViewContainer container = new ModelAndViewContainer();

        // 1. 测试返回值类型为 ModelAndView
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        ServletWebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());
        //检查是否支持返回值类型
        if (composite.supportsReturnType(methodHandle.getReturnType())) {
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            System.out.println(container.getModel());
            System.out.println(container.getViewName()  );

            renderView(context, container, webRequest);
        }
    }

    static class Controller {
        private static final Logger log = LoggerFactory.getLogger(Controller.class);

        public ModelAndView test1() {
            log.debug("test1()");
            ModelAndView mav = new ModelAndView("view1");
            mav.addObject("name", "张三");
            return mav;
        }

        public String test2() {
            log.debug("test2()");
            return "view2";
        }

        @ModelAttribute
//        @RequestMapping("/test3")
        public User test3() {
            log.debug("test3()");
            return new User("李四", 20);
        }

        public User test4() {
            log.debug("test4()");
            return new User("王五", 30);
        }

        public HttpEntity<User> test5() {
            log.debug("test5()");
            return new HttpEntity<>(new User("赵六", 40));
        }

        public HttpHeaders test6() {
            log.debug("test6()");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            return headers;
        }

        @ResponseBody
        public User test7() {
            log.debug("test7()");
            return new User("钱七", 50);
        }
    }

    public static HandlerMethodReturnValueHandlerComposite getReturnValueHandler() {
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        composite.addHandler(new ModelAndViewMethodReturnValueHandler());
        composite.addHandler(new ViewNameMethodReturnValueHandler());
        composite.addHandler(new ServletModelAttributeMethodProcessor(false));
        composite.addHandler(new HttpEntityMethodProcessor(List.of(new MappingJackson2HttpMessageConverter())));
        composite.addHandler(new HttpHeadersReturnValueHandler());
        composite.addHandler(new RequestResponseBodyMethodProcessor(List.of(new MappingJackson2HttpMessageConverter())));
        composite.addHandler(new ServletModelAttributeMethodProcessor(true));
        return composite;
    }

    @SuppressWarnings("all")
    private static void renderView(AnnotationConfigApplicationContext context, ModelAndViewContainer container,
                                   ServletWebRequest webRequest) throws Exception {
        log.debug(">>>>>> 渲染视图");
        FreeMarkerViewResolver resolver = context.getBean(FreeMarkerViewResolver.class);
        String viewName = container.getViewName() != null ? container.getViewName() : new DefaultRequestToViewNameTranslator().getViewName(webRequest.getRequest());
        log.debug("没有获取到视图名, 采用默认视图名: {}", viewName);
        // 每次渲染时, 会产生新的视图对象, 它并非被 Spring 所管理, 但确实借助了 Spring 容器来执行初始化
        View view = resolver.resolveViewName(viewName, Locale.getDefault());
        view.render(container.getModel(), webRequest.getRequest(), webRequest.getResponse());
        System.out.println(new String(((MockHttpServletResponse) webRequest.getResponse()).getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    // 必须用 public 修饰, 否则 freemarker 渲染其 name, age 属性时失败
    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                   "name='" + name + '\'' +
                   ", age=" + age +
                   '}';
        }
    }
}
