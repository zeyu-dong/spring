package cn.edu.swu.spring_yihang.a03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeyu
 * @date 2022/04/26
 **/



public class TestMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory factory = new MyBeanFactory();
        factory.addBeanPostProcessor(bean -> {System.out.println("解析@Autowired");});
        factory.addBeanPostProcessor(bean -> {System.out.println("解析@Resource");});
        factory.getBean();
    }

    static class MyBeanFactory{
        public Object getBean(){
            Object bean = new Object();
            System.out.println("构造"+bean);
            System.out.println("依赖注入"+bean);//希望能解析Autowired
            for (BeanPostProcessor processor : processors) {
                processor.inject(bean);
            }
            System.out.println("初始化" + bean);
            return bean;
        }

        private List<BeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(BeanPostProcessor processor) {
            processors.add(processor);
        }
        //变化的抽象成接口
        static interface BeanPostProcessor{
            public void inject(Object bean);
        }
    }
}
