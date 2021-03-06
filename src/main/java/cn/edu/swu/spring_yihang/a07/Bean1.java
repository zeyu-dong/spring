package cn.edu.swu.spring_yihang.a07;

import cn.edu.swu.spring_yihang.a06.MyConfig1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class Bean1 implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MyConfig1.class);


    @PostConstruct
    public void init1() {
        log.debug("初始化1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("初始化2");
    }

    public void init3() {
        log.debug("初始化3");
    }
}
