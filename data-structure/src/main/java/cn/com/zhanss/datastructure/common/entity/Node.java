package cn.com.zhanss.datastructure.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @desc Node
 * @author zhanshuchan
 * @date 2022/3/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Node<T> {

    private T value;

    private Node<T> pre;

    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
