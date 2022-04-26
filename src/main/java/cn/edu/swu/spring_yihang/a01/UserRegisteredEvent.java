package cn.edu.swu.spring_yihang.a01;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @author zeyu
 * @date 2022/04/24
 **/
public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source) {
        super(source);
    }

}
