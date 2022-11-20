package cn.com.zhanss.spring.entity;

/**
 * 班长
 *
 * @author zhanss
 * @since 2022-10-13
 */
public class Monitor {

    /**
     * 是班长
     */
    private boolean flag = true;

    private Integer id;

    public String name;

    private String gender;

    public String deptNo;

    public Monitor() {
    }

    public Monitor(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
}
