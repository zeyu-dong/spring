package cn.edu.swu.spring_yihang.A04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class Bean1 {
    private static final Logger log = LoggerFactory.getLogger(Bean1.class);
    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2) {
        log.debug("Autowired生效");
        this.bean2 = bean2;
    }

    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3) {
        log.debug("@Resource生效");
        this.bean3 = bean3;
    }


//    private String home;
//    @Autowired
//    public void setHome(@Value("${JAVA_HOME }") String home) {
//        log.debug("@Value生效");
//        this.home = home;
//    }

    @PostConstruct
    public void init(){
        log.debug("@PostConstruct生效");
    }


    @PreDestroy
    public void destroy() {
        log.debug("@PreDestroy 生效");
    }


    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +

                '}';
    }
}
