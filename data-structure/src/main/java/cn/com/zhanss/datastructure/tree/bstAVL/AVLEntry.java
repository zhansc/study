package cn.com.zhanss.datastructure.tree.bstAVL;

import java.util.Map;

/**
 * AVL节点
 *
 * @author zhanss
 * @since 2019/10/19
 */
public class AVLEntry<K, V> implements Map.Entry<K, V> {

    private K key;

    private V value;

    public AVLEntry<K, V> left;

    public AVLEntry<K, V> right;

    public int height;

    @Override
    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return this.value;
    }

    public AVLEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public AVLEntry(){}

    public AVLEntry(K key, V value, AVLEntry<K, V> left, AVLEntry<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLEntry{" +
                "key=" + key +
                ", value=" + value +
                ", left=" + left +
                ", right=" + right +
                ", height=" + height +
                '}';
    }
}
