package cn.edu.swu.spring_yihang.a14;

import org.springframework.cglib.core.Signature;
//import sun.jvm.hotspot.gc.shared.Generation;

import javax.annotation.Resource;
import java.util.Scanner;

/**
 * @author zeyu
 * @date 2022/04/28
 **/
public class TargetFastClass {

    static Signature s0 = new Signature("save", "()V");
    static Signature s1 = new Signature("save", "(I)V");
    static Signature s2 = new Signature("save", "(J)V");
    //获取目标方法编号

    public int getIndex(Signature signature) {

        if (signature.equals(s0)){
            return 0;
        } else if (signature.equals(s1)){
            return 1;
        } else if (signature.equals(s2)){
            return 2;
        }
        return -1;

    }
//
    public Object invoke(int index, Object target, Object[] args) {
        if (index == 0) {
            ((Target) target).save();
            return null;
        } else if (index == 1) {
            ((Target) target).save((int) args[0]);
            return null;
        } else if (index == 2) {
            ((Target) target).save((long) args[0]);
        } else {
            throw new RuntimeException("无此方法");
        }
        return null;
    }

    public static void main(String[] args) {
        TargetFastClass fastClass = new TargetFastClass();
        int i = fastClass.getIndex(new Signature("save", "()V"));
        System.out.println(i);
        fastClass.invoke(i, new Target(), new Object[0]);

    }

}
