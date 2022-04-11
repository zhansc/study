package cn.com.zhanss.spring.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

/**
 * 后置通知
 *
 * @author zhanss
 * @since 2022-04-11
 */
public class AfterAdvisor implements Advisor {
    @Override
    public Advice getAdvice() {
        System.out.println("AfterAdvisor后置通知....");
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
