import cn.com.zhanss.entity.Person;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.io.Serializable;

/**
 * @desc 测试
 * @author zhanshuchan
 * @date 2021/7/16
 */
public class JavaTest {

    @Test
    public void testH5() {

        String wecomExternalUserId = "wmGvUXDwAA1yh1-Ix8Luz5rRIxV23lHw";
        Long yzKdtId = 93655678L;

        String md5 = Hashing.md5().newHasher()
                .putString(wecomExternalUserId + "_" + yzKdtId, Charsets.UTF_8)
                .hash().toString();
        System.out.println("md5----->"+ md5);
    }

    @Test
    public void testJSONField() {

        Person person = new Person(1, "小明", 1, "杭州市西湖区", "1234567890098765432", "19012345678");

        InnerUser user = JSONObject.parseObject(JSONObject.toJSONString(person), InnerUser.class);
        System.out.println("user--->"+ user);
    }

}

class InnerUser implements Serializable {

    @JSONField(name = "id")
    private Integer userId;

    @JSONField(name = "name")
    private String userName;

    private Integer gender;

    @JSONField(name = "address")
    private String userAddress;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

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
    public InnerUser(){}

    public InnerUser(Integer userId, String userName, Integer gender, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "InnerUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", userAddress=" + userAddress +
                '}';
    }
}

