package cn.edu.swu.spring_yihang.a23;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;

import java.util.Date;

/**
 * @author zeyu
 * @date 2022/05/01
 **/
public class TestServletDataBinder {
    public static void main(String[] args) {

        MyBean target = new MyBean();

        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(target);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("a", "10");
        request.setParameter("b", "hello");
        request.setParameter("Date", "1999/12/27");

        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
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
