package com.zhanss.framework.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhanss.framework.annotation.Controller;
import com.zhanss.framework.annotation.ReponseBody;
import com.zhanss.framework.annotation.RequestMapping;
import com.zhanss.framework.annotation.RequestParam;
import com.zhanss.framework.context.WebApplicationContext;
import com.zhanss.framework.handler.MyHandler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc 前端控制器
 * @author zhanshuchan
 * @date 2021/6/27
 */
public class DispatcherServlet extends HttpServlet {

    private WebApplicationContext webApplicationContext;

    /**
     * controller层请求路径映射关系
     */
    private Map<String, MyHandler> handlerMapping = new ConcurrentHashMap<String, MyHandler>();

    @Override
    public void init() {
        // 读取SpringMVC配置文件
        String contextConfigLocation = this.getServletConfig().getInitParameter("contextConfigLocation");

        // 创建SpringMVC容器
        webApplicationContext = new WebApplicationContext(contextConfigLocation);

        // 初始化
        webApplicationContext.onRefresh();

        // 解析controller层的请求地址
        this.initHandlerMapping();
    }

    /**
     * 请求分发
     * @param request
     * @param response
     */
    public void doDispatcher(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uri = request.getRequestURI();
            // 自动补齐"/"
            MyHandler handler = handlerMapping.get(uri);
            if (handler != null) {
                // 获取请求参数
                Method method = handler.getMethod();
                // 返回的是一个二维数组，第一项是参数的位置，固定的（即使第一个参数没有添加注解，还是占一个位置，为空数组），第二项是当前位置多个注解的数量
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                Object[] args = new Object[parameterAnnotations.length];
                int i = 0;
                for (Annotation[] parameterAnnotation : parameterAnnotations) {
                    i ++;
                    if (parameterAnnotation != null) {
                        for (Annotation annotation : parameterAnnotation) {
                            if (annotation instanceof RequestParam) {
                                String valueName = ((RequestParam) annotation).value();
                                args[i] = request.getParameterValues(valueName);
                            }
                        }
                    }
                }

                // 通过反射调用controller方法
                Object result = method.invoke(handler.getController(), args);
                if (result instanceof String) {
                    String page = (String) result;
                    if (page.contains(":")) {
                        String[] split = page.split(":");
                        String pageType = split[0];
                        String pagePath = split[1];
                        if ("forword".equals(pageType)) {
                            // 转发
                            request.getRequestDispatcher(pagePath).forward(request, response);
                        } else if ("redirect".equals(pageType)) {
                            // 重定向
                            response.sendRedirect(pagePath);
                        }
                    } else {
                        // 默认走转发
                        request.getRequestDispatcher(page).forward(request, response);
                    }

                } else {
                    // 对象转成JSON格式数据
                    if (method.isAnnotationPresent(ReponseBody.class)) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(result);
                        response.setContentType("text/html;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.print(json);
                        writer.flush();
                        writer.close();
                    }
                }
            } else {
                // 默认走转发
                String basePath = "WEB-INF"+ (uri.startsWith("/") ? uri : "/"+ uri) + ".jsp";
                request.getRequestDispatcher(basePath).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析controller层的请求地址
     */
    public void initHandlerMapping() {
        try {
            // 遍历所有的bean，解析controller层的请求地址
            for (Map.Entry<String, Object> entry : webApplicationContext.singletonObjects.entrySet()) {
                Class<?> clazz = entry.getValue().getClass();
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // Controller类上的@RequestMapping注解
                    RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
                    String rootPath = "";
                    if (clazzAnnotation != null) {
                        // 需要处理自动补齐"/"
                        rootPath = clazzAnnotation.value();
                        if (StringUtils.isNotBlank(rootPath) && !rootPath.startsWith("/")) {
                            rootPath = "/".concat(rootPath);
                        }
                    }

                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        // 解析controller层的所有方法上的@RequestMapping注解
                        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                        if (methodAnnotation == null) {
                            continue;
                        }
                        String methodPath = methodAnnotation.value();
                        if (StringUtils.isNotBlank(methodPath) && !methodPath.startsWith("/")) {
                            methodPath = "/".concat(methodPath);
                        }
                        String basePath = rootPath + methodPath;
                        MyHandler handler = new MyHandler(basePath, method, entry.getValue());
                        handlerMapping.put(basePath, handler);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doDispatcher(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);

    }

}
