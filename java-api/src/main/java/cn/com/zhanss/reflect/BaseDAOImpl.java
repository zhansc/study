package cn.com.zhanss.reflect;


import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc
 * @author zhanshuchan
 * @date 2020/11/10
 */
public class BaseDAOImpl {

    public List getRows(String sql, Object[] params, Class clazz)  throws Exception {
        Object object = DBUtil.list(sql, params, clazz);

        List<Person> personList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        Person person0 = new Person();
        Student student0 = new Student();

        Object obj = clazz.newInstance();
        if (object instanceof List) {
            if (clazz.getName().equals("cn.com.zhanss.reflect.Person")) {
                personList = (List<Person>) object;
                if (!CollectionUtils.isEmpty(personList)) {

                }


            } else if (clazz.getName().equals("cn.com.zhanss.reflect.Student")) {
                studentList = (List<Student>) object;
            }
        } else {
            if (clazz.getName().equals("cn.com.zhanss.reflect.Person")) {
                person0 = (Person) object;
            } else if (clazz.getName().equals("cn.com.zhanss.reflect.Student")) {
                student0 = (Student) object;
            }
        }

        return null;
    }

}
