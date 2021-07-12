package cn.com.zhanss.springmvc.controller;

import cn.com.zhanss.entity.User;
import cn.com.zhanss.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @desc 测试参数转化器
 * @author zhanshuchan
 * @date 2021/5/15
 */
@Controller
public class ConverterController {

    @Autowired
    private UserService userService;

    /**
     * 前端传入的属性值一定要和Controller层处理方法参数对应起来
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/converter")
    public String converter(User user, Model model) {

        userService.sayHello();

        System.out.println("=========converter=========="+ user);
//        model.addAttribute("user", user);
        try {
            Method test = this.getClass().getMethod("test", User.class, Model.class);
            Annotation[][] parameterAnnotations = test.getParameterAnnotations();
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                if (parameterAnnotation == null) {
                    continue;
                }
                if (parameterAnnotation.length == 0) {
                    continue;
                }
                for (Annotation annotation : parameterAnnotation) {
                    System.out.println("第N个注解:"+annotation.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public void test(User user, @RequestParam Model model) {

    }

}
