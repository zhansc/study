package cn.com.zhanss.wework.javaapi.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 数据库工具
 * @author zhanshuchan
 * @date 2020/11/10
 */
public class DBUtil {

    static List<Person> personList = new ArrayList<>();
    static List<Student> studentList = new ArrayList<>();
    static Person person0 = new Person();
    static Student student0 = new Student();
    /**
     * 数据源
     */
    static {
        person0.setId(111);
        person0.setName("111");
        person0.setGender("male");
        person0.setDeptNo("'2020111'");
        for (int i = 1; i < 10; i ++) {
            Person person = new Person();
            person.setId(i);
            person.setName("name"+ i);
            person.setDeptNo("202011"+ i);
            person.setGender(i % 2 == 0? "male" : "female");
            personList.add(person);
        }

        student0.setId(111);
        student0.setName("name"+ 111);
        student0.setDeptNo("202011"+ 11);
        student0.setGender(111 % 2 == 0? "male" : "female");
        student0.setAge(20+ 111);
        student0.setClassName("杭州市西溪路"+111 +"号");
        student0.setClassName("五年级"+ 111 + "班");
        student0.setSalary(5.0 + 111);
        for (int i = 1; i < 10; i ++) {
            Student student = new Student();
            student.setId(i);
            student.setName("name"+ i);
            student.setDeptNo("202011"+ i);
            student.setGender(i % 2 == 0? "male" : "female");
            student.setAge(20+i);
            student.setClassName("杭州市西溪路"+i +"号");
            student.setClassName("五年级"+ i+ "班");
            student.setSalary(5.0 + i);
            studentList.add(student);
        }
    }

    public static Object list(String sql, Object[] params, Class clazz) {
        if (sql.startsWith("list")) {
            if (clazz.getName().equals("cn.com.zhanss.reflect.Person")) {
                return personList;
            } else if (clazz.getName().equals("cn.com.zhanss.reflect.Student")) {
                return studentList;
            }
        } else {
            if (clazz.getName().equals("cn.com.zhanss.reflect.Person")) {
                return person0;
            } else if (clazz.getName().equals("cn.com.zhanss.reflect.Student")) {
                return student0;
            }
        }
        return null;
    }

}
