package cn.edu.swu.spring_yihang.a09;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author zeyu
 * @date 2022/04/27
 **/

@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class F1 {

}
