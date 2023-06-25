/*
62. 不同路径 (https://leetcode.cn/problems/unique-paths/)
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。问总共有多少条不同的路径？

输入：m = 3, n = 7; 输出：28
输入：m = 3, n = 2; 输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下

输入：m = 7, n = 3; 输出：28
输入：m = 3, n = 3; 输出：6
*/

/*
Solution (动态规划) (Time complexity -- O(m * n); Space complexity -- O(m * n)):
(1) Initialization of the state transition matrix:
        Since the number of unique paths on the boundary can only be 1, the elements of the matrix boundary are also 1:
            dp[:][:] = 0;
            dp[0][:] = 1; dp[:][0] = 1;
(2) State transition equation: 
        Since the robot can only move right or  down, we only need to consider the left and upper elements of the current position (i, j), i.e., (i, j-1) and (i-1, j):
            dp[i][j] = dp[i-1][j] + dp[i][j-1]

Optimized solution_1 (Space complexity -- O(2n)):
    Considering the columns, only dp[i][:] and dp[i-i][:] are required during updating the state transition matrix, thus we can compress the size of the martix from MxN to 2xN:

Optimized solution_2 (Space complexity -- O(n)):
    Besides the columns, the rows are the same, only dp[:][j] and dp[:][j-i] are required during updating the state transition matrix, 
    thus we can further compress the size of the matrix from 2xN to N:
*/

import java.util.Arrays;

public class unique_path_1 {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp[0].length; i++)
            dp[0][i] = 1; // assign each elem of the first row as 1
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1; // assign each elem of the first col as 1
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths_opti_1(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + pre[j];
            }
            pre = cur.clone();
        }
        return cur[n - 1];
    }

    public int uniquePaths_opti_2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }

    public static void main(String[] args) {
        unique_path_1 up1 = new unique_path_1();
        int res = up1.uniquePaths(3, 3);
        int res_opt1 = up1.uniquePaths_opti_1(3, 3);
        int res_opt2 = up1.uniquePaths_opti_2(3, 3);
        System.out.printf("DP: %d\nDP_Opt_1: %d\nDP_Opt_2: %d", res, res_opt1, res_opt2);
    }

}