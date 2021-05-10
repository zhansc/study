package cn.com.zhanss.annotation.dao;

import cn.com.zhanss.spring.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @desc 学生
 * @author zhanshuchan
 * @date 2021/2/19
 */
@Repository
public class StudentDAO extends BaseDAO<Student> {
    @Override
    public void save() {
        System.out.println("保存学生！");
    }
}
