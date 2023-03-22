package cn.com.zhanss.datastructure.doexercise.lru;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCahche
 */
public class DoubleLinkLruCache {
    private class Node {
        private int key;
        private int value;
        private Node pre;
        private Node next;

        private Node() {}
        private Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    class LruCache {
        private Integer capacity;
        private Map<Integer, Node> map;
        private DoubleLinkList cache;

        public LruCache() {
            this.capacity = 16;
            map = new HashMap<>(16);
            cache = new DoubleLinkList();
        }

        public LruCache(Integer capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            cache = new DoubleLinkList();
        }

        public Node set(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                cache.swap(node);
                return node;
            }
            if (map.size() >= capacity) {
                cache.remove();
            }
            Node node = new Node(key, value);
            map.put(key, node);
            cache.addFirst(node);
            return node;
        }

        /**
         *
         * @param key
         * @return value
         */
        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                cache.swap(node);
                return node.value;
            }
            return -1;
        }
    }

    @Test
    public void testLruCache() {
        LruCache lruCache = new LruCache();
        lruCache.set(1, 1);
        lruCache.set(2, 2);
        lruCache.set(3, 3);
        lruCache.set(4, 4);
        lruCache.set(5, 5);
        lruCache.set(6, 6);

        System.out.println("lruCache get(6)---"+ lruCache.get(6));
        System.out.println("lruCache get(2)---"+ lruCache.get(2));
        System.out.println("lruCache get(1)---"+ lruCache.get(1));
        System.out.println("lruCache get(1)---"+ lruCache.get(1));
        System.out.println("lruCache get(5)---"+ lruCache.get(5));
        System.out.println("lruCache get(5)---"+ lruCache.get(5));
        System.out.println("lruCache get(9)---"+ lruCache.get(9));

        System.out.println("lruCache end");
    }

    class DoubleLinkList {
        /**
         * 头指针
         */
        private Node head = new Node();
        /**
         * 尾指针
         */
        private Node tail = new Node();

        public DoubleLinkList(){}
        /**
         * 时间复杂度是O(1)
         * 从头开始添加
         * @param node
         */
        public void addFirst(Node node) {
            if (head.next == null) {
                head.next = node;
                tail.next = node;
                return;
            }
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        /**
         * 时间复杂度O(n)
         * 默认删除最近最少使用的一个
         * @return
         */
        public void remove() {
            tail.next = tail.next.pre;
            tail.next.next = null;
        }

        private void swap(Node temp) {
            if (temp == null || temp.pre == null) {
                return;
            }
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            } else {
                tail.next = tail.next.pre;
            }

            temp.next = head.next;
            head.next.pre = temp;
            head.next = temp;
        }
    }

}
