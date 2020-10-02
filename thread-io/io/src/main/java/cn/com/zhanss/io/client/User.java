package cn.com.zhanss.io.client;

import lombok.*;

import java.io.Serializable;

/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2020/9/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -8336978381368983889L;
    /**
     * 用户名
     */
    @NonNull
    private String name;

    /**
     * 密码
     */
    @NonNull
    private String passwor;
}
