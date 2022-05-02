package cn.edu.swu.spring_yihang.a23;

import org.springframework.beans.SimpleTypeConverter;

import java.util.Date;

/**
 * @author zeyu
 * @date 2022/05/01
 **/


public class TestSimpleTypeConverter {
    public static void main(String[] args) {
        SimpleTypeConverter converter = new SimpleTypeConverter();
        Integer integer = converter.convertIfNecessary("13", int.class);
        Date date = converter.convertIfNecessary("1999/12/27", Date.class);
        System.out.println(date);
        System.out.println(integer);
    }
}
