package cn.com.zhanss.spring.entity;
/**
 * @desc
 * @author zhanshuchan
 * @date 2021/02/18
 */
public class Person {

    private Integer id;

    private String name;

    private String gender;

    private String deptNo;

    public Person() {

    }

    public Person(String name, String deptNo) {
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
