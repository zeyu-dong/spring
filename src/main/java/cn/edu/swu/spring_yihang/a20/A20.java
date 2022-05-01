package cn.edu.swu.spring_yihang.a20;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author zeyu
 * @date 2022/04/30
 **/
public class A20 {
    private static final Logger log = LoggerFactory.getLogger(A20.class);

    public static void main(String[] args) throws Exception {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        //解析RequestMapping 注解，根据路径与控制器方法的映射关系。在初始化生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
        MyRequestHandlerMappingHandlerAdpter handlerAdapter = context.getBean(MyRequestHandlerMappingHandlerAdpter.class);

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
//        handlerMethods.forEach((key,value)->{
//            System.out.println(key + "=" + value);
//        });

        MockHttpServletResponse response = new MockHttpServletResponse();

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test4");
        request.setParameter("name", "dongzeyu");
        request.addHeader("token", "某个令牌");


        //请求来了，获取控制器方法
        HandlerExecutionChain chain = handlerMapping.getHandler(request);

        handlerAdapter.invokeHandlerMethod(request, response, (HandlerMethod) chain.getHandler());
        //检查响应

        byte[] bytes = response.getContentAsByteArray();
        System.out.println(new String(bytes, StandardCharsets.UTF_8));


//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>参数解析器");
//        List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
//        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
//            System.out.println(argumentResolver);
//
//        }
//
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>参数解析器");
//        for (HandlerMethodReturnValueHandler returnValueHandler : handlerAdapter.getReturnValueHandlers()) {
//            System.out.println(returnValueHandler);
//        }


    }



}
