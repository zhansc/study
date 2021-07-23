package cn.com.zhanss.entity;
/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2021/5/15
 */
public class User {

    private Integer userId;

    private String userName;

    private Integer gender;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public User(Integer userId, String userName, Integer gender, String password) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                '}';
    }
}
