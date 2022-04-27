package cn.edu.swu.spring_yihang.a10.aop;

import cn.edu.swu.spring_yihang.a10.service.Myservice;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
@Aspect
public class MyAspect {
    private static final Logger log = LoggerFactory.getLogger(Myservice.class);

    //@Before("execution(* com.itheima.service.MyService.foo())")
    @Before("execution(* cn.edu.swu.spring_yihang.a10.service.Myservice.foo())")
    public void before(){
        log.debug("before");

    }

}
