package cn.com.zhanss.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc 自定义试图解析器
 * @author zhanshuchan
 * @date 2021/5/11
 */
@Controller
public class ViewResolverController {

    @RequestMapping("zhanss")
    public String customizeResolver() {
        System.out.println("customizeResolver");
        return "zhanss:/index.jsp";
    }

    @RequestMapping("zhansc")
    public String customizeResolver2() {
        System.out.println("customizeResolver2");
        return "zhansc:/index";
    }
}
