package cn.com.zhanss.springboot.conditional;

import cn.com.zhanss.spring.entity.Student;
import cn.com.zhanss.spring.entity.Teacher;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.junit.Test;
import java.util.Map;

/**
 * springboot条件注解
 *
 * @author zhanss
 * @since 2022-04-16
 */
public class Conditional {

    /**
     * 指定自动配置类之间的顺序
     */
    @AutoConfigureOrder(1)
    /**
     * 学生配置类在老师配置类之后加载
     */
    @AutoConfigureAfter(name = "TeacherBeanConfig")
    @Configuration
    public class StudentBeanConfig {

        @Bean("student")
        public Student student1() {
            return new Student("开心");
        }

        /**
         * @ConditionalOnMissingBean
         * 当student1实例bean加载失败则加载下面的student2，最好放在student1实例后面
         * @ConditionalOnBean 和 @ConditionalOnMissingBean作用相反
         * @return
         */
        @ConditionalOnMissingBean
        @Bean("student")
        public Student student2() {
            return new Student("幸福");
        }
    }

    @Configuration
    public class TeacherBeanConfig {
        @Bean("zhaoTeacher")
        public Teacher zhao() {
            return new Teacher("赵老师");
        }

        /**
         * 有赵老师bean实例詹老师实例bean才生效
         * @return
         */
        @ConditionalOnBean
        @Bean("zhaoTeacher")
        public Teacher zhan() {
            return new Teacher("詹老师");
        }
    }

    public static class ConditionalTest implements ApplicationContextAware {

        private ApplicationContext applicationContext;

        @Test
        public void testBean() {
            Map<String, Student> beansOfType = applicationContext.getBeansOfType(Student.class);
            System.out.println(beansOfType);
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }
}
