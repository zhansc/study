package com.zhanss.framework.handler;

import java.lang.reflect.Method;

/**
 * @desc 处理器
 * @author zhanshuchan
 * @date 2021/6/28
 */
public class MyHandler {

    private String uri;

    private Method method;

    private Object controller;

    public MyHandler(String uri, Method method, Object controller) {
        this.uri = uri;
        this.method = method;
        this.controller = controller;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
