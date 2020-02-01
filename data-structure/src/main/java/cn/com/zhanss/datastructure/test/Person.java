package cn.com.zhanss.datastructure.test;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhanshuchan
 * @date 2019/12/8
 */
@Data
@Builder
public class Person {
    private int id;

    private int age;

    private String name;

    private String gender;

}
