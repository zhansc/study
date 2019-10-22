package cn.com.zhanss.structure.bstAVL;

import cn.com.zhanss.structure.warmup.TreeNode;

/**
 * 中序遍历的后续、前驱节点
 *
 * @author zhanss
 * @since 2019/10/21
 */
public class InOrderSuccessorInBST {

    /**
     * 后续节点
     * @param root
     * @param p
     * @return
     */
    public TreeNode inOrderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (getLastEntry(root) == p) {
            return null;
        }
        if (p.right != null) {
            return getFirstEntry(p.right);
        }

        TreeNode parent = root;
        TreeNode temp = root;
        while (parent != null) {
            if (p.getValue() < parent.getValue()) {
                temp = parent;
                parent = parent.left;
            } else {
                parent = parent.right;
            }
        }
        return temp;
    }

    private TreeNode getFirstEntry(TreeNode p) {
        if (p == null) {
            return null;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    private TreeNode getLastEntry(TreeNode p) {
        if (p == null) {
            return null;
        }
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

}
