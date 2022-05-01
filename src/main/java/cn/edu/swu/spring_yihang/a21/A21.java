package cn.edu.swu.spring_yihang.a21;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockPart;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExpressionValueMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zeyu
 * @date 2022/05/01
 **/
public class A21 {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        HttpServletRequest request = mockRequest();

        //控制器方法封装为HandlerMethod
        HandlerMethod handlerMethod = new HandlerMethod
                (new Controller(), Controller.class.getDeclaredMethod("test", String.class, String.class, int.class, String.class, MultipartFile.class, int.class, String.class, String.class, String.class, HttpServletRequest.class, User.class, User.class, User.class));

        //准备对象绑定与类型封装
        RequestParamMethodArgumentResolver resolver =
                new RequestParamMethodArgumentResolver(null, false);

        //webview container执行过程产生模型数据
        ModelAndViewContainer container = new ModelAndViewContainer();
        //解析每个参数值
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {




            String annotations = Arrays.stream(methodParameter
                    .getParameterAnnotations())
                    .map(a -> a.annotationType().getSimpleName())
                    .collect(Collectors.joining());


            String str = annotations.length() > 0 ? " @" + annotations+" " : " ";

            methodParameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());



            if (resolver.supportsParameter(methodParameter)) {
                //支持参数
                Object o = resolver.resolveArgument(methodParameter, container, new ServletWebRequest(request), null);
                System.out.println("[" + methodParameter.getParameterIndex()
                        + "]"
                        + str
                        + methodParameter.getParameterType().getSimpleName()
                        + " "
                        + methodParameter.getParameterName()
                        + "  ------>  " + o
                );


            }else {

                System.out.println("[" + methodParameter.getParameterIndex()
                        + "]"
                        + str
                        + methodParameter.getParameterType().getSimpleName()
                        + " "
                        + methodParameter.getParameterName());
            }

        }


    }


    private static HttpServletRequest mockRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name1", "zhangsan");
        request.setParameter("name2", "lisi");
        request.addPart(new MockPart("file", "abc", "hello".getBytes(StandardCharsets.UTF_8)));
        Map<String, String> map = new AntPathMatcher().extractUriTemplateVariables("/test/{id}", "/test/123");
        System.out.println(map);
        request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, map);
        request.setContentType("application/json");
        request.setCookies(new Cookie("token", "123456"));
        request.setParameter("name", "张三");
        request.setParameter("age", "18");
//        request.setContent("""
//                    {
//                        "name":"李四",
//                        "age":20
//                    }
//                """.getBytes(StandardCharsets.UTF_8));

        return new StandardServletMultipartResolver().resolveMultipart(request);
    }

    static class Controller {
        public void test(
                @RequestParam("name1") String name1, // name1=张三
                String name2,                        // name2=李四
                @RequestParam("age") int age,        // age=18
                @RequestParam(name = "home", defaultValue = "${JAVA_HOME}") String home1, // spring 获取数据
                @RequestParam("file") MultipartFile file, // 上传文件
                @PathVariable("id") int id,               //  /test/124   /test/{id}
                @RequestHeader("Content-Type") String header,
                @CookieValue("token") String token,
                @Value("${JAVA_HOME}") String home2, // spring 获取数据  ${} #{}
                HttpServletRequest request,          // request, response, session ...
                @ModelAttribute("abc") User user1,          // name=zhang&age=18
                User user2,                          // name=zhang&age=18
                @RequestBody User user3              // json
        ) {
        }
    }

    static class User {
        private String name;
        private int age;

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
