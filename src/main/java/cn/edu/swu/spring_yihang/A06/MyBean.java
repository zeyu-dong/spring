package cn.edu.swu.spring_yihang.A06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class MyBean implements BeanNameAware , ApplicationContextAware, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MyBean.class);
    @Override
    //注册时设定的名字
    public void setBeanName(String name) {
        log.debug("{}",this);
        log.debug(name);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("容器是：{}",applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("当前Bean "+this+"初始化");
    }


//    下面两个不生效，因为没有添加扩展。但是aware接口不添加仍然生效

    @Autowired
    public void aaa(ApplicationContext applicationContext) {
        log.debug("当前Bean " + this + "使用Autowired初始化" + applicationContext);
    }

    @PostConstruct
    public void init() {
        log.debug("当前Bean " + this + "PostConstruct");
    }
}
