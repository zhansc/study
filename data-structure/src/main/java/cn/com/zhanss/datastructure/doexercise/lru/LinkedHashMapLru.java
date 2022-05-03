package cn.com.zhanss.datastructure.doexercise.lru;
import org.junit.Test;

import java.util.*;

/**
 * 数组时间LRU缓存
 *
 * @author zhanss
 * @since 2022-05-02
 */
public class LinkedHashMapLru {

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
    }

    class LRUCache extends LinkedHashMap {

        private Integer capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return (int) super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        public boolean removeEldestEntry(Map.Entry eldest) {
            return size() > capacity;
        }
    }

}
