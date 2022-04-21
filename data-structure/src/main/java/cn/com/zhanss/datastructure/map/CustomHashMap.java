package cn.com.zhanss.datastructure.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 自定义HashMap
 * HashMap基础结构：数组+链表+红黑树 可以运用在部门结构设计上
 *
 * @author zhanss
 * @since 2022-04-20
 */
public class CustomHashMap<K, V> implements Map<K, V> {
    /**
     * 数组容量默认大小 16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 数组最大值
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 数组
     */
    private Node<K, V>[] table;
    /**
     * 数组大小
     */
    private int size;
    /**
     * map容量大小
     */
    private int capacity;
    /**
     * 负载因子
     */
    private float loadFactor;
    /**
     * 下次扩容的阈值
     */
    private int threshold;
    /**
     * 修改计数器，检测遍历HashMap时其他的增删操作
     */
    private volatile int modCount;

    public CustomHashMap() {
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = (int) (this.capacity * this.loadFactor);
    }

    public CustomHashMap(int capacity){
        this.capacity = tableSizeFor(capacity);
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = (int) (this.capacity * this.loadFactor);
    }

    public CustomHashMap(int capacity, float loadFactor) {
        this.capacity = tableSizeFor(capacity);
        this.loadFactor = loadFactor < 0.0f || Float.isNaN(loadFactor) ? DEFAULT_LOAD_FACTOR : loadFactor;
        this.threshold = (int) (this.capacity * this.loadFactor);
    }

    private static class Node<K, V> {
        /**
         * 节点中的key值
         */
        private K key;
        /**
         * 节点中的value值
         */
        private V value;
        /**
         * 节点key的hash值
         */
        private int hash;
        /**
         * 节点的后置节点
         */
        private Node<K, V> next;

        public Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return getNode(key);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            System.out.println("HashMap的key值不能为null");
            return value;
        }
        putVal(key, value);
        return value;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * // 简易版的HashMap，没有红黑树
     * @param key
     * @param value
     * @return
     */
    private boolean putVal(K key, V value) {
        // 对key进行hash
        int hash = hash(key);
        // 计算hash值在数组的索引位置
        int index = hash & (capacity - 1);
        Node<K, V> p;
        if (table == null || table.length == 0) {
            // 初始化
            resize();
        }
        Node<K, V> newNode = newNode(key, value, hash, null);
        if ((p = table[index]) == null) {
            table[index] = newNode;
        } else if (p.hash == hash && (p.key == key || key.equals(p.key))) {
            // hash冲突，且key地址相等或key值相等，则key重复直接覆盖
            p = newNode;
        } else {
            // 节点key值不相等，则增加链表
            p.next = newNode;
        }
        ++ size;
        if (size > threshold) {
            resize();
        }
        // 修改加1
        ++ modCount;
        return true;
    }

    private void resize() {
        if (table == null || table.length == 0) {
            // 初始化
            table = (Node<K,V>[])new Node[capacity];
        } else {
            int oldCapacity = capacity;
            // capacity容量扩大两倍
            capacity <<= 1;
            threshold = (int) (capacity * loadFactor);
            // 节点元素重新计算hash
            Node<K, V> tempNode;
            Node<K, V>[] newTable = (Node<K,V>[])new Node[capacity];
            for (Node<K, V> aTable : table) {
                tempNode = aTable;
                if (tempNode == null) {
                    continue;
                }
                int hash = hash(tempNode.key);
                if (tempNode.next == null) {
                    newTable[hash & (oldCapacity - 1)] = tempNode;
                }
                Node<K, V> next;
                do {
                    next = tempNode;
                    int linkedHash = hash(next.key);
                    int linkedIndex = hash & (oldCapacity - 1);
                    if ((linkedHash & oldCapacity) == 0) {
                        // 数组索引位置不变
                    } else {
                        // 数组索引位置为原来index += oldCapacity
                        linkedIndex += oldCapacity;
                    }
                    next.next = null;
                    newTable[linkedIndex] = next;
                    tempNode = tempNode.next;
                } while (tempNode != null);
            }
            table = newTable;
        }
    }

    /**
     * 取指>=定值的最小的2^m那个数
     * @param capacity
     * @return
     */
    private int tableSizeFor(int capacity) {
        // capacity-1是为了避免capacity=2^m，经过下面计算后变成原值扩大了两倍
        int cap = capacity - 1;
        // 目的是将cap二进制数的最高的1填满所有的后置位
        cap |= cap >>> 1;
        cap |= cap >>> 2;
        cap |= cap >>> 4;
        cap |= cap >>> 8;
        cap |= cap >>> 16;
        // 满1的后置位加1后则进1,
        return cap < 0 ? 1 : cap >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : (cap + 1);
    }

    private int hash(Object key) {
        int hashCode = key.hashCode();
        return (hashCode >>> 16) ^ hashCode;
    }

    private Node newNode(K key, V value, int hash, Node<K, V> next) {
        return new Node<>(key, value, hash, next);
    }

    private V getNode(Object key) {
        if (key == null) {
            return null;
        }
        int exceptModeCount = modCount;
        int hash = hash(key);
        int index = hash & (capacity - 1);
        Node<K, V> temp = table[index];
        if (temp == null) {
            return null;
        }
        do {
            if (key.hashCode() == temp.key.hashCode() || key.equals(temp.key)) {
                if (exceptModeCount != modCount) {
                    throw new RuntimeException("ConcurrentModificationException...");
                }
                return temp.value;
            }
            temp = temp.next;
        } while (temp != null);
        return null;
    }
}
