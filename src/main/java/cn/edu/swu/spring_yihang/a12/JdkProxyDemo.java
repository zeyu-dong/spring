package cn.edu.swu.spring_yihang.a12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class JdkProxyDemo {
    public interface Foo{
        void foo();
    }
    static class Target implements Foo{
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    //jdk只可以针对接口
    //cglib
    public static void main(String[] args) {
        //target准备目标对象
        Target target = new Target();


        //代理类和普通类的区别是：普通类先写源代码编译类加载使用。代理类没有源码，在运行期间直接生成代理类的字节码
        //生成的字节码需要在加载后运行，自然需要loader进行加载运行
        ClassLoader loader = JdkProxyDemo.class.getClassLoader();

        //第二个参数是实现那些接口，当然是数组封装了
        //第三个参数是规定接口或者说方法的行为，需要提供一个invocationhandler的实现

        //invocationhandler匿名内部类三个参数 第一个参数：代理对象自己，第二个参数：正在执行的方法对象 第三个参数：方法传过来的实际参数
       Foo proxy = (Foo) Proxy.newProxyInstance(loader, new Class[]{Foo.class}, (p, method, objects) -> {
            System.out.println("before");
            //传统就是目标吊方法

           //可以方法反射调目标

           Object o = method.invoke(target, args);
           System.out.println("after");
           //让代理也返回目标方法执行的结果
           return o;
        });

       proxy.foo();

    }
}
