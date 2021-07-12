package com.zhanss.entity;
/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2021/6/28
 */
public class User {

    private Integer id;

    private String name;

    private String gender;

    public User(Integer id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
