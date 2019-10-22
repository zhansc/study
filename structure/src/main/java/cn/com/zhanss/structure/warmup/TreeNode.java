package cn.com.zhanss.structure.warmup;

import lombok.Data;

/**
 * 树节点
 *
 * @author zhanss
 * @since 2019/10/18
 */
@Data
public class TreeNode {
    int value;

    /**
     * 左子节点
     */
    public TreeNode left;

    /**
     * right
      */
    public TreeNode right;

    TreeNode(int x) { value = x; }


}
