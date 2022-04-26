package cn.edu.swu.spring_yihang.a03;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class TestMethodTemplate {
    public static void main(String[] args) {

    }

    static class MyBeanFactory{
        public Object getBean(){
            Object bean = new Object();
            System.out.println("构造"+bean);
            System.out.println("依赖注入"+bean);//希望能解析Autowired
            System.out.println("初始化" + bean);
            return bean;
        }

        //变化的抽象成接口
        static interface BeanPostProcessor{
            public void inject();
            public void inject();
            public void inject();
            public void inject();
            public void inject();
            public void inject();
        }
    }
}
