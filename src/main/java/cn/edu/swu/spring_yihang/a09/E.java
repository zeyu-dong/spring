package cn.edu.swu.spring_yihang.a09;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
@Component
public class E {

    @Lazy
    @Autowired
    private F1 f1;

    @Autowired
    private ObjectFactory<F3> f3;


    @Autowired
    private ApplicationContext context;
    public F3 getF3() {
        return f3.getObject();
    }

    public void setF3(ObjectFactory<F3> f3) {
        this.f3 = f3;
    }

    public F1 getF1() {
        return f1;
    }

    public F4 getF4(){
        return context.getBean(F4.class);
    }
}
