package cn.edu.swu.spring_yihang.a15;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
//import org.graalvm.compiler.debug.CSVUtil;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author zeyu
 * @date 2022/04/29
 **/
public class A15 {
    public static void main(String[] args) {
//      1.备好切点

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

//      2.备好通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {

                System.out.println("before");
                Object proceed = invocation.proceed();
                System.out.println("after");
                return proceed;
            }
        };
//      3.准备切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,advice);

//      4.创建代理


        Target1 target1 = new Target1();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target1);

        factory.addAdvisor(advisor);
        factory.setInterfaces(target1.getClass().getInterfaces());
        factory.setProxyTargetClass(true);



        I1 proxy = (I1) factory.getProxy();
        System.out.println(proxy.getClass());

        proxy.foo();
        proxy.bar();



    }

    interface I1{
        void foo();
        void bar();
    }

    static class Target1 implements I1 {
        @Override
        public void foo() {
            System.out.println("target1 foo");
        }

        @Override
        public void bar() {
            System.out.println("target1 bar");
        }
    }
    static class Target2 implements I1 {
        @Override
        public void foo() {
            System.out.println("2  foo");
        }

        @Override
        public void bar() {
            System.out.println("2   bar");
        }
    }
}
