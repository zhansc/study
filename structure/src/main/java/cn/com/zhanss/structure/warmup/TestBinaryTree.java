package cn.com.zhanss.structure.warmup;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author zhanss
 * @since 2019/10/18
 */
public class TestBinaryTree {

    @Test
    public void invertBinaryTree(TreeNode treeNode) {
        // 前序遍历
        if(treeNode != null) {
            TreeNode node = treeNode.getLeft();
            treeNode.setLeft(treeNode.getRight());
            treeNode.setRight(node);
            invertBinaryTree(treeNode.getLeft());
            invertBinaryTree(treeNode.getRight());
        } else {
            return;
        }

        // 中序遍历
        if(treeNode != null) {
            invertBinaryTree(treeNode.getLeft());
            TreeNode node = treeNode.getLeft();
            treeNode.setLeft(treeNode.getRight());
            treeNode.setRight(node);
            // 经过递归调用后原来的右子树变成了现在的左子树
            invertBinaryTree(treeNode.getLeft());
        } else {
            return;
        }

        // 后序遍历
        if(treeNode != null) {
            invertBinaryTree(treeNode.getLeft());
            invertBinaryTree(treeNode.getRight());
            TreeNode node = treeNode.getLeft();
            treeNode.setLeft(treeNode.getRight());
            treeNode.setRight(node);
        } else {
            return;
        }

        // 层序遍历
        if(treeNode == null){
            return;
        } else {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(treeNode);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode t = node.getLeft();
                node.setLeft(node.getRight());
                node.setRight(t);

                // 将左右子树压入队列
                if(node.getLeft() != null){
                    queue.offer(node.getLeft());
                }
                if(node.getRight() != null){
                    queue.offer(node.getRight());
                }
            }
        }
        return;
    }

}
