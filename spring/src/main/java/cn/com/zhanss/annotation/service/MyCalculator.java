package cn.com.zhanss.annotation.service;

import cn.com.zhanss.annotation.utils.Security;
import org.springframework.stereotype.Service;

/**
 * @desc 自定义计算器
 * @author zhanshuchan
 * @date 2021/2/23
 */
@Service
public class MyCalculator implements Calculator {
    @Override
    @Security(add = "12", added = "12")
    public Integer add(Integer add, Integer added) {
        return add + added;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        return a / b;
    }
}
