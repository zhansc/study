package cn.com.zhanss.datastructure.tree.bstAVL;

import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 将排序数组转换为平衡二叉树
 *
 * @author zhanss
 * @since 2019/10/23
 */
public class ConvertSortedArrayToBST {

    public TreeNode sortedArrayToBSTWithAVL(int[] nums) {
        if (ObjectUtils.isEmpty(nums)) {
            return null;
        }
        LeetCodeAVL avl = new LeetCodeAVL();
        for (int num : nums) {
            avl.put(num);
        }
        return avl.root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (ObjectUtils.isEmpty(nums)) {
            return null;
        }
        return buildFromSorted(0, nums.length - 1, nums);
    }

    private TreeNode buildFromSorted(int lo, int hi, int[] nums) {
        if (hi < lo) {
            return null;
        }
        // 有序列表的中间值为根节点
        int mid = (lo + hi) >>> 1;
        TreeNode left  = null;
        if (lo < mid) {
            // 递归左子树
            left = buildFromSorted(lo, mid - 1, nums);
        }
        TreeNode middle = new TreeNode(nums[mid]);
        if (left != null) {
            middle.left = left;
        }
        if (mid < hi) {
            // 递归右子树
            TreeNode right = buildFromSorted(mid+1, hi, nums);
            middle.right = right;
        }
        return middle;
    }
}

class LeetCodeAVL {
    /**
     * 大小
     */
    private int size;

    /**
     * 根节点
     */
    public TreeNode root;

    /**
     * 非递归方式遍历
     */
    private LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

    public void put(int key) {
        // 根节点为空：直接插入
        if (root == null) {
            root = new TreeNode(key);
            // 入栈遍历的路径，做插入平衡调整时需要回溯
            stack.push(root);
            size ++;
        }
        // 指针指向根节点
        TreeNode p = root;
        // 当前节点不为空
        while (p != null) {
            stack.push(p);
            int comp = key - p.getValue();
            // 左子树
            if (comp < 0) {
                // 没有左子树，添加左子树
                if (p.left == null) {
                    p.left = new TreeNode(key);
                    size ++;
                    stack.push(p.left);
                    break;
                } else {
                    // 有左子树，递归左子树
                    p = p.left;
                }
            } else if (comp == 0) {
                break;
                // 右子树
            } else {
                // 没有右子树，添加右子树
                if (p.right == null) {
                    p.right = new TreeNode(key);
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
    }

    private Map<TreeNode, Integer> hashHeight = new HashMap<TreeNode, Integer>();

    public int getHeigth(TreeNode p) {
        return hashHeight.containsKey(p) ? hashHeight.get(p) : 0;
    }

    /**
     * 右旋
     * @param p
     * @return
     */
    private TreeNode rotateRight(TreeNode p) {
        TreeNode left = p.left;
        p.left = left.right;
        left.right = p;
        // 获取节点高度
        hashHeight.put(p, Math.max(getHeigth(p.left), getHeigth(p.right)) + 1);
        hashHeight.put(left, Math.max(getHeigth(left.left), hashHeight.get(p)) + 1);
        return left;
    }

    /**
     * 左旋
     * @param p
     * @return
     */
    private TreeNode rotateLeft(TreeNode p) {
        TreeNode right = p.right;
        p.right = right.left;
        right.left = p;
        // 获取节点高度
        hashHeight.put(p, Math.max(getHeigth(p.left), getHeigth(p.right)) + 1);
        hashHeight.put(right, Math.max(hashHeight.get(p), getHeigth(right.right)));
        return right;
    }

    /**
     * 先左旋再右旋
     * @param p
     * @return
     */
    private TreeNode firstLeftThenRight(TreeNode p) {
        p.left = rotateLeft(p.left);
        return rotateRight(p);
    }

    /**
     * 先右旋再左旋
     * @param p
     * @return
     */
    private TreeNode firstRightThenLeft(TreeNode p) {
        p.right = rotateRight(p.right);
        return rotateLeft(p);
    }

    public void fixAfterInvertion(int key) {
        TreeNode p = root;
        while (!stack.isEmpty()) {
            // put中入栈纪录节点路径，出栈就是指针回溯的过程
            p = stack.pop();
            int newHeight = Math.max(getHeigth(p.left), getHeigth(p.right)) + 1;
            // p 的高度大于 1 且前后的高度一致，则不需要进行调整（仍然是平衡树）
            if (hashHeight.containsKey(p) && hashHeight.get(p) > 1 && newHeight == hashHeight.get(p)) {
                stack.clear();
                return;
            }
            hashHeight.put(p, newHeight);
            int balanceFactor = getHeigth(p.left) - getHeigth(p.right);
            // 平衡树
            if (Math.abs(balanceFactor) <= 1) {
                // 继续向上回溯
                continue;
            } else {
                if (balanceFactor == 2) {
                    // 左子树
                    if (key - p.left.getValue() < 0) {
                        // 左节点
                        p = rotateRight(p);
                    } else {
                        p = firstLeftThenRight(p);
                    }
                } else {
                    // 右子树
                    if (key - p.right.getValue() > 0) {
                        // 右节点
                        p = rotateLeft(p);
                    } else {
                        p = firstRightThenLeft(p);
                    }
                }
                // 向上回溯，确认新插入的节点是左子树还是右子树
                if (!stack.isEmpty()) {
                    if (key - stack.peek().getValue() < 0) {
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

    public void levelOrder() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while (!queue.isEmpty()) {
            preCount --;
            TreeNode p = queue.poll();
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

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}

@Data
class TreeNode {
    public Integer value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(Integer value) {
        this.value = value;
    }
}
