package cn.edu.swu.spring_yihang.a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class Component1 {
    private static final Logger log = LoggerFactory.getLogger( "Component1.class" );

    @Autowired
    private ApplicationEventPublisher context;

    public void register() {

        log.info("用户注册");
        log.info("发短信");
        context.publishEvent(new UserRegisteredEvent(this));

    }
}