package cn.com.zhanss.springboot.conditional;

import cn.com.zhanss.spring.entity.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 老师配置
 *
 * @author zhanss
 * @since 2022-10-13
 */
@Configuration
public class TeacherConfig {

    @Bean
    public Teacher getTeacher() {
        return new Teacher("zhaolaoshi");
    }
}
