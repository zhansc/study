package cn.com.zhanss.spring.factory;

import cn.com.zhanss.spring.entity.Person;
import org.springframework.beans.factory.FactoryBean;

/**
 * @desc FactoryBean是spring规定的一个接口，Spring会将当前接口的实现类作为一个工厂，
 *  * 但是在IOC容器启动的时候不会实例化（不管是不是单例模式），只有在使用的时候才会创建对象
 *
 * 此方式是spring创建bean的一种补充，用户可以按需求创建对象，创建的bean交由spring容器进行管理，
 * 无论是否是单例模式，都是在用到的时候创建该对象
 *
 * Person 泛型是规定了当前工厂实例创建的是哪一个或哪一类对象
 * @author zhanshuchan
 * @date 2021/2/18
 */
public class MyFactoryBean implements FactoryBean<Person> {

    /**
     * 返回获取的bean
     *
     */
    @Override
    public Person getObject() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setName("zhangsan");
        person.setDeptNo("ABC123");
        person.setGender("male");
        return person;
    }

    /**
     * 获取返回bean的类型
     *
     */
    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    /**
     * 判断当前bean会否为单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
