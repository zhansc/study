package cn.com.zhanss.springmvc.converter;

import cn.com.zhanss.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @desc 自定义参数转换器
 * @author zhanshuchan
 * @date 2021/5/15
 */
@Component
public class MyConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        String[] items = source.split("-");
        return new User(Integer.parseInt(items[0]), items[1], Integer.parseInt(items[2]), items[3]);
    }
}
