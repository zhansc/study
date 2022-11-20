package cn.com.zhanss.springboot.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 条件注解
 * 实现Condition接口，重写matches方法，配合@Bean使用，@Conditional
 * 引入的类型的matches方法返回的是true，则注入，否则不注入
 *
 * @author zhanss
 * @since 2022-10-13
 */
public class ConditionalOnStudent implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 根据特定业务需求来决定是否注入对应的Bean
        try {
            // @ConditionOnClass
            Class.forName("cn.com.zhanss.zhaotongxue");
            return true;
        } catch (Exception e) {
            // @ConditionOnBean
            // 此处存在加载当前类的时候，Teacher这个Bean可能还没有加载进来
            // 因为IoC容器会先加载的实现了Condition接口的类，然后在加载的对应的@Configuration
//            boolean flag = context.getRegistry().containsBeanDefinition("teacher");
            return false;
        }

    }

}
