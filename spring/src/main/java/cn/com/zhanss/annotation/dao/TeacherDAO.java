package cn.com.zhanss.annotation.dao;

import cn.com.zhanss.spring.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * @desc 老师
 * @author zhanshuchan
 * @date 2021/2/19
 */
@Repository
public class TeacherDAO extends BaseDAO<Teacher> {
    @Override
    public void save() {
        System.out.println("保存老师！");
    }
}
