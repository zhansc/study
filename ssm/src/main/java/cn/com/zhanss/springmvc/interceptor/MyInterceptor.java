package cn.com.zhanss.springmvc.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author zhanss
 * @since 2022-04-12
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("执行handle之前，打印下日志、做一系列的校验操作，若校验失败返回false");
        return true;
    }

    @Override
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                    @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("执行handle之后，做消息、业务同步等");
    }

    @Override
    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {
        System.out.println("最后的最后，做一些连接、线程、锁等的资源的释放操作");
    }
}
