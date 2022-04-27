package cn.edu.swu.spring_yihang.a12;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class CglibProxyDemo {
    static class Target{
        public void foo(){
            System.out.println("target foo");
        }
    }



    public static void main(String[] param) {
        Target target = new Target();
        Target proxy = (Target) Enhancer.create(Target.class, (MethodInterceptor) (p, method, args, methodProxy) -> {
            System.out.println("before");

            //反射调用目标
//            Object invoke = method.invoke(target, args);

            //methodProxy避免反射调用方法
//            Object invoke = methodProxy.invoke(target, args);

            Object result = methodProxy.invokeSuper(p, args);//内部没有用反射，需要代理

            System.out.println("after");
            return result;
        });


        proxy.foo();

    }
}
