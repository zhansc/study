package cn.com.zhanss.wework.javaapi.aes;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @desc H5
 * @author zhanshuchan
 * @date 2021/7/21
 */
public class H5Test {

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
    public void test() {

        InnerUser innerUser = new InnerUser();
        System.out.println(innerUser);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class InnerUser {
        Integer id;

        boolean hasNext;
    }

}
