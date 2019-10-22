package cn.com.zhanss.structure.bstAVL;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * 平衡树迭代器
 *
 * @author zhanss
 * @since 2019/10/20
 */
public class AVLIterator<K, V> implements Iterator<AVLEntry<K, V>> {

    private Stack<AVLEntry<K, V>> stack;

    AVLIterator(AVLEntry<K, V> root) {
        stack = new Stack<>();
        addLeftPath(root);
    }

    private void addLeftPath(AVLEntry<K, V> p) {
        while (p != null) {
            // 左路径入栈
            stack.push(p);
            p = p.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public AVLEntry<K, V> next() {
        AVLEntry<K, V> p = stack.pop();
        // 右路径入栈
        addLeftPath(p.right);
        return p;
    }

    @Override
    public void remove() {
        throw new ConcurrentModificationException("Can not remove!");
    }
}
