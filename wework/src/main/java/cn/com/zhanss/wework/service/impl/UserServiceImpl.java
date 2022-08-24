package cn.com.zhanss.wework.service.impl;

import cn.com.zhanss.wework.entity.User;
import cn.com.zhanss.wework.service.UserService;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 用户
 *
 * @author zhanss
 * @since 2022-05-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName(User user) {
        return user.getName();
    }

    @Test
    public void test() {
        String str = "https://mp.weixin.qq.com/s/P4j6l6vkNrSc7QrAzZ3XqQ";
        int start = str.indexOf("https://") + 8;
        int end = str.indexOf("?");
        String substring = str.substring(start);
        if (end == -1) {
            end = substring.indexOf("/");
        }
        String substring1 = substring.substring(0, end);
        System.out.println(substring1);
    }
}
