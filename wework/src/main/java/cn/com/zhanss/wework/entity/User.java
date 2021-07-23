package cn.com.zhanss.wework.entity;

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

}
