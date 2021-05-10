package cn.com.zhanss.spring.entity;
/**
 * @desc 学生
 * @author zhanshuchan
 * @date 2021/02/18
 */
public class Student {

    private Integer id;

    public String name;

    private String gender;

    public String deptNo;

    public Student() {

    }

    public Student(String name, String deptNo) {
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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", deptNo='" + deptNo + '\'' +
                '}';
    }
}
