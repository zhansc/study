package cn.com.zhanss.datastructure.doexercise.lru;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义LRU缓存
 * HashMap + 双向链表
 * https://leetcode-cn.com/problems/lru-cache/
 * @author zhanss
 * @since 2022-05-04
 */
public class CustomLruCache {

    class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;
        public Node(int key, int value, Node pre, Node next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    class DoubleLinkedList {
        /**
         * 最近使用的
         */
        public Node head;
        /**
         * 最久未被使用的
         */
        public Node tail;
        public DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.pre = head;
        }

        /**
         * 从头head插入新节点
         * @param node
         * @return
         */
        public void addFirst(Node node) {

            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
        }

        public int delete(Node node) {
            if (node == null) {
                return -1;
            }
            node.pre.next = node.next;
            node.next.pre = node.pre;
            return node.key;
        }

        /**
         * 从双向链表队尾删除最久未被使用的节点
         * @return
         */
        public int deleteLast() {
            if (head.next == tail) {
                return -1;
            }
            return delete(tail.pre);
        }
    }
    class LRUCache {
        private int capacity;

        private Map<Integer, Node> map;

        private DoubleLinkedList cache;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
            this.capacity = capacity;
            cache = new DoubleLinkedList();
        }

        public LRUCache() {
            map = new HashMap<>(16);
            this.capacity = 16;
            cache = new DoubleLinkedList();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            // 查过一次重新调整位置
            put(key, node.value);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);
            if (map.containsKey(key)) {
                // 已经存在，则先删除再添加到头部
                cache.delete(map.get(key));
            } else {
                if (map.size() == capacity) {
                    int k = cache.deleteLast();
                    map.remove(k);
                }
            }
            map.put(key, node);
            cache.addFirst(node);
        }
    }

    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);

        System.out.println(lruCache.get(1));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

}
