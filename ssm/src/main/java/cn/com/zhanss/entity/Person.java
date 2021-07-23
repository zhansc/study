package cn.com.zhanss.entity;

import java.io.Serializable;

/**
 * @desc 人
 * @author zhanshuchan
 * @date 2021/7/19
 */
public class Person implements Serializable {

    private Integer id;

    private String name;

    private Integer gender;

    private String address;

    private String identityId;

    private String phone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Person(){}

    public Person(Integer id, String name, Integer gender, String address, String identityId, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.identityId = identityId;
        this.phone = phone;
    }
}
