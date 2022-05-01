package cn.edu.swu.spring_yihang.a14;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zeyu
 * @date 2022/04/28
 **/
public class A14 {
    public static void main(String[] args) {
        Target target = new Target();
        Proxy proxy = new Proxy();

        proxy.setInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object p, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //增强
                System.out.println("before");
                //方法反射调用
//                return method.invoke(target, objects);



                //fastclass
//               return methodProxy.invoke(target, objects);//内部无反射，结合目标用

                return methodProxy.invokeSuper(p, objects);//内部无反射，结合代理用
            }
        });

        proxy.save();
        proxy.save(1);
        proxy.save(2L);


    }
}
