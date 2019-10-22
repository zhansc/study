package cn.com.zhanss.structure.bstAVL;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author zhanss
 * @since 2019/10/21
 */
public class TestBST {

    private Random random = new Random();

    @Test
    public void testIterator() {

        int maxSize = 16;
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        for (int i = 0; i < maxSize; i ++) {
            avlMap.put(random.nextInt(maxSize), random.nextInt(maxSize) + "");
        }

        for (AVLEntry<Integer, String> anAvlMap : avlMap) {
            System.out.println(anAvlMap);
        }
    }

    @Test
    public void testIteratorWithJDK() {
        int maxSize = 65536;
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < maxSize; i ++) {
            int key = random.nextInt(maxSize);
            String value = random.nextInt(maxSize) + "";
            avlMap.put(key, value);
            treeMap.put(key, value);
        }

        Assert.assertEquals(avlMap.size(), treeMap.size());
        System.out.println(avlMap.size());

        Iterator<AVLEntry<Integer, String>> avlEntryIterator = avlMap.iterator();
        Iterator<Map.Entry<Integer, String>> entryIterator = treeMap.entrySet().iterator();
        while (avlEntryIterator.hasNext() && entryIterator.hasNext()) {
            Assert.assertEquals(avlEntryIterator.next().getKey(), entryIterator.next().getKey());
        }

    }

    @Test
    public void testQuery() {
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        avlMap.put(1, "a");
        avlMap.put(2, "b");
        avlMap.put(3, "c");
        avlMap.put(4, "d");
        avlMap.put(5, "e");
        avlMap.put(2, "bb");
        avlMap.put(6, "f");
        avlMap.put(7, "g");

        System.out.println(avlMap.get(2));
        Assert.assertEquals(avlMap.get(3), "c");
        Assert.assertTrue(!avlMap.isEmpty());

        System.out.println("最小值："+ avlMap.getFirstEntry(null));
        System.out.println("最大值："+ avlMap.getLastEntry(null));
    }

    @Test
    public void testQueryWithJDK() {
        int maxSize = 255;
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        int i = 0;
        while (i++ < maxSize) {
            int key = random.nextInt(maxSize);
            String value = random.nextInt(maxSize) + "";
            avlMap.put(key, value);
            treeMap.put(key, value);
        }
        Assert.assertEquals(avlMap.getFirstEntry(null).getKey(), treeMap.firstKey());
        Assert.assertEquals(avlMap.getLastEntry(null).getKey(), treeMap.lastKey());
    }

    @Test
    public void testRemove() {
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        int[] keys = {5, 3, 7, 2, 4, 6, 9, 1, 8, 10};
        for (int key : keys) {
            avlMap.put(key, key + "");
        }
        System.out.println("删除："+ avlMap.remove(2));
        avlMap.levelOrder();
        Iterator<AVLEntry<Integer, String>> iterator = avlMap.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getKey()+ " ");
        }
        System.out.println();
    }

    @Test
    public void testPerson() {

        AVLMap<Person, String> avlMap = new AVLMap<Person, String>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        int maxSize = 255;
        for (int i = 0; i < maxSize; i ++) {
            int ran = random.nextInt(maxSize);
            avlMap.put(new Person(ran, "name"+ ran), "value"+ ran);
        }
        Iterator<AVLEntry<Person, String>> iterator = avlMap.iterator();
        while (iterator.hasNext()) {
            AVLEntry<Person, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println(avlMap.get(entry.getKey()));
            System.out.println("========================");
        }
    }

    @Test
    public void testSortInt() {
        Integer[] arr = {2,45, 6, 12, 21, 34, 90, 11};
        SortWithGeneric sort = new SortWithGeneric();
        Integer[] sortArr = sort.sort(arr);
        for (int i = 0; i < sortArr.length; i ++) {
            System.out.println(sortArr[i]);
        }
    }

    @Test
    public void testSortStr() {
        String[] arr = {"dw","f", "dj", "az", "ed", "i", "m", "w"};
        SortWithGeneric sort = new SortWithGeneric();
        String[] sortArr = sort.sort(arr);
        for (int i = 0; i < sortArr.length; i ++) {
            System.out.println(sortArr[i]);
        }
    }

}
