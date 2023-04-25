package cn.com.zhanss.datastructure.tree;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Node {
    Integer value;

    Node left;

    Node right;

    public Node(){}

    public Node(Integer value) {
        this.value = value;
    }

    public Node left() {
        return this.left;
    }

    public Node right() {
        return this.right;
    }
}