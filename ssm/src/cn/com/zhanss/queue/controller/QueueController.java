package cn.com.zhanss.queue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhanss
 * @since 2019/10/18
 */
@Controller
@RequestMapping("/queue")
public class QueueController {

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam String message) {

        return "";
    }

}
