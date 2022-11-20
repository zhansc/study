import cn.com.zhanss.annotation.proxy.CalculatorProxy;
import cn.com.zhanss.annotation.service.Calculator;
import cn.com.zhanss.annotation.service.MyCalculator;
import cn.com.zhanss.annotation.service.TeacherServiceImpl;
import cn.com.zhanss.spring.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @desc 测试类
 * @author zhanshuchan
 * @date 2021/2/18
 */
public class SpringTest {

    @Autowired
    private Calculator calculator;

    /**
     * 创建bean工厂
     */
    ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
    @Test
    public void test() {

        Person person = context.getBean("myFactoryBean", Person.class);
        System.out.println("person--->"+person);

        System.out.println(new Date());

        TeacherServiceImpl teacherServiceImpl = context.getBean("teacherServiceImpl", TeacherServiceImpl.class);
        teacherServiceImpl.save();

    }

    @Test
    public void test1() {
        Calculator proxy = CalculatorProxy.getProxy(new MyCalculator());
        System.out.println("proxy---->"+ proxy.add(1, 1));
        System.out.println("↑自定义aop=========================================");

        System.out.println("↓springaop-aop=========================================");
        calculator = context.getBean("myCalculator", Calculator.class);
        calculator.add(3, 1);

//        calculator.div(3, 0);

        String str1 = null + "222";
        System.out.println(str1.equals("null222"));

    }


}