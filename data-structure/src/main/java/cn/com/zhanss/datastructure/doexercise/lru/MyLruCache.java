package cn.com.zhanss.datastructure.doexercise.lru;

public class MyLruCache {
    private class Node {
        private int key;

        private int value;

        private Node pre;

        private Node next;

        public Node() {}
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Node head = new Node();

    private Node tail = new Node();


    public void set(int key, Integer value) {
        Node node = new Node(key, value);
        if (tail.next == null) {
            head.next = node;
            tail.next = node;
            return;
        }
        tail.next.next = node;
        tail.next = node;
    }

    public Node get(int key) {

        return null;
    }
}
