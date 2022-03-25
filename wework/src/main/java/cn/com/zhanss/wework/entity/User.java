package cn.com.zhanss.wework.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2021/7/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String gender;

    private String phone;

    @JSONField(name = "age")
    private Integer age_j;

    @JSONField(name = "age")
    private Integer age;

    /**
     * 现居住地址
     */
    private String stayAddress;

    /**
     * 出生地址
     */
    @JSONField(name="born")
    private String bornAddress;

    /**
     * 身份证号
     */
    private String identityId;

}
