package cn.com.zhanss.spring.entity;
/**
 * @desc  
 * @author zhanshuchan
 * @date 2021/2/19
 */
public class Teacher {

    private Integer id;

    public String name;

    public Teacher(){}

    public Teacher(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
