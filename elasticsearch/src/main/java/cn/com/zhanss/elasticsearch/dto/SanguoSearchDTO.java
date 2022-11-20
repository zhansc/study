package cn.com.zhanss.elasticsearch.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 三国
 *
 * @author zhanss
 * @since 2022-08-24
 */
@Data
public class SanguoSearchDTO implements Serializable {

    private static final long serialVersionUID = 4339565663366732022L;
    /**
     * 姓名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;
}
