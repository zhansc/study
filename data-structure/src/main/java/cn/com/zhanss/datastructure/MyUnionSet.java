package cn.com.zhanss.datastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 并查集
 *
 * @author zhanss
 * @since 2022-04-07
 */
public class MyUnionSet {

    private static class Node<V> {
        private V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class UnionSet<V> {
        /**
         * 值指向的节点
         */
        private HashMap<V, Node<V>> nodes = new HashMap<>();
        /**
         * 指定节点的最顶父节点
         */
        private HashMap<Node<V>, Node<V>> parent = new HashMap<>();
        /**
         * 头节点下挂着几个节点
         */
        private HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

        /**
         * 结合初始化
         * @param values
         */
        public UnionSet(List<V>values) {
            for (V val : values) {
                if (Objects.isNull(val)) {
                    continue;
                }
                Node<V> node = new Node<>(val);
                nodes.put(val, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 找指定节点的最高父节点，且将从指定的node开始到头结点head之间的所有节点，都挂到head下
         * @param node
         * @return 头结点
         */
        private Node<V> findFather(Node<V> node) {
            if (node == null) {
                return null;
            }
            Stack<Node<V>> path = new Stack<>();
            while (node != parent.get(node)) {
                path.push(node);
                node = parent.get(node);
            }
            // 将从指定的node开始到头结点head之间的所有节点，都挂到head下，避免每次向上遍历，减小时间复杂度
            while (!path.isEmpty()) {
                parent.put(path.pop(), node);
            }
            return node;
        }

        /**
         * 判断两个指定值是否在同一个集合中
         * @param a
         * @param b
         * @return
         */
        public boolean isSamSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 合并指定两个值所在的两个集合
         * @param a
         * @param b
         */
        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead == bHead) {
                return;
            }
            Integer aSetSize = sizeMap.get(aHead);
            Integer bSetSize = sizeMap.get(bHead);
            Node<V> more = aSetSize >= bSetSize ? aHead : bHead;
            Node<V> less = more == aHead ? aHead : bHead;
            // 将b节点挂到a节点下
            parent.put(less, more);
            sizeMap.put(more, aSetSize + bSetSize);
            nodes.remove(less);
        }
    }
}
