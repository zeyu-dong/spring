package cn.edu.swu.spring_yihang.A04;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zeyu
 * @date 2022/04/26
 **/


@ConfigurationProperties(prefix = "java")
@Component
public class Bean4 {

    private String version;
    private String home;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Bean4{" +
                "version='" + version + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
