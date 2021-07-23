package cn.com.zhanss.wework.javaapi.reflect;
/**
 * @desc  
 * @author zhanshuchan
 * @date 2020/11/8
 */
public class Person {

    private Integer id;

    public String name;

    private String gender;

    public String deptNo;

    public Person() {

    }

    public Person (String name, String deptNo) {
        this.name = name;
        this.deptNo = deptNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", deptNo='" + deptNo + '\'' +
                '}';
    }
}
