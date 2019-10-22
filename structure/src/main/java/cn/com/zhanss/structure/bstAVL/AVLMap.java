package cn.com.zhanss.structure.bstAVL;

import org.junit.Assert;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * AVL字典
 *
 * @author zhanss
 * @since 2019/10/19
 */
public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>> {

    /**
     * 大小
     */
    private int size;

    /**
     * 根节点
     */
    private AVLEntry<K, V> root;

    /**
     * 比较器
     */
    private Comparator<K> comparator;

    /**
     * 非递归方式遍历
     */
    private LinkedList<AVLEntry<K, V>> stack = new LinkedList<AVLEntry<K, V>>();

    /**
     * 比较器：左右子节点和根节点大小
     * @param a
     * @param b
     * @return
     */
    private int compare(K a, K b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            Comparable<K> comparable = (Comparable<K>) a;
            return comparable.compareTo(b);
        }
    }

    public V put(K key, V value) {
        // 根节点为空：直接插入
        if (root == null) {
            root = new AVLEntry<K, V>(key, value);
            // 入栈遍历的路径，做插入平衡调整时需要回溯
            stack.push(root);
            size ++;
        }
        // 指针指向根节点
        AVLEntry<K, V> p = root;
        // 当前节点不为空
        while (p != null) {
            stack.push(p);
            int comp = this.compare(key, p.getKey());
            // 左子树
            if (comp < 0) {
                // 没有左子树，添加左子树
                if (p.left == null) {
                    p.left = new AVLEntry<K, V>(key, value);
                    size ++;
                    stack.push(p.left);
                    break;
                } else {
                    // 有左子树，递归左子树
                    p = p.left;
                }
            } else if (comp == 0) {
                // 碰撞命中，将老的value替换成新的value
                p.setValue(value);
                break;
            // 右子树
            } else {
                // 没有右子树，添加右子树
                if (p.right == null) {
                    p.right = new AVLEntry<K, V>(key, value);
                    size ++;
                    stack.push(p.right);
                    break;
                } else {
                    // 递归右子树
                    p = p.right;
                }
            }
        }
        // 插入之后进行平衡调整
        fixAfterInvertion(key);
        return value;
    }

    /**
     * 根据key获取Entry
     * @param key
     * @return
     */
    private AVLEntry<K, V> getEntry(K key) {
        AVLEntry<K, V> p = root;
        while (p != null) {
            int comp = this.compare(key, p.getKey());
            if (comp < 0) {
                p = p.left;
            } else if (comp == 0) {
                return p;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public V get(K key) {
        AVLEntry<K, V> entry = this.getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    public boolean containsKey(K key) {
        AVLEntry<K, V> entry = this.getEntry(key);
        return entry != null;
    }

    public boolean containsValue(V value) {
        Iterator<AVLEntry<K, V>> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据任意节点获取最小子节点
     * @param entry
     * @return
     */
    public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> entry) {
        AVLEntry<K, V> p = entry;
        if (entry == null) {
            p = root;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> entry) {
        AVLEntry<K, V> p = entry;
        if (entry == null) {
            p = root;
        }
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 递归删除给定二叉树P中对应的节点key
     * @param p
     * @param key
     * @return
     */
    private AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p, K key) {
        if (p == null) {
            return null;
        }
        int comp = this.compare(key, p.getKey());
        if (comp == 0) {
            // 删除当前节点
            if (p.left == null && p.right == null) {
                // 没有子节点，直接置
                p = null;
            } else if (p.left != null && p.right == null) {
                // 只有左子树，用左子树覆盖当前节点
                System.out.println("前："+p.getKey());
                p = p.left;
                System.out.println("后："+p.getKey());
            } else if (p.left == null) {
                // 只有右子树，用右子树覆盖当前节点
                p = p.right;
            } else {
                // 既存在左子树（查找左子树的最大Entry）又存在右子树（查找右子树的最小Entry）
                if ((size & 1) == 0) {
                    // size为偶数，处理左子树
                    AVLEntry<K, V> leftMax = getLastEntry(p.left);
                    // 用左子树最大节点覆盖当前节点
                    p.setKey(leftMax.getKey());
                    p.setValue(leftMax.getValue());
                    // 递归所有左子树：指针向上移动
                    p.left = deleteEntry(p.left, p.getKey());
                } else {
                    // size为奇数，处理右子树
                    AVLEntry<K, V> rightMax = getFirstEntry(p.right);
                    // 用左子树最大节点覆盖当前节点
                    p.setKey(rightMax.getKey());
                    p.setValue(rightMax.getValue());
                    // 递归所有右子树：指针向上移动
                    p.right = deleteEntry(p.right, p.getKey());
                }
            }
        } else if (comp < 0) {
            // 遍历左子树
            p.left = deleteEntry(p.left, key);
        } else {
            // 遍历右子树
            p.right = deleteEntry(p.right, key);
        }
        return p;
    }

    public V remove(K key) {
        AVLEntry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        // 重新覆盖，可能删除的正好是root节点，那么它的引用就变了
        V value = entry.getValue();
        root = deleteEntry(root, key);
        size --;
        return value;
    }

    /**
     * 层序遍历输出二叉树
     */
    public void levelOrder() {
        Queue<AVLEntry<K, V>> queue = new LinkedList<AVLEntry<K, V>>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while (!queue.isEmpty()) {
            preCount --;
            AVLEntry<K, V> p = queue.poll();
            System.out.print(p +" ");
            if (p.left != null) {
                queue.offer(p.left);
                pCount ++;
            }
            if (p.right != null) {
                queue.offer(p.right);
                pCount ++;
            }
            if (preCount == 0) {
                preCount = pCount;
                pCount = 0;
                System.out.println();
            }
        }
    }

    /**
     * 右旋
     * @param p
     * @return
     */
    private AVLEntry<K, V> rotateRight(AVLEntry<K, V> p) {
        AVLEntry<K, V> left = p.left;
        p.left = left.right;
        left.right = p;
        // 获取节点高度
        p.height = Math.max(getHeigth(p.left), getHeigth(p.right)) + 1;
        left.height = Math.max(getHeigth(left.left), p.height) + 1;
        return left;
    }

    /**
     * 左旋
     * @param p
     * @return
     */
    private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> p) {
        AVLEntry<K, V> right = p.right;
        p.right = right.left;
        right.left = p;
        // 获取节点高度
        p.height = Math.max(getHeigth(p.left), getHeigth(p.right)) + 1;
        right.height = Math.max(p.height, getHeigth(right.right)) + 1;
        return right;
    }

    /**
     * 先左旋再右旋
     * @param p
     * @return
     */
    private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> p) {
        p.left = rotateLeft(p.left);
        return rotateRight(p);
    }

    /**
     * 先右旋再左旋
     * @param p
     * @return
     */
    private AVLEntry<K, V> firstRightThenLeft(AVLEntry<K, V> p) {
        p.right = rotateRight(p.right);
        return rotateLeft(p);
    }

    public void fixAfterInvertion(K key) {
        AVLEntry<K, V> p = root;
        while (!stack.isEmpty()) {
            p = stack.pop();
            int newHeight = Math.max(getHeigth(p.left), getHeigth(p.right)) + 1;
            // p 的高度大于 1 且前后的高度一致，则不需要进行调整（仍然是平衡树）
            if (p.height > 1 && newHeight == p.height) {
                stack.clear();
                return;
            }
            p.height = newHeight;
            int balanceFactor = getHeigth(p.left) - getHeigth(p.right);
            // 平衡树
            if (Math.abs(balanceFactor) <= 1) {
                continue;
            } else {
                if (balanceFactor == 2) {
                    // 左子树
                    if (compare(key, p.left.getKey()) < 0) {
                        // 左节点
                        p = rotateRight(p);
                    } else {
                        p = firstLeftThenRight(p);
                    }
                } else {
                    // 右子树
                    if (compare(key, p.right.getKey()) < 0) {
                        p = firstRightThenLeft(p);
                    } else {
                        p = rotateRight(p);
                    }
                }
                // 向上回溯，确认新插入的节点是左子树还是右子树
                if (!stack.isEmpty()) {
                    if (compare(key, stack.peek().getKey()) < 0) {
                        // 左子树
                        stack.peek().left = p;
                    } else {
                        stack.peek().right = p;
                    }
                }
            }
        }
        root = p;
    }

    public void checkBalance() {
        postOrderCheckBalance(root);
    }

    /**
     * 递归后序遍历二叉树，判断是否平衡
     * @param p
     */
    private void postOrderCheckBalance(AVLEntry<K, V> p) {
        if (p != null) {
            postOrderCheckBalance(p.left);
            postOrderCheckBalance(p.right);
            // 断言：p几点的左右子树高度差不大于 1
            Assert.assertTrue(Math.abs(getHeigth(p.left) - getHeigth(p.right)) <= 1);
        }
    }

    public AVLMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public AVLMap() {
    }

    public int getHeigth(AVLEntry<K, V> p) {
        return p != null ? p.height : 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<AVLEntry<K, V>> iterator() {
        return new AVLIterator<K, V>(root);
    }
}
