package cn.edu.swu.spring_yihang.a20;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zeyu
 * @date 2022/04/30
 **/
public class YmlReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Yml yml = returnType.getMethodAnnotation(Yml.class);

        return yml != null;
    }

    @Override   //返回值
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //转换结果为yml对象
        String dump = new Yaml().dump(returnValue);
        //将yaml写入响应体中
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().print(dump);
        mavContainer.setRequestHandled(true);
    }
}
