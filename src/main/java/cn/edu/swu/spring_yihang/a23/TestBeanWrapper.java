package cn.edu.swu.spring_yihang.a23;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.SimpleTypeConverter;

import java.util.Date;

/**
 * @author zeyu
 * @date 2022/05/01
 **/
public class TestBeanWrapper {
    public static void main(String[] args) {
        MyBean target = new MyBean();

        BeanWrapperImpl wrapper = new BeanWrapperImpl(target);
        wrapper.setPropertyValue("a", "10");
        wrapper.setPropertyValue("b", "hello");
        wrapper.setPropertyValue("c", "1999/10/13");
        System.out.println(target);

    }
    static class MyBean {
        private int a;
        private String b;
        private Date c;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public Date getC() {
            return c;
        }

        public void setC(Date c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "MyBean{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    ", c=" + c +
                    '}';
        }
    }
}
