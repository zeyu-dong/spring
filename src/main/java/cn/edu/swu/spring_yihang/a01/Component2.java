package cn.edu.swu.spring_yihang.a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zeyu
 * @date 2022/04/23
 */


//@Component
public class Component2 {
    private static final Logger log = LoggerFactory.getLogger( "Component2.class" );

    public static void main(String[] args) {

        log.debug("dsadf");
    }

    @EventListener
    public void aaa(UserRegisteredEvent event) {

        log.info("{}",event);
        log.info("componet2发送短信");
    }
}