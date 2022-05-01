package cn.edu.swu.spring_yihang.a13;

import cn.edu.swu.spring_yihang.a13.A13.Foo;
import cn.edu.swu.spring_yihang.a13.A13.Target;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author zeyu
 * @date 2022/04/27
 **/
public class $Proxy0 implements Foo {

    private InvocationHandler h;

    public $Proxy0(InvocationHandler h) {
        this.h = h;
    }

    @Override
    public void foo() {
        try {
            h.invoke(this, foo, new Object[0]);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException( e);
        }

    }

    @Override
    public int bar() {
        try {

            Object result = h.invoke(this, bar, new Object[0]);
            return (int) result;
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }

    }

    static Method foo;
    static Method bar;

    static {
        try {
            foo =   Foo.class.getMethod("foo");
            bar =   Foo.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }

    }
}
