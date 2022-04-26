package cn.com.zhanss.datastructure;

import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * 并查集
 *
 * @author zhanss
 * @since 2022-04-07
 */
public class MyUnionSet {

    @Test
    public void StudentFilter() {
        List<Student> stus = new ArrayList<>();
        stus.add(new Student("iden124335jlda", "b12132jl", "github23fasd"));
        stus.add(new Student("iden2sdsd", "b664534", "githut12ljl23"));
        stus.add(new Student("iden12433wedsdjld", "b12132jl", "githut12ljl23"));
        stus.add(new Student("iden124335jlda", "boudanljfa", "githubdada211"));
        UnionSet<Student> studentUnionSet = new UnionSet<>(stus);
        Map<String, Student> identifyId = new HashMap<>();
        Map<String, Student> bId = new HashMap<>();
        Map<String, Student> githubId = new HashMap<>();
        for (Student stu : stus) {
            Student studentIden = identifyId.get(stu.getIdentifyId());
            if (studentIden != null && studentUnionSet.isSamSet(stu, studentIden)) {
                studentUnionSet.union(stu, studentIden);
            } else {
                identifyId.put(stu.getIdentifyId(), stu);
            }
            Student studentBid = bId.get(stu.getBId());
            if (studentBid != null && studentUnionSet.isSamSet(stu, studentBid)) {
                studentUnionSet.union(stu, studentBid);
            } else {
                bId.put(stu.getBId(), stu);
            }
            Student studentGit = githubId.get(stu.getGithubId());
            if (studentGit != null && studentUnionSet.isSamSet(stu, studentGit)) {
                studentUnionSet.union(stu, studentGit);
            } else {
                githubId.put(stu.getGithubId(), stu);
            }
        }
        int size = studentUnionSet.sizeMap.size();
        System.out.println(size);
    }

    @Data
    public static class Student {
        private String identifyId;

        private String bId;

        private String githubId;

        public Student(String identifyId, String bId, String githubId) {
            this.identifyId = identifyId;
            this.bId = bId;
            this.githubId = githubId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return Objects.equals(identifyId, student.identifyId) &&
                    Objects.equals(bId, student.bId) &&
                    Objects.equals(githubId, student.githubId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifyId, bId, githubId);
        }
    }


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
         * 头节点下挂着几个节点，仅 存在代表节点的集合有sizeMap
         */
        private HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

        /**
         * 集合初始化
         * @param values
         */
        public UnionSet(List<V> values) {
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
         * 找指定节点的最顶父节点，且将从指定的node开始到头结点head之间的所有节点，都挂到head下
         * @param node
         * @return 头结点
         */
        private Node<V> findFather(Node<V> node) {
            if (node == null) {
                return null;
            }
            Stack<Node<V>> path = new Stack<>();
            // 找到当前节点cur的代表节点，最顶父节点
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
            sizeMap.remove(less);
            nodes.remove(less);
        }
    }
}
