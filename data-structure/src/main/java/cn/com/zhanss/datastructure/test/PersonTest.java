package cn.com.zhanss.datastructure.test;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author zhanshuchan
 * @date 2019/12/8
 */
public class PersonTest {

    @Resource
    private PersonService personService;

    @Test
    public void test1() {
        personService.get(1);
    }
}
