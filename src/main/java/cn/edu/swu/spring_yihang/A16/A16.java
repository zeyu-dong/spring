package cn.edu.swu.spring_yihang.A16;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @author zeyu
 * @date 2022/04/29
 **/
public class A16 {
    public static void main(String[] args) throws NoSuchMethodException {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* bar())");
        boolean foo = pointcut.matches(T1.class.getMethod("foo"), T1.class);
        boolean bar = pointcut.matches(T1.class.getMethod("bar"), T1.class);
//        System.out.println(foo);
//        System.out.println(bar);
//        根据注解进行匹配
        AspectJExpressionPointcut pt2 = new AspectJExpressionPointcut();
//        就是判断方法上是否有这个注解
        pt2.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
//        System.out.println(pt2.matches(T1.class.getMethod("foo"), T1.class));


        StaticMethodMatcherPointcut pt3 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                MergedAnnotations from = MergedAnnotations.from(method);

                //检查方法上是否加了
                if (from.isPresent(Transactional.class)) {
                    return true;
                }
                from = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                if (from.isPresent(Transactional.class)) {
                    return true;
                }
                return false;
            }
        };
//        System.out.println(">>>>>>>>>>>>>>>>>>>>");

        System.out.println(pt3.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt3.matches(T2.class.getMethod("foo"), T2.class));
        System.out.println(pt3.matches(T3.class.getMethod("foo"), T3.class));


    }



    static class T1{
        @Transactional
        public void foo(){}
        public void bar(){}
    }

    @Transactional
    static class T2{ public void foo(){}
        public void bar(){}
    }

    @Transactional
    interface I3{
        void foo();
    }
    static class T3 implements I3{
        @Override
        public void foo() {

        }
    }

}
