package cn.edu.swu.spring_yihang.a13;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

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




    public static void main(String[] args) throws IOException {
        Foo proxy = new $Proxy0((proxy1, method, args1) -> {
            System.out.println("before");
            return method.invoke(new Target(), args);
        });

        proxy.foo();
        int bar = proxy.bar();
//      System.out.println(bar);
        System.in.read();
        
    }
}
