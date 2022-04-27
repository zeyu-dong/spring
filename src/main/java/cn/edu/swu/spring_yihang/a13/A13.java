package cn.edu.swu.spring_yihang.a13;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class A13 {
    interface Foo{
        void foo();
        int bar();
    }

    static class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("target foo");
        }

        @Override
        public int  bar() {
            System.out.println("target bar");
            return 100;
        }
    }

    interface InvocationHandler{
        Object invoke(Object proxy,Method method,Object[] args) throws InvocationTargetException, IllegalAccessException;
    }


    public static void main(String[] args) {
        Foo proxy = new $Proxy0(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy,Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println("before");
                return method.invoke(new Target(),args);

            }
        });
        proxy.foo();
        int bar = proxy.bar();
        System.out.println(bar);


    }
}
