package cn.com.zhanss.structure.bstAVL;

import cn.com.zhanss.structure.warmup.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器
 *
 * @author zhanss
 * @since 2019/10/19
 */
public class BSTIterator {

    private Iterator<Integer> itr;

    public BSTIterator(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(root, list);
        itr = list.iterator();
    }

    /**
     * 中序遍历递归
     * @param node
     * @param list
     *      线性集合
     */
    private void inOrder(TreeNode node, List<Integer> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.getValue());
            inOrder(node.right, list);
        }
    }

    public boolean hasNext() {
        return itr.hasNext();
    }

    public int next() {
        return itr.next();
    }
}
