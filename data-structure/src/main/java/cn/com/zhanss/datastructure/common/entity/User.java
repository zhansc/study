package cn.com.zhanss.datastructure.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2022/3/15
 */
@Data
@ToString(callSuper=true)
@EqualsAndHashCode
public class User {
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 现居住地址
     */
    private String stayAddress;

    /**
     * 出生地址
     */
    private String bornAddress;

    /**
     * 身份证号
     */
    private String identityId;
}
