package cn.edu.swu.spring_yihang.a20;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.Yaml;

/**
 * @author zeyu
 * @date 2022/04/30
 **/

@Controller
public class Controller1 {

    private static final Logger log = LoggerFactory.getLogger(Controller1.class);

    @GetMapping("/test1")
    public ModelAndView tes1() {
        log.debug("test1");
        return null;
    }

    @PostMapping("/test2")
    public ModelAndView test2(@RequestParam("name") String name) {
        log.debug("test2{}", name);
        return null;
    }

    @PutMapping("/test3")
    public ModelAndView test3(@Token String token) {
        log.debug("test3{}", token);
        return null;
    }

    @RequestMapping("/test4")

    //代表方法的返回值要被写到响应体中去
//    @ResponseBody
    @Yml
    public User test4(){
        log.debug("test4");
        return new User("董泽宇", 22);
    }
    static class User{
        String name;
        Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

    }

    public static void main(String[] args) {
        String str = new Yaml().dump(new User("董泽宇", 22));
        System.out.println(str);

    }
}
