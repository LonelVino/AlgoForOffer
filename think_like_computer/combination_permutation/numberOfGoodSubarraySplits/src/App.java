/*
 * 2750. 将数组划分成若干好子数组的方式 (https://leetcode.cn/problems/ways-to-split-array-into-good-subarrays/description/)
给你一个二元数组 nums。如果数组中的某个子数组 恰好 只存在 一 个值为 1 的元素，则认为该子数组是一个 "好子数组"。子数组是数组中的一个连续 非空 元素序列。
请你统计将数组 nums 划分成若干"好子数组"的方法数，并以整数形式返回。
由于数字可能很大，返回其对 10^9 + 7 取余 之后的结果。

 
示例 1：输入：nums = [0,1,0,0,1]; 输出：3
解释：存在 3 种可以将 nums 划分成若干好子数组的方式：
- [0,1] [0,0,1]
- [0,1,0] [0,1]
- [0,1,0,0] [1]

示例 2：输入：nums = [0,1,0]; 输出：1
 */

public class App {
    public int numberOfGoodSubarraySplits(int[] nums) {
        int idx = 0;
        while (nums[idx] != 1) {
            idx += 1;
            if (idx == nums.length) {
                return 0;
            }
        }
        long count = 1;
        int flag = 1;
        long zero_count = 1;
        for (int i = idx + 1; i < nums.length; i++) {
            if (nums[i] == 1) { // nums[i] = 1
                if (flag == 1) {
                    // count += 1;
                } else {
                    count = count * zero_count % ((long) 1e9 + 7);
                    flag = 1;
                }
            } else { // nums[i] = 0
                if (flag == 1) {
                    zero_count = 2;
                    flag = 0;
                } else {
                    zero_count += 1;
                }
            }
            System.out.printf("debug i: %d; nums: %d; count: %d\n", i, nums[i], count);
        }

        return (int) (count);
    }

    public static void main(String[] args) throws Exception {
        int[] nums = { 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1,
                0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0,
                0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0,
                1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1,
                1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1,
                1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1,
                1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1,
                1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0,
                1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1,
                0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0 };
        App solution = new App();
        int res = solution.numberOfGoodSubarraySplits(nums);
        System.out.printf("Number of splits: %d", res);
    }
}
