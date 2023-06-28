/*
496. 下一个更大元素 I  
https://leetcode.cn/problems/next-greater-element-i/
----------------------------------------------------------------------------------------
题目描述：
nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
----------------------------------------------------------------------------------------
示例 1：

输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
输出：[-1,3,-1]
解释：nums1 中每个值的下一个更大元素如下所述：
- 4 ，即nums2中index=2的元素，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
- 1 ，即nums2中index=0的元素，nums2 = [1,3,4,2]。下一个更大元素是 3 。
- 2 ，即nums2中index=3的元素，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。

示例 2：

输入：nums1 = [2,4], nums2 = [1,2,3,4].
输出：[3,-1]
解释：nums1 中每个值的下一个更大元素如下所述：
- 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
- 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
----------------------------------------------------------------------------------------
提示：

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 104
nums1和nums2中所有整数 互不相同
nums1 中的所有整数同样出现在 nums2 中
----------------------------------------------------------------------------------------
思路：
1. nums1中的数字顺序只影响最终输出数组的顺序，而不影响输出数组的内容。因此，原问题化简为"对nums2中每个数字求其下一个更大元素" + "由nums1中数字作为键值索引出对应的nums2中元素的下一个最大元素"
2. leetcode套路：出现"求最近一个比其大的元素"的要求时，可尝试使用单调栈

步骤：
1. 因为计算的是"下一个"，所以对nums2，从左往右遍历
2. 为保证每次都将nums2[i]压入（由上往下递减的）单调栈，可以弹出栈顶元素
3. 则nums2[i]入栈前的栈顶元素为其对应的“下一个更大元素”，可将其储存在哈希表中
4. 遍历nums1, 使用nums1[i]作为键值索引哈希表中的值
----------------------------------------------------------------------------------------

暴力解法思路：
时间复杂度O（n*m）：为每个nums1元素遍历一次nums2数组，在nums2中找到与nums1相同元素后，判断之后遍历到的每个nums2[j]是否大于nums1[i]。如果是，则该值为nums1元素对应的目标值，可提前退出nums2的遍历。

暴力解法代码：
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < m && nums1[i] != nums2[j]) j++;
            while (j < m && nums1[i] >= nums2[j]) j++;
            ans[i] = j < m ? nums2[j] : -1;
        }
        return ans;
    }
}
----------------------------------------------------------------------------------------
*/


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        Deque<Integer> d = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = m - 1; i >= 0; i--) {
            int x = nums2[i];
            while (!d.isEmpty() && d.peekLast() <= x) d.pollLast();
            map.put(x, d.isEmpty() ? -1 : d.peekLast());
            d.addLast(x);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) ans[i] = map.get(nums1[i]);
        return ans;
    }
}
