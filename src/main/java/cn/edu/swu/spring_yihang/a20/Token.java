package cn.edu.swu.spring_yihang.a20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zeyu
 * @date 2022/04/30
 **/

//需要经常用到请求头中的token信息，用下面注解来标注由哪个参数来获取它
//    token=令牌


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

public @interface Token {
}
