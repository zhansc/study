package cn.com.zhanss.wework.javaapi.aes;

import cn.com.zhanss.wework.entity.Person;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;

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

        Person person = Person.builder().id(1).name("小明").build();

        InnerUser user = JSONObject.parseObject(JSONObject.toJSONString(person), InnerUser.class);
        System.out.println("user--->"+ user);
    }

}


