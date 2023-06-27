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

/*
 * Insight: only when `nums[i]==1`, update `count`; otherwise, count the number of `0`s between its previous and rear `1`, which will be used for `count` update
 * 
 * Main process: 
 *
 * NB: (1) take the remainder of each `count` update
 *     (2) type of `count` --> long type
 *     (3) should be multiplication, rather than addition
*/

public class App {
    public int numberOfGoodSubarraySplits(int[] nums) {
        long ans = 1;
        int pre = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                continue;
            if (pre >= 0)
                ans = ans * (i - pre) % ((long) 1e9 + 7);
            pre = i;
        }
        return pre >= 0 ? (int) ans : 0;
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
