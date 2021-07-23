package cn.com.zhanss.springmvc.viewResoler;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @desc 自定义视图
 * @author zhanshuchan
 * @date 2021/5/11
 */
public class MyView implements View {
    @Override
    public String getContentType() {
        return "text/html;charset=UTF-8";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType(getContentType());
        PrintWriter writer = response.getWriter();
        writer.write("你好，詹书禅");
        writer.write("flower");
    }
}
