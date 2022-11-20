package cn.com.zhanss.elasticsearch.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 三国
 *
 * @author zhanss
 * @since 2022-08-24
 */
@Data
public class SearchSanguoVO implements Serializable {

    private static final long serialVersionUID = 5022806854454471069L;
    /**
     * 姓名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

}
