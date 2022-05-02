package cn.edu.swu.spring_yihang.a22;

import b.c.Bean2;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author zeyu
 * @date 2022/05/01
 **/
public class A22 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Method foo =
                Bean2.class.getDeclaredMethod("foo", String.class, int.class);
//
//        for (Parameter parameter : foo.getParameters()) {
//            System.out.println(parameter.getName());
//        }

        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

        String[] parameterNames = discoverer.getParameterNames(foo);


        for (String parameterName : parameterNames) {
            System.out.println(parameterName);
        }
    }
}
