package cn.com.zhanss.entity;
/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2021/5/15
 */
public class User {

    private Integer id;

    private String name;

    private Integer gender;

    private String password;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(Integer id, String name, Integer gender, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                '}';
    }

}
