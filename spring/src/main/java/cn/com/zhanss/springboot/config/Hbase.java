package cn.com.zhanss.springboot.config;

import lombok.Data;

/**
 * Hbase
 *
 * @author zhanss
 * @since 2022-04-16
 */
@Data
public class Hbase {

    private String ip;

    private String port;

    private String maxSize;
}
