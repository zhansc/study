package cn.com.zhanss.wework.javaapi.reflect;

/**
 * @desc 学生
 * @author zhanshuchan
 * @date 2020/11/8
 */
public class Student extends Person {

    public String className;

    public Integer age;

    private String address;

    public String grade;

    public Double salary;

    public Student(){}

    public Student(String className, int age, String address) {
        this.className = className;
        this.age = age;
        this.address = address;
    }

    public Student(String className, Integer age, String address, String grade, Double salary) {
        this.className = className;
        this.age = age;
        this.address = address;
        this.grade = grade;
        this.salary = salary;
    }

    public Student(String name, String deptNo, String className, Integer age, String address, String grade, Double salary) {
        super(name, deptNo);
        this.className = className;
        this.age = age;
        this.address = address;
        this.grade = grade;
        this.salary = salary;
    }

    private Student(int age, String address) {
        this.age = age;
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "className='" + className + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", grade='" + grade + '\'' +
                ", salary=" + salary +
                '}';
    }

    private void add(int a, int b) {
        System.out.println(a + b);
    }
}
