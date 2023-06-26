/*
 * 2749. 得到整数零需要执行的最少操作数 (https://leetcode.cn/problems/minimum-operations-to-make-the-integer-zero/description/)
给你两个整数：num1 和 num2 。在一步操作中，你需要从范围 [0, 60] 中选出一个整数 i ，并从 num1 减去 2^i + num2 。
请你计算，要想使 num1 等于 0 需要执行的最少操作数，并以整数形式返回。
如果无法使 num1 等于 0 ，返回 -1 。

示例 1：输入：num1 = 3, num2 = -2; 输出：3
解释：可以执行下述步骤使 3 等于 0 ：
- 选择 i = 2 ，并从 3 减去 22 + (-2) ，num1 = 3 - (4 + (-2)) = 1 。
- 选择 i = 2 ，并从 1 减去 22 + (-2) ，num1 = 1 - (4 + (-2)) = -1 。
- 选择 i = 0 ，并从 -1 减去 20 + (-2) ，num1 = (-1) - (1 + (-2)) = 0 。
可以证明 3 是需要执行的最少操作数。

示例 2：输入：num1 = 5, num2 = 7; 输出：-1
解释：可以证明，执行操作无法使 5 等于 0 。
 */

/*
 * Soluton 1 -- ENUMERATION:
 * ------- 
 * Insight: Numeral calculations with 2^i, could be solved by binary number, 
 *      e.g., the binary number of 12 is 1100, thus, 12 is the sum of (1 x 2^3 + 1 x 2^2 + 0 x 2^1 + 0 x 2^0)
 * 1. Type -- Long type for large number
 * 2. BitCount() -- count '1' in the binary number to determine the number of manipulation needed (2^i)
 * 3. Boundary conditions -- when num2 >= -1, the result will never be reduced to 0.
 * 
 * Main idea: 
 *      (1) let x = num1 - num2*k, then what we want is how to factorize `x` into several `2^i`.
 *      (2) enumerate `k` (number of manipulations) since 1, until find `k` that meets the condition (`num1` reduced to 0)
 * 
 * Boundary conditions:
 *      (1) when `x < k`, `x` cannot be factorized to k `2^i`, even though we take `2^0` for k times 
 *      (2) otherwise, if `k` is smaller than the number of `1` in the binary number (`binary(x)`), 
 *          then we wont have enough manipulations to reduce `x` to 0, e.g., `x = 12` and `k = 1`, but we need `2^3` and `2^2`, i.e., `k` should be 2
 * 
 * Time complexity: O(x+|y|), where x is the number of digits of `num1`, while y for `num2` (I still didnt get it)
 * Space complexity: O(1), caz only `x`, `k` are the extra variables, both of which are long type integers.
 */

/*
 * Soluton 2 -- Bit Manipulation
 * -------
 * TODO:
 */
import java.lang.Long;

public class App {
    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 1; k <= num1 - num2 * k; k++) {
            long x = num1 - num2 * k;
            if (k >= Long.bitCount(x))
                return (int) k;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        App solution = new App();
        int res = solution.makeTheIntegerZero(112577768, -501662198);
        System.out.printf("The minimum manipulation num is: %d", res);
    }
}
