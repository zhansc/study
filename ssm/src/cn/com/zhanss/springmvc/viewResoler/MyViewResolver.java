package cn.com.zhanss.springmvc.viewResoler;

import org.apache.camel.Ordered;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @desc 自定义试图解析器
 * @author zhanshuchan
 * @date 2021/5/11
 */
@Component
// @Order(1)
public class MyViewResolver implements ViewResolver, Ordered {

    private  static final String PREFFIX = "zhanss:";

    @Value("1")
    private int order;

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (!StringUtils.isEmpty(viewName) && viewName.startsWith(PREFFIX)) {
            return new MyView();
        }
        return null;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
