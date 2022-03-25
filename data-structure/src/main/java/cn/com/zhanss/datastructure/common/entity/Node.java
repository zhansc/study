package cn.com.zhanss.datastructure.common.entity;

import lombok.Data;

/**
 * @desc Node
 * @author zhanshuchan
 * @date 2022/3/2
 */
@Data
public class Node {

    private Integer value;

    private Node last;

    private Node next;
}
