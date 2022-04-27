package cn.edu.swu.spring_yihang.A07;

import cn.edu.swu.spring_yihang.A06.MyConfig1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class Bean2 implements DisposableBean {
    private static final Logger log = LoggerFactory.getLogger(MyConfig1.class);

    @PreDestroy
    public void destroy1(){log.debug("销毁1");}


    @Override
    public void destroy() throws Exception {
        log.debug("销毁2");
    }

    public void destroy3(){log.debug("销毁3");}

}
