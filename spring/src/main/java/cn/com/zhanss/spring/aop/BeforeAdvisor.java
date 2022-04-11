package cn.com.zhanss.spring.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

/**
 * 前置通知
 *
 * @author zhanss
 * @since 2022-04-11
 */
public class BeforeAdvisor implements Advisor {
    @Override
    public Advice getAdvice() {
        System.out.println("BeforeAdvisor前置通知....");
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
