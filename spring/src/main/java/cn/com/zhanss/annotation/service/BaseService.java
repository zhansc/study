package cn.com.zhanss.annotation.service;

import cn.com.zhanss.annotation.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc 服务
 * @author zhanshuchan
 * @date 2021/2/19
 */
public class BaseService<T> {

    @Autowired
    private BaseDAO<T> baseDAO;

    public void save() {
        baseDAO.save();
    }

}
