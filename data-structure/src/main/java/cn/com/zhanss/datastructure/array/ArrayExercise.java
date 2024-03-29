package cn.com.zhanss.datastructure.array;

import cn.com.zhanss.datastructure.doexercise.random.Rand2Rand;
import cn.com.zhanss.datastructure.heap.MyHeap;
import lombok.*;
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
        int[] arr1 = Rand2Rand.lenRandomValueRandom(8, 20);
        print(arr1);
        int[] arr2 = Rand2Rand.lenRandomValueRandom(12, 10);
        print(arr2);
        int[] sumArr = sumArrays(arr1, arr2);
        print(sumArr);

        int[] threeSumBySort = new int[]{0};
        List<List<Integer>> sumsSort = threeSumBySort(threeSumBySort);
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
        if (nums == null || nums.length < k || k < 1) {
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
     * a + b + c = 0
     * 排序 + 双指针
     * https://leetcode-cn.com/problems/3sum/
     * 时间复杂度O(N^2)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        // 先排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 数组是有序的，就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 求数组中a + b + c = 0 不重复的a/b/c数组
     * nums = [-1,0,1,2,-1,-4]
     *
     * https://leetcode-cn.com/problems/3sum/
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

    /**
        给你一个长度为 n 的数组 words ，该数组由 非空 字符串组成。
        定义字符串 word 的 分数 等于以 word 作为 前缀 的 words[i] 的数目。
        例如，如果 words = ["a", "ab", "abc", "cab"] ，那么 "ab" 的分数是 2 ，因为 "ab" 是 "ab" 和 "abc" 的一个前缀。
        返回一个长度为 n 的数组 answer ，其中 answer[i] 是 words[i] 的每个非空前缀的分数 总和 。
        注意：字符串视作它自身的一个前缀。

        输入：words = ["abc","ab","bc","b"]
        输出：[5,4,3,2]
        解释：对应每个字符串的答案如下：
        - "abc" 有 3 个前缀："a"、"ab" 和 "abc" 。
        - 2 个字符串的前缀为 "a" ，2 个字符串的前缀为 "ab" ，1 个字符串的前缀为 "abc" 。
        总计 answer[0] = 2 + 2 + 1 = 5 。
        - "ab" 有 2 个前缀："a" 和 "ab" 。
        - 2 个字符串的前缀为 "a" ，2 个字符串的前缀为 "ab" 。
        总计 answer[1] = 2 + 2 = 4 。
        - "bc" 有 2 个前缀："b" 和 "bc" 。
        - 2 个字符串的前缀为 "b" ，1 个字符串的前缀为 "bc" 。
        总计 answer[2] = 2 + 1 = 3 。
        - "b" 有 1 个前缀："b"。
        - 2 个字符串的前缀为 "b" 。
        总计 answer[3] = 2 。

        输入：words = ["abcd"]
        输出：[4]
        解释：
        "abcd" 有 4 个前缀 "a"、"ab"、"abc" 和 "abcd"。
        每个前缀的分数都是 1 ，总计 answer[0] = 1 + 1 + 1 + 1 = 4 。

        链接：https://leetcode.cn/problems/sum-of-prefix-scores-of-strings
     */
    @Test
    public void test() {
        String[] words = new String[]{"abcd"};
        String[] names = new String[]{"Mary","John","Emma"};
        int[] height = new int[]{180,165,170};
        String[] strings = new Solution().sortPeople(names, height);
        System.out.println();
    }

    /**
     * 利用Java排序API
     */
    class Solution {
        public String[] sortPeople(String[] names, int[] heights) {
            int n = names.length;
            Integer[] indices = new Integer[n];
            for (int i = 0; i < n; i++) {
                indices[i] = i;
            }
            Arrays.sort(indices, (a, b) -> heights[b] - heights[a]);
            String[] res = new String[n];
            for (int i = 0; i < n; i++) {
                res[i] = names[indices[i]];
            }
            return res;
        }
    }


    /**
     * 自定义堆排序实现
     */
    class Solution1 {
        /**
            给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。
            对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。
            请按身高 降序 顺序返回对应的名字数组 names 。

            示例 1：
            输入：names = ["Mary","John","Emma"], heights = [180,165,170]
            输出：["Mary","Emma","John"]
            解释：Mary 最高，接着是 Emma 和 John 。

            示例 2：
            输入：names = ["Alice","Bob","Bob"], heights = [155,185,150]
            输出：["Bob","Alice","Bob"]
            解释：第一个 Bob 最高，然后是 Alice 和第二个 Bob 。

            链接：https://leetcode.cn/problems/sort-the-people
         */
        public String[] sortPeople(String[] names, int[] heights) {
            if (names == null || names.length == 0 || heights == null || heights.length == 0) {
                return null;
            }
            // 排序
            CustomHeap customHeap = new CustomHeap(names.length);
            customHeap.batchInsert(heights);
            String[] ans = new String[heights.length];
            int i = 0;
            int pop = customHeap.pop(heights);
            while (pop >= 0) {
                ans[i ++] = names[pop];
                pop = customHeap.pop(heights);
            }
            return ans;
        }

        class CustomHeap {
            private int heapSize;
            private int[] arr;

            public CustomHeap() {
                new CustomHeap(10);
            }
            public CustomHeap(int heapSize) {
                this.arr = new int[heapSize];
            }
            public void batchInsert(int[] arr) {
                if (arr == null || arr.length == 0) {
                    return;
                }
                for (int i = 0; i < arr.length; i ++) {
                    insert(arr, i);
                }
            }

            /**
             * 堆中存储得是数据得下标
             * @param source
             * @param numIndex
             */
            public void insert(int[] source, int numIndex) {
                int index = heapSize, parent;
                arr[heapSize ++] = numIndex;
                while (index > 0) {
                    parent = (index - 1) / 2;
                    if (source[arr[index]] > source[arr[parent]]) {
                        ArrayExercise.this.swap(arr, index, parent);
                    }
                    index = parent;
                }
            }
            public int pop (int[] source) {
                if (heapSize <= 0) {
                    return -1;
                }
                int num = arr[0];
                ArrayExercise.this.swap(arr, 0, -- heapSize);
                heapify(source, 0);
                return num;
            }
            public void heapify(int[] source, int index) {
                int left = index * 2 + 1;
                while (left < heapSize) {
                    int right = left + 1;
                    int largest = right < heapSize && source[arr[left]] < source[arr[right]] ? right : left;
                    largest = source[arr[largest]] > source[arr[index]] ? largest : index;
                    if (largest == index) {
                        return;
                    }
                    ArrayExercise.this.swap(arr, index, largest);
                    index = largest;
                    left = index * 2 + 1;
                }
            }
        }

        private void swap(int[] heights, int a, int b) {
            heights[a] = heights[a] ^ heights[b];
            heights[b] = heights[a] ^ heights[b];
            heights[a] = heights[a] ^ heights[b];
        }

        /**
         * 前缀树
         */
        public int[] sumPrefixScores(String[] words) {
            Trie tree = new Trie();
            for (String word : words) {
                tree.insert(word);
            }
            int[] res = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                res[i] = tree.searchWord(word);
            }
            return res;
        }
    }
    class Trie {
        class Node {
            boolean isEnd;
            int cnt = 1;
            // 数组比列表和hash更省空间，效率更高
            Node[] children = new Node[26];
        }
        Node root;
        public Trie() {
            root = new Node();
        }
        public void insert(String word) {
            Node current = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new Node();
                } else {
                    current.children[index].cnt ++;
                }
                current = current.children[index];
            }
            current.isEnd = true;
        }
        public int searchWord(String word) {
            int sum = 0;
            Node current = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (current.children[index] == null) return sum;
                current = current.children[index];
                sum += current.cnt;
            }
            return sum;
        }
    }
}
