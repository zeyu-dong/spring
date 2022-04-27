package cn.edu.swu.spring_yihang.A05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author zeyu
 * @date 2022/04/26
 **/
public class Bean1 {
    private static final Logger log = LoggerFactory.getLogger(Bean1.class);

    public Bean1() {
        log.debug("我被管理了");

    }
}
