package cn.edu.swu.spring_yihang.a09;

import cn.edu.swu.spring_yihang.a08.BeanForRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zeyu
 * @date 2022/04/27
 **/

@ComponentScan("cn.edu.swu.spring_yihang.a09")
public class A09Application {
    private static final Logger log = LoggerFactory.getLogger(BeanForRequest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(A09Application.class);


        E e = context.getBean(E.class);
//        log.debug("{}", e.getF1().getClass());
//        log.debug("{}",e.getF1());
//        log.debug("{}",e.getF1());

        log.debug("{}", e.getF4());
        log.debug("{}", e.getF4());
        context.close();


    }
}
