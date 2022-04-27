package cn.edu.swu.spring_yihang.a10.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
@Service
public class Myservice {
    private static final Logger log = LoggerFactory.getLogger(Myservice.class);

    public void foo(){
        log.debug("foo");

    }
}
