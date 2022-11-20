package cn.com.zhanss.springboot.conditional;

import cn.com.zhanss.spring.entity.Student;
import cn.com.zhanss.spring.entity.Teacher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 学生配置
 *
 * @author zhanss
 * @since 2022-10-13
 */
@Configuration
public class StudentConfig {

    @Bean
    @Conditional(ConditionalOnStudent.class)
    public Student getStudent() {
        return new Student("zhangsan");
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StudentConfig.class);
        System.out.println(ac.getBean(Student.class));
    }
}