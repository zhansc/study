package cn.com.zhanss.wework;

import cn.com.zhanss.wework.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @desc 启动类
 * @author zhanshuchan
 * @date 2021/7/22
 */
@SpringBootApplication(scanBasePackages = {"cn.com.zhanss.wework"})
@Controller
public class WeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeworkApplication.class, args);
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg", "index");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser() {
        return new User(1, "李四", "男", "12345678901");
    }
}
