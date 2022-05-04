package cn.com.zhanss.datastructure.array;

import cn.com.zhanss.datastructure.doexercise.random.Rand2Rand;
import cn.com.zhanss.datastructure.heap.MyHeap;
import cn.com.zhanss.datastructure.sort.ArraySort;
import org.junit.Test;

import java.util.*;

/**
 * 数组练习
 *
 * @author zhanss
 * @since 2022-04-15
 */
public class ArrayExercise {

    @Test
    public void testSumArrays() {
        Rand2Rand rand2Rand = new Rand2Rand();
        int[] arr1 = rand2Rand.lenRandomValueRandom(8, 20);
        print(arr1);
        int[] arr2 = rand2Rand.lenRandomValueRandom(12, 10);
        print(arr2);
        int[] sumArr = sumArrays(arr1, arr2);
        print(sumArr);

        List<List<Integer>> sumsSort = threeSumBySort(new int[]{-1,0,1,2,-1,-4});
        System.out.println(sumsSort);

        int[] nums = new int[]{2,0,1};
        sortColors(nums, 0 , nums.length - 1);
        print(nums);

        int[][] intervals = new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}};
        int[][] outs = merge(intervals);

        List<Integer> rows = getRow(4);

        int[][] rotate = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        // 二维矩阵旋转90度
        rotate(rotate);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("b");
        list.set(1, "bb");

        int[] findKthLargest = new int[]{3,2,1,5,6,4};
        int kthLargest = findKthLargest(findKthLargest, 3);
        System.out.println("----kthLargest1------>"+ kthLargest);

        kthLargest = findKthLargestV1(findKthLargest, 2);
        System.out.println("----kthLargest2------>"+ kthLargest);


        System.out.println("----end------");
    }

    /**
     * 利用大根堆排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k || k < 1) {
            return -1;
        }
        MyHeap heap = new MyHeap(nums.length);
        for (int num : nums) {
            heap.heapInsert(num);
        }
        int res = -1;
        while (k > 0) {
            res = heap.pop();
            k --;
        }
        return res;
    }

    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k || k < 1) {
            return -1;
        }
        if (nums.length == 1 && k == 1) {
            return nums[0];
        }
        int i = 0, j;
        for (; i < nums.length; i ++) {
            for (j = i + 1; j < nums.length; j ++) {
                // 选择排序：倒序排
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                }
            }
            if (i == (k-1)) {
                return nums[i];
            }
        }
        return -1;
    }

    public void print(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
        System.out.println();
    }

    /**
     * 求两个数组的和数组
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] sumArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }
        int minLen = arr1.length;
        int maxLen = arr2.length;
        if (arr1.length > arr2.length) {
            minLen = arr2.length;
            maxLen = arr1.length;
        }
        int[] sumArr = new int[maxLen];
        for (int i = 0; i < minLen; i ++) {
            sumArr[i] = arr1[i] + arr2[i];
        }
        int j = minLen;
        while (arr1.length > minLen && j < arr1.length) {
            sumArr[j] = arr1[j];
            j ++;
        }
        while (arr2.length > minLen && j < arr2.length) {
            sumArr[j] = arr2[j];
            j ++;
        }
        return sumArr;
    }

    /**
     * 求数组中a + b + c = 0 不重复的a/b/c数组
     * nums = [-1,0,1,2,-1,-4]
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumBySort(int[] nums) {
        List<List<Integer>> sums = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return sums;
        }
        // [-1,-1,-4,2,1,1]
        ArraySort arraySort = new ArraySort();
        // 先排序
        arraySort.processMerge(nums, 0, nums.length - 1);
        int temp;
        Map<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i ++) {
            for (int j = i + 1; j < nums.length - 1; j ++) {
                List<Integer> sum = new ArrayList<>();
                temp = nums[i] + nums[j];
                int abs = 0 - temp;
                if (findC(nums, j + 1, abs)) {
                    String cacheKey = "" + nums[i] + nums[j] + abs;
                    if (cache.containsKey(cacheKey)) {
                        // 去重
                        continue;
                    }
                    cache.put(cacheKey, 1);
                    sum.add(nums[i]);
                    sum.add(nums[j]);
                    sum.add(abs);
                    sums.add(sum);
                }
            }
        }
        return sums;
    }

    private boolean findC(int[] nums, int left, int abs) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        for (int i = left; i < nums.length; i ++) {
            if (nums[i] == abs) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色
     * nums = [2,0,2,1,1,0]
     * @param nums
     */
    public void sortColors(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int less = left - 1;
        int more = right + 1;
        int random = /*randomFromArray(nums, right)*/nums[right];
        int index = left;
        while (index < more) {
            if (nums[index] < random) {
                swap(nums, index ++, ++ less);
            } else if (nums[index] > random) {
                swap(nums, index, -- more);
            } else {
                ++ index;
            }
        }
        sortColors(nums, left, less);
        sortColors(nums, more, right);
    }

    public void swap(int[] arr, int arrIndex, int numIndex) {
        int temp = arr[arrIndex];
        arr[arrIndex] = arr[numIndex];
        arr[numIndex] = temp;
    }

    private int randomFromArray(int[] nums, int length) {
        int random = (int) (Math.random() * length);
        return nums[random];
    }

    /**
     * 合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]   {{1,4},{0,0}} {{1,4},{1,2}}
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 先将区间按照左侧数字排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            // 遍历区间，当前区间的左侧值大于merged区间中最后一个区间的右侧值则，没有重合直接添加
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                // 有重复则，取最大值
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * 输入: rowIndex = 3
     * 输出: [1,3,3,1]
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> rows = new ArrayList<>();
        if (rowIndex == 0) {
            rows.add(1);
            return rows;
        }
        if (rowIndex == 1) {
            rows.add(1);
            rows.add(1);
            return rows;
        }
        int count = 0;
        Map<Integer, List<Integer>> cache = new HashMap<>();
        do {
            if (cache.isEmpty()) {
                List<Integer> floor = new ArrayList<>();
                floor.add(1);
                cache.put(count, floor);
            } else {
                List<Integer> floor = cache.get(count - 1);
                int pre = 0;
                int value;
                int length = floor.size();
                for (int i = 0; i < length; i ++) {
                    value = pre + floor.get(i);
                    pre = floor.get(i);
                    floor.set(i, value);
                }
                floor.add(1);
                cache.put(count, floor);
                cache.remove(count - 1);
            }
            count ++;
        } while (count <= rowIndex);
        return cache.get(count - 1);
    }

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     * 链接：https://leetcode-cn.com/problems/rotate-image
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || (matrix.length == 1 && matrix[1].length == 1)) {
            return;
        }
        int length = matrix.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i --) {
            for (int k = 0; k < length; k ++) {
                stack.add(matrix[k][i]);
            }
        }
        for (int i = 0; i < length; i ++) {
            for (int j = 0; j < length; j ++) {
                matrix[i][j] = stack.pop();
            }
        }
    }

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return null;
        }
        if (n == 1) {
            return new int[][]{{1}};
        }
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i ++) {

        }
        return matrix;
    }

}
