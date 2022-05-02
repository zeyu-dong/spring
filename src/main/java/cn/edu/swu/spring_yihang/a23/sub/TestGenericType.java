package cn.edu.swu.spring_yihang.a23.sub;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import javax.servlet.ServletOutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestGenericType {
    public static void main(String[] args) {
        //jdk api

        Type type = TeacherDao.class.getGenericSuperclass();

        System.out.println(type);
        if (type instanceof ParameterizedType parameterizedType) {
            Type[] types = parameterizedType.getActualTypeArguments();
            System.out.println(types[0]);

        }

        //spring api
        Class<?> aClass = GenericTypeResolver.resolveTypeArgument(TeacherDao.class, BaseDao.class);
        System.out.println(aClass);

    }

}
