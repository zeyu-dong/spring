package cn.edu.swu.spring_yihang.a05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
