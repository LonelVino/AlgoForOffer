# LeetCode -- Python 学习之旅

[TOC]

## 简单

### 1.两数之和

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

#### 用 Python 中 list 的相关函数求解

方法一：
关键：`num2 = target - num1`，是否也在`list`里，用以下两种办法：
`num2 in nums`，返回`True`说明可以
`nums.index(num2)`，查找`num2`的索引

```python
def twoSum(nums, target):  # len() 方法返回对象（字符、列表、元组等）长度或项目个数
	lens = len(nums)
            j  = -1
            for i in range(lens):
                if (targe - nums[i]) in nums:
                    if (nums.count(target - nums[i]) == 1)&(target - nums[i] == nums[i]): 
                        continue
                     else:
                        j = nums.index(target - nums[i], i+1)
              if j > 0:
                return [i,j]
            else:
                return []
```

方法二：

优化解法：`num2`不需要每次都从`nums`里查找一边，只需要从`num1`的位置前后查找即可。
为了方便这里`index`从`num1`之前开始查找:

```python
def twoSum(nums, target):
    lens = len(nums)
    j = -1
    for i in range (1, lens):
        temp  = nums[:i]
        if (target - nums[i]) in temp:
            j = temp.index( target - nums[i])
            break
          if j>=0:
            return [j ,  i]
```

#### 用字典模拟哈希求解

```python
d = {key1 : value1, key2 : value2 }  # 字典格式
dict = {'Alice': '2341', 'Beth': '9102', 'Cecil': '3258'}  # 一个简单的字典实例
# 访问字典里的值
dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'}
print dict['Name']    # 输出Zara
# 修改字典
dict['Age'] = 8 # 更新
dict['School'] = "RUNOOB" # 添加
# 删除字典元素
del dict['name']  
dict.clear()       # 清空字典里所有条目
del dict
```



方法三：

字典记录了 `num1` 和 `num2` 的值和位置，而省了再查找 `num2` 索引的步骤。

```python
def twoSum(nums, target):
    hashmap = {}
    for ind, num in enumerate(nums):
        hashmap[num] = ind
        for i, num in enumerate(nums):
            j = hashmap.get(target - num)
            if j is not None and i != j:
                return [i, j]
 '''
 enumerate() 函数用于将一个可遍历的数据对象(如列表、元组或字符串)组合为一个索引序列，同时列出数据和数据下标
 >>> list(enumerate(seasons, start=1))       # 下标从 1 开始
 
[(1, 'Spring'), (2, 'Summer'), (3, 'Fall'), (4, 'Winter')]
 '''
```

方法四： 

类似于方法二，`num2`不需要在整个`dict`中去查找，可以在`num1`之前的`dict`查找；因此只需要一次循环

```python
def twoSum(nums, target):
    hashmap = {}
    for i, num in enumerate(nums):
        if hashmap.get(target - num) is not None:
            return [i, hashmap.get(target - num)]
         hashmap[num] = i #这句不能放在if语句之前，解决list中有重复值或target-num=num的情况
```



### 2.整数反转（考虑溢出）

通过循环将数字`x`的每一位拆开，在计算新值时**每一步都判断是否溢出**。
溢出的条件：大于最大值`MAX_VALUE`，小于最小值`MIN_VALUE`

设当前反转的结果为`ans`，下一位为`pop`

从`ans * 10 + pop > MAX_VALUE`这个溢出条件来看
			当出现了`ans > MAX_VALUE / 10`且 `还有pop需要添加的时候`，一定要溢出
			当出现了`ans == MAX_VALUE / 10`且`pop > 7`( 8是$2^{31}$ 的个位数)，则一定溢出

从`ans * 10 + pop < MIN_VALUE`这个溢出条件来看
			当出现了`ans < MIN_VALUE / 10`且`还有pop需要添加`时，一定要溢出
			当出现了`ans == MIN_VALUE / 10`且`pop < -8`时，一定要溢出 ( 8是$2^{31}$的个位数 )

<img src="LeetCode -- Python 学习之旅.assets/image-20200229134139873.png" alt="image-20200229134139873" style="zoom:67%;" /><img src="LeetCode -- Python 学习之旅.assets/image-20200229134156836.png" alt="image-20200229134156836" style="zoom:67%;" />

```python
import sys
def Reverse (x):
    ans = 0
    MAX_VALUE = sys.maxint
    MIN_VALUE = sys.minint
    while x != 0:
        pop = x % 10
        if ans > MAX_VALUE / 10 or (ans == MAX_VALUE / 10 and pop > 7)
        	return 0
        if ans < MIN_VALUE / 10 or (ans == MIN_VALUE / 10 and pop < -8)
        	return 0
        ans = ans * 10 + pop
        x /= 10
  return ans
```



### 3.回文数

#### 解法一：普通解法

将 **整数转为字符串**，然后再将字符串转换为数组，只需要循环字符串的一半，看对应元素是否相等。

<img src="LeetCode -- Python 学习之旅.assets/6df9cbf08ef47a1761e7426aab48228a8dcfc9c5f89c82b44148ad0e24efe511-file_1558924390360.gif" alt="6df9cbf08ef47a1761e7426aab48228a8dcfc9c5f89c82b44148ad0e24efe511-file_1558924390360" style="zoom:40%;" />



```python
class Solution:
    def isPalinDrome(self, x : int) -> bool :
        reserved_str = str(x)
        return reserved_str == reserved_str[ ::- 1]
    # 函数：判断是不是回文数
  
if __name__ == "__main__":
    ss = Solution()
    output = filter(ss.isPalinDrome, range(10000, 100000))
    print (list(output))   # 注意要用list()将output转化为一个列表，output是一个对象
    
  # 分片操作实际上是可以设置第三个参数的，第三个参数代表的是步长，当步长为-1就可以实现列表反转，操作如下：
# >>> number = [0,1,2,3,4]
# >>> number[::-1]
# [4, 3, 2, 1, 0]
# 开始位置未添加时默认为0，结束位置未添加时默认为最后一个元素所在位置
''' 反转reverse（）方法操作如下：
>>> number = [0,1,2,3,4]
>>> number.reverse()
>>> number
[4, 3, 2, 1, 0]'''

```

#### 解法二：进阶解法--数学解法

**取整和取余的数字进行对比**

比如`1221`这个数字：
	通过计算 `1221 / 1000 =  1`,  再`1221 % 10 = 1`, 可得到首位和末位；
	通过计算 `(1221 % 1000 / 10) / (1000 / 100) = 2`, 再 `(1221 % 1000 / 10) % 10 = 2`，可以得到第三位和第四位

<img src="https://pic.leetcode-cn.com/6df9cbf08ef47a1761e7426aab48228a8dcfc9c5f89c82b44148ad0e24efe511-file_1558924390360" alt="img" style="zoom:40%;" />

```python
class Solution:

    def isPalinDrome(self, x : int) -> bool:
        # 先判断边界条件
        if x < 0: return False
        div = 1
        # 计算最大除数
        while x / div >= 10:
            div *= 10
        # 开始循环，每循环一次都会判断，如果不满足直接跳出循环
        while x > 0:
            left = int(x / div)
            right = x % 10 # 得到末位数，用10取余
            print(left, right)
            if right != left: return False
            x = int((x % div) / 10)
            div = div / 100
        return True
```

#### 解法三：进阶解法--巧妙解法

**取出后半段进行反转，进行判断**

具体做法如下：
	每次进行取余操作 `y = x % 10`
	将取出的余数加到`reservedNum`里，`reversedNum = reservedNum * 10 + y`
	每次取出一个余数之后，自然数都要`/ 10`
	判断	`x`是不是小于`reversedNum`，如果是就结束循环
	最后判断`x`与`reversedNum`是否相等。这里需要考虑到奇数位的情况：如果是偶数位，则判断条件为`x == reversedNum`；如果是奇数位，则判断条件为`x == reversedNum / 10`。(比如 `12321`，只有当`reversedNum = 123, x = 12`时才跳出循环，此时判断条件为`12 == 123/10`)

<img src="https://pic.leetcode-cn.com/6777f38a8c8c443fde069cd4ffcbe39fc2828ff39ca3440fb01f15034e0271b8-file_1558924390353" alt="img" style="zoom: 20%;" />

```python
class Solution:

    def isPalinDrome(delf, x: int) -> bool:
        # 边界条件, 这里末尾是0就可以直接返回false的原因:比如1100、1010、1210、1110
        if ((x < 0) or ((x % 10 == 0) and x != 0)): return False
        if (x == 0): return True
        reversedNum = 0
        # 判断循环
        while x > reversedNum:
            reversedNum = reversedNum * 10 + (x % 10)
            x = int(x / 10)
        if ((reversedNum == x) or ((int(reversedNum / 10)) == x )): return True
        else: return False

```



### 4.罗马数字转整数

罗马数字包含以下七种字符: `I`，` V`， `X`， `L`，`C`，`D `和 `M`。


```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```




例如， 罗马数字 `2 `写做`II `，即为两个并列的 `1`。`12` 写做 `XII` ，即为 `X` +` II` 。 `27` 写做  `XXVII`, 即为 `XX + V + II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 `4` 不写做 `IIII`，而是 `IV`。数字 `1` 在数字 `5` 的左边，所表示的数等于大数 `5` 减小数 `1` 得到的数值 `4` 。同样地，数字 `9` 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

```
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
```


给定一个罗马数字，将其转换成整数。输入确保在 `1` 到 `3999` 的范围内。

**示例 1:**

```
输入: "III"
输出: 3
```

**示例 2:**

```
输入: "IV"
输出: 4
```

**示例 3:**

```
输入: "IX"
输出: 9
```
**示例 4:**

```
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
```

**示例 5:**


```
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```



```python
class Solution:

  def romanToInt(self, s: str) -> int:
    dict1 = {'I': 1, 'IV': 4, 'V': 5, 'IX':9, 'X': 10, 'XL': 40, 'L': 50, 'XC': 90, 
    'C': 100, 'CD': 400, 'D': 500, 'CM': 900, 'M': 1000}
    sum = 0
    i = 0
    while(i < len(s)):
      if s[i:i+2] in dict1.keys():
        sum += dict1[s[i:i+2]]
        i += 2
      elif s[i:i+1] in dict1.keys():
        sum += dict1[s[i]]
        i += 1
    return sum

if __name__ == "__main__":
  ss = Solution()
  ans = ss.romanToInt("DCDIV")
  print(ans)

```



### 5.整数转罗马数字

罗马数字包含以下七种字符: `I`，` V`， `X`， `L`，`C`，`D `和 `M`。


```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```




例如， 罗马数字 `2 `写做`II `，即为两个并列的 `1`。`12` 写做 `XII` ，即为 `X` +` II` 。 `27` 写做  `XXVII`, 即为 `XX + V + II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 `4` 不写做 `IIII`，而是 `IV`。数字 `1` 在数字 `5` 的左边，所表示的数等于大数 `5` 减小数 `1` 得到的数值 `4` 。同样地，数字 `9` 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

```
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
```

给定一个整数，将其转换成罗马数字。输入确保在 `1` 到 `3999` 的范围内。

**示例 1:**

```
输入: 3
输出: "III"
```

**示例 2:**

```
输入: 4
输出: "IV"
```

**示例 3:**

```
输入: 9
输出: "IX"
```

**示例 4:**

```
输入: 58
输出: "LVIII"
解释: L = 50, V= 5, III = 3.
```

**示例 5:**


```
输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

**贪心算法**：在表示一个较大整数的时候，“写出来的“罗马数字”的个数越少越好，以方便表示，并且这种表示方式还应该是唯一的。

> 每一步都使用当前较大的罗马数字作为加法因子，最后得到罗马数字表示就是长度最少的。

### 6.合并数组的数组

给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。

初始化 A 和 B 的元素数量分别为 m 和 n。

**示例:**

```
输入:
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
```

#### 方法1：直接合并后排序

最直观的方法是先将数组 `B` 放进数组 `A` 的尾部，然后直接对整个数组进行排序。

```python
from typing import List

class Solution:
  def merge(self, A: List[int], m: int, B: List[int], n: int) -> None: 
    A[m:] = B
    A.sort()

if __name__ == "__main__":
  ss = Solution()
  List1 = [12,23,43,0,0,0,0]
  m = 3
  List2 = [23,453,6546,34234]
  n = 3
  x = ss.merge(List1, m, List2, n)
```



**复杂度分析**

时间复杂度：$O((m+n)log(m+n))$
排序序列长度为 $m+n$，套用快速排序的时间复杂度即可，平均情况为 $O((m+n)log(m+n))$。

空间复杂度：$O(log(m+n))$
排序序列长度为 $m+n$，套用快速排序的空间复杂度即可，平均情况为 $O(\log(m+n))$。

#### 方法二：双指针

方法 1 没有利用数组 `A` 与` B` 已经被排序的性质。为了利用这一性质，我们可以使用双指针方法。这一方法将两个数组看作队列，每次从两个数组头部取出比较小的数字放到结果中。如下面的动画所示：

<img src="https://pic.leetcode-cn.com/fc6ffc0e15f9af4cfd24be0e5848b704d378236c658e46da21ef9264d924614b-lc_animation.gif" alt="lc_animation.gif" style="zoom: 33%;" />

我们为两个数组分别设置一个指针 `pa` 与 `pb` 来作为队列的头部指针

```python
from typing import List

class Solution:
  def merge(self, A: List[int], m: int, B: List[int], n: int) -> bool:
    sorted = []
    pa = 0; pb = 0
    while pa < m or pb < n:
      if pa == m:
        sorted.append(B[pb])
        pb += 1
      elif pb == n:
        sorted.append(A[pa])
        pa += 1
      elif A[pa] < B[pb]:
        sorted.append(A[pa])
        pa += 1
      else:
        sorted.append(B[pb])
        pb += 1
    A[:] = sorted

```

**复杂度分析**

时间复杂度：$O(m+n)$
排序序列长度为 $m+n$，套用快速排序的时间复杂度即可，平均情况为 $O(m+n)$。

空间复杂度：$O(m+n)$
排序序列长度为 $m+n$，套用快速排序的空间复杂度即可，平均情况为 $O(m+n)$。



### 7.和为s的连续正数序列

输入一个正整数 `target` ，输出所有和为 `target` 的连续正整数序列（至少含有两个数）。
序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

**示例 1：**

```
输入：target = 9
输出：[[2,3,4],[4,5]]
```

**示例 2：**

```
输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```

**限制：**

`1 <= target <= 10^5`

#### 滑动窗口

滑动窗口框起来的是一个左闭右开区间 $[i,j)$。注意，为了编程的方便，滑动窗口一般表示成一个**左闭右开区间**。在一开始，$i=1, j=1$，滑动窗口位于序列的最左侧，窗口大小为零。

<img src="https://pic.leetcode-cn.com/af4821c41d5b093e6a41ad5602208f9e7057cc1b002cd0053de71bc9a0e35b12.jpg" alt="sliding-window" style="zoom: 30%;" />

滑动窗口的重要性质是：**窗口的左边界和右边界永远只能向右移动**，而不能向左移动。这是为了保证滑动窗口的时间复杂度是 $O(n)$。
滑动窗口只有 **右边界向右移动（扩大窗口）** 和 **左边界向右移动（缩小窗口）** 两个操作.

##### 问题：窗口何时扩大、何时缩小？

当窗口的和小于`target`时，右边界右移（`j=j+1`）;
当窗口的和大于`target`时，左边界右移（`i=i+1`）;当窗口的和等于`target`时，记录此时窗口`[i, j)`；此时找到了唯一`i`开头的序列，然后左窗口右移；

```python
from typing import List

class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        i = 1; j = 1; sum = 0; res = []

        while i <= target // 2:
            if sum < target:
                sum += j
                j += 1
            elif sum > target:
                sum -= i
                i += 1
            else:
                arr = list(range(i, j))
                res.append(arr)
                sum -= i
                i += 1

        return res
```

### [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

给定一个数组，它的第 *`i`* 个元素是一支给定股票第 *`i`* 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。注意你不能在买入股票前卖出股票。

**示例 1:**

```powershell
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```

**示例 2:**

```powershell
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

形式上，对于每组$i$和 $j$（其中 $j > i$）我们需要找出 $max(prices[j] - prices[i])$。

#### 方法一：暴力法

```python
from typing import List

class Solution:
  def maxProfit(self, prices: List[int]) -> int:
    maxprofit = 0
    for i in range(len(prices)):
      for j in range(len(prices)):
        profit = prices[j] - prices[i]
        if maxprofit < profit:
          maxprofit = profit
    return maxprofit
```

复杂度分析

- 时间复杂度：$O(n^2)$。循环运行了$n(n-1)/2$次
- 空间复杂度：$O(1)$。只使用了常数个变量

#### 方法二：一次遍历

显然，如果我们真的在买卖股票，我们肯定会想：如果我是在历史最低点买的股票就好了！太好了，在题目中，我们只要用一个变量记录一个历史最低价格 $minprice$，我们就可以假设自己的股票是在那天买的。那么我们在第$ i $天卖出股票能得到的利润就是 $prices[i] - minprice$。

因此，我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？当考虑完所有天数之时，我们就得到了最好的答案。

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        inf = int(1e9)
        minprice = inf
        maxprofit = 0
        for price in prices:
            maxprofit = max(price - minprice, maxprofit)
            minprice = min(price, minprice)
        return maxprofit
```

**复杂度分析**

- 时间复杂度：$O(n)$，只需要遍历一次。
- 空间复杂度：$O(1)$，只使用了常数个变量。

### [1013. 将数组分成和相等的三个部分](https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/)

给你一个整数数组 `A`，只有可以将其划分为三个和相等的非空部分时才返回 `true`，否则返回 `false`。

形式上，如果可以找出索引 `i+1 < j` 且满足 `(A[0] + A[1] + ... +A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ...+ A[A.length - 1])`就可以将数组三等分。

**示例 1：**

```powershell
输出：[0,2,1,-6,6,-7,9,1,2,0,1]
输出：true
解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
```

**示例 2：**

```powershell
输入：[0,2,1,-6,6,7,9,-1,2,0,1]
输出：false
```

**示例 3：**

```powershell
输入：[3,3,6,5,-2,2,5,1,-9,4]
输出：true
解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
```

**提示：**

1. `3 <= A.length <= 50000`
2. `-10^4 <= A[i] <= 10^4`

#### 方法一：双指针

数组元素的总和 `sum` 不是3的倍数，直接返回`false`
使用双指针`left，right` 从数组两头开始一起找，节约时间
当 `left + 1 < right` 的约束下，可以找到数组两头的和都是` sum/3`,那么中间剩下的元素和就一定也是`sum/3`
（`left + 1 < right`的约束就是要中间有剩下的元素，使用`left < right`的约束，数组可能可以恰好被划分成两部分，中间没有元素）

```python
from typing import List

class Solution:
    def canThreePartsEqualSum(self, A:List[int]) -> bool:
        sum = 0
        for i in A:
            sum += i
        if (sum%3 != 0):
            return False

        left = 0
        leftSum = A[left]
        right = len(A) - 1
        rightSum = A[right]

        while (left + 1 < right):
            if (leftSum == sum/3) and (rightSum == sum/3):
                return True
            if (leftSum != sum/3):
                left += 1
                leftSum += A[left]
            if (rightSum != sum/3):
                right -= 1
                rightSum += A[right]
        return False

```

#### 方法二：直接找

```python
from typing import List

class Solution:
  def canThreePartsEqualSum(self, A: List[int]) -> bool:
    sum = 0
    for i in A:
      sum += i
    if sum % 3 != 0:
      return False
    
    s = 0
    flag = 0
    for i in A:
      s += i
      if s == sum/3:
        s = 0
        flag += 1
    
    return flag >= 3
```

### [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串，判断字符串是否有效。

注意空字符串可被认为是有效字符串。

**示例 1:**

```powershell
输入: "()"
输出: true
```

**示例 2:**

```powershell
输入: "()[]{}"
输出: true
```

**示例 4:**

```powershell
输入: "([)]"
输出: false
```

#### 方法：栈

算法

初始化栈 `S`。
一次处理表达式的每个括号。
如果遇到开括号，我们只需将其推到栈上即可。
如果我们遇到一个闭括号，那么我们检查栈顶的元素。如果栈顶的元素是一个 `相同类型的 `左括号，那么我们将它从栈中弹出并继续处理。**否则，这意味着表达式无效**。
如果到最后我们剩下的栈中仍然有元素，那么这意味着表达式无效。

<img src="LeetCode -- Python 学习之旅.assets/栈括号.gif" alt="栈括号" style="zoom: 70%;" />

```python
class Solution:
    def isValid(self, s: str) -> bool:
        mapping = {")": "(", "]": "[", "}": "{"}
        stack = []
        for char in s:
            if char in mapping:
                top_element = stack.pop() if stack else '#' #  三目运算符b?x:y----stack作为判断条件
                if mapping[char] != top_element:
                    return False
            else:
                stack.append(char)
        return not stack

```



**复杂度分析**

时间复杂度：`O(n)`，因为我们一次只遍历给定的字符串中的一个字符并在栈上进行 `O(1)` 的推入和弹出操作。
空间复杂度：`O(n)`，当我们将所有的开括号都推到栈上时以及在最糟糕的情况下，我们最终要把所有括号推到栈上。例如` ((((((((((`。



### [1071. 字符串的最大公因子](https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/)

对于字符串 `S` 和 `T`，只有在 `S = T + ... + T`（`T` 与自身连接 1 次或多次）时，我们才认定 “`T` 能除尽 `S`”。返回最长字符串 `X`，要求满足 `X` 能除尽 `str1` 且 `X` 能除尽 `str2`。

**示例 1：**

```powershell
输入：str1 = "ABCABC", str2 = "ABC"
输出："ABC"
```

**示例 2：**

```powershell
输入：str1 = "ABABAB", str2 = "ABAB"
输出："AB"
```

**示例 3：**

```powershell
输入：str1 = "LEET", str2 = "CODE"
输出：""
```

**提示：**

`1 <= str1.length <= 1000`
`1 <= str2.length <= 1000`
`str1[i] `和 `str2[i] `为大写英文字母

#### 最大公约数GCD算法之辗转相除法

<img align="left" src="https://img-blog.csdnimg.cn/20190309215312757.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzNTI3ODYx,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />**辗转相除法、更相减损术的效率远高于穷举法，且这两个方法的计算规模几乎不受计算对象量级的影响。**

辗转相除法的效率最高，更相减损术次之。除穷举法的效率高于减穷举法。















```python
def gcd(a,b):
    if a%b == 0:
        return b
    else :
        return gcd(b,a%b)
```

如果它们有公因子` abc`，那么 `str1 `就是 `m` 个 `abc` 的重复，`str2` 是` n` 个 `abc` 的重复，连起来就是 `m+n`个 `abc`，好像 `m+n`个 `abc` 跟 `n+m` 个 `abc` 是一样的。

如果`str1+str2 == str2+str1`，意味着有解；且`str1+str2!=str2+str1`是无解的充要条件

确定有解之后，最优解长度即`gcd(str1.length, str2.length)`

````python
class Solution:
    def gcdOfStrings(self, str1: str, str2: str) -> str:
        if (str1 + str2 != str2 + str1):
            return ""
        else:
            min_len = self.gcd(len(str1), len(str2))
            return str1[0:min_len]  #截取第一位到第min_len-1位的字符


    def gcd(self, num1: int, num2: int) -> int:
        if (num1 % num2 == 0):
            return num2
        else:
            return self.gcd(num2, num1 % num2)
````



### [169. 多数元素](https://leetcode-cn.com/problems/majority-element/)

给定一个大小为 *n* 的数组，找到其中的多数元素。多数元素是指在数组中出现次数**大于** `⌊ n/2 ⌋` 的元素。
可以假设数组是非空的，并且给定的数组总是存在多数元素。

**示例 1:**

```
输入: [3,2,3]
输出: 3
```

**示例 2:**

```
输入: [2,2,1,1,1,2,2]
输出: 2
```

最简单的暴力方法（即枚举数组中的每个元素，再遍历一遍数组统计其出现次数，时间复杂度为 $O(N^2)$的算法）  找出时间复杂度小于 O(N^2)*O*(*N*2) 的优秀做法

#### 方法一：哈希表

我们使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，**键表示一个元素，值表示该元素出现的次数**。

##### **1.collections**–**高性能容量数据类型**

collections的数据类型之一：counter(计数器)

```python
#统计词频
colors = ['red', 'blue', 'red', 'green', 'blue', 'blue']
result = {}
for color in colors:
    if result.get(color)==None:
        result[color]=1
    else:
        result[color]+=1
print (result)
#{'red': 2, 'blue': 3, 'green': 1}
```

用Counter怎么实现：

```python
from collections import Counter
colors = ['red', 'blue', 'red', 'green', 'blue', 'blue']
c = Counter(colors)
print (dict(c))
```

##### **2.字典中找最值**

`dogdistance = {'dog-dog': 33, 'dog-cat': 36, 'dog-car': 41, 'dog-bird': 42}`

`min(dogdistance, key=dogdistance.get)`

返回最小值的键值：`'dog-dog'`

`max(dogdistance, key=dogdistance.get)`

返回最大值的键值：`'dog-bird'`

```python
class Solution:
	def majorityElement(self, nums):
        	counts = collections.Counter(nums)
                    return max(counts.keys(), key=counts.get)
		# dict.keys() 返回字典中所有可用键的列表
```

时间复杂度：$O(n)$，其中 $n$ 是数组 `nums` 的长度。

空间复杂度：$O(n)$。哈希表最多包含$N-N/2$个键值对，所以占用的空间为 $O(n)$。

#### 方法二：排序

如果将数组 `nums `中的所有元素按照单调递增或单调递减的顺序排序，那么下标为 $n/2$ 的元素（下标从 `0` 开始）一定是众数。

```python
class Solution:
    def majorityElement(self, nums):
        nums.sort()
        return nums[len(nums)//2]
```

复杂度分析

时间复杂度：$O(nlogn)$。将数组排序的时间复杂度为 $O(nlogn)$。

空间复杂度：$O(logn)$。如果使用语言自带的排序算法，需要使用 $O(logn)$ 的栈空间。

### [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)

给定一个包含了一些 $0 $和 $1$的非空二维数组` grid` , 一个 **岛屿** 是由四个方向 (水平或垂直) 的 $1 $(代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

示例 1:

```powershell
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
```

对于上面这个给定矩阵应返回 `6`。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

**示例 2:**

```powershell
[[0,0,0,0,0,0,0,0]]
```

对于上面这个给定的矩阵, 返回 `0`。

**注意:** 给定的矩阵`grid` 的长度和宽度都不超过 50。

#### 方法一：深度优先搜索

为了确保每个土地访问不超过一次，我们每次经过一块土地时，将这块土地的值置为 `0`。这样我们就不会多次访问同一土地。

```python
class Solution:
    def dfs(self, grid, cur_i, cur_j):
        if cur_i < 0 or cur_j < 0 or cur_i == len(grid) or cur_j == len(grid[0]) or grid[cur_i][cur_j] != 1:
            return 0
        grid[cur_i][cur_j] = 0
        ans = 1
        for di, dj in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
            next_i, next_j = cur_i + di, cur_j + dj
            ans += self.dfs(grid, next_i, next_j)
        return ans
def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
    ans = 0
    for i, l in enumerate(grid):
        for j, n in enumerate(l):
            ans = max(self.dfs(grid, i, j), ans)
    return ans
```

```python
enumerate(sequence, [start=0])   #返回 enumerate(枚举) 对象。
```

- `sequence` -- 一个序列、迭代器或其他支持迭代对象。
- `start` -- 下标起始位置。

```powershell
>>>seasons = ['Spring', 'Summer', 'Fall', 'Winter']
>>> list(enumerate(seasons))
[(0, 'Spring'), (1, 'Summer'), (2, 'Fall'), (3, 'Winter')]
>>> list(enumerate(seasons, start=1))       # 下标从 1 开始
[(1, 'Spring'), (2, 'Summer'), (3, 'Fall'), (4, 'Winter')]
```

list(enumerate(seasons))

[(0, 'Spring'), (1, 'Summer'), (2, 'Fall'), (3, 'Winter')]

\>>> list(enumerate(seasons, start=1))       # 下标从 1 开始

[(1, 'Spring'), (2, 'Summer'), (3, 'Fall'), (4, 'Winter')]

**复杂度分析**

时间复杂度：$O(R * C)$。其中 $R$ 是给定网格中的行数，$C$是列数。我们访问每个网格最多一次。

空间复杂度：$O(R * C)$，递归的深度最大可能是整个网格的大小，因此最大可能使用$ O(R * C)$ 的栈空间。

### [1160. 拼写单词](https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/)

给你一份『词汇表』（字符串数组） `words` 和一张『字母表』（字符串） `chars`。

假如你可以用 `chars` 中的『字母』（字符）拼写出 `words` 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写时，`chars` 中的每个字母都只能用一次。

返回词汇表 `words` 中你掌握的所有单词的 **长度之和**。

**示例 1：**

```powershell
输入：words = ["cat","bt","hat","tree"], chars = "atach"
输出：6
解释： 
可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
```

**示例 2：**

```powershell
输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
输出：10
解释：
可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
```

**提示：**

1. `1 <= words.length <= 1000`
2. `1 <= words[i].length, chars.length <= 100`
3. 所有字符串中都仅包含小写英文字母

#### 解题思路：

直接统计字母表 `chars` 中每个字母出现的次数，然后检查词汇表 `words` 中的每个单词;如果该单词中每个字母出现的次数都小于等于词汇表中对应字母出现的次数，就将该单词长度加入答案中。

<img src="https://pic.leetcode-cn.com/545be33139677e5ef8de527255c93d11b21ffc77a020f22cabec2779f95e0415.gif" alt="图解" style="zoom:67%;" />

```python
from typing import List
import collections
from collections import Counter

class Solution:
  def countCharacters(self, words: List[str], chars: str) -> int:
    ans = 0
    cnt = collections.Counter(chars)
    for w in words:
      c = collections.Counter(w)
      if all(c[i] <= cnt[i] for i in c):
         ans += len(w)
    return ans
```

### [836. 矩形重叠](https://leetcode-cn.com/problems/rectangle-overlap/)

矩形以列表 `[x1, y1, x2, y2]` 的形式表示，其中 `(x1, y1)` 为左下角的坐标，`(x2, y2)` 是右上角的坐标。

如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。

给出两个矩形，判断它们是否重叠并返回结果。

**示例 1：**

```powershell
输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
输出：true
```

**示例 2：**

```powershell
输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
输出：false
```

**提示：**

1. 两个矩形 `rec1` 和 `rec2` 都以含有四个整数的列表的形式给出。
2. 矩形中的所有坐标都处于 `-10^9` 和 `10^9` 之间。
3. `x` 轴默认指向右，`y` 轴默认指向上。
4. 你可以仅考虑矩形是正放的情况。

#### 解法：穷举法

将二维问题转化为一维问题。既然题目中的矩形都是平行于坐标轴的，我们将矩形投影到坐标轴上：

<img align="left" src="LeetCode -- Python 学习之旅.assets/image-20200318231449424.png" alt="image-20200318231449424" style="zoom:55%;" />

矩形投影到坐标轴上，就变成了**区间**。稍加思考，我们发现：**两个互相重叠的矩形，它们在 $x$轴和 $y$ 轴上投影出的区间也是互相重叠的**。这样，我们就将矩形重叠问题转化成了区间重叠问题
穷举出两个区间所有可能的 6 种关系：

<img align="left" src="LeetCode -- Python 学习之旅.assets/image-20200318231623442.png" alt="image-20200318231623442" style="zoom:67%;" />

区间的 6 种关系中，不重叠只有两种情况，**<u>*判断不重叠更简单*</u>**。假设两个区间分别是 `[s1, e1]` 和 `[s2, e2]` 的话，区间不重叠的两种情况就是 `e1 <= s2` 和 `e2 <= s1`。

<img align="left" src="LeetCode -- Python 学习之旅.assets/image-20200318231802651.png" alt="image-20200318231802651" style="zoom:67%;" />

区间不重叠的条件：`e1 <= s2 || e2 <= s1`。将条件取反即为区间重叠的条件。

```python
def isRectangleOverlap(self, rec1: List[int], rec2: List[int]) -> bool:
    x_overlap = not(rec1[2] <= rec2[0] or rec2[2] <= rec1[0]) #x2 <= X1或者X2 <= x1 
    y_overlap = not(rec1[3] <= rec2[1] or rec2[3] <= rec1[1]) # y2 <= Y1或者Y2 <= y1 
    return x_overlap and y_overlap
```

### [409. 最长回文串](https://leetcode-cn.com/problems/longest-palindrome/)

给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

在构造过程中，请注意区分大小写。比如 `"Aa"` 不能当做一个回文字符串。

**注意:**
假设字符串的长度不会超过 1010。

**示例 1:**

```powershell
输入:
"abccccdd"
输出:
7
解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
```

#### 方法一：贪心

**算法**

对于每个字符 `ch`，假设它出现了 `v` 次，我们可以**使用该字符 `v / 2 * 2` 次**，在回文串的左侧和右侧分别放置 `v / 2` 个字符 `ch`，其中 `/` 为**整数除法**。例如若 `"a"` 出现了 `5` 次，那么我们可以使用 `"a"` 的次数为 `4`，回文串的左右两侧分别放置 `2` 个 `"a"`。

如果有任何一个字符 `ch` 的出现次数 `v` 为奇数（即 `v % 2 == 1`），那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。在代码中，我们用 `ans` 存储回文串的长度，由于在遍历字符时，`ans` 每次会增加 `v / 2 * 2`，因此 `ans` 一直为偶数。但在发现了第一个出现次数为奇数的字符后，我们将 `ans` 增加 `1`，这样 `ans` 变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变 `ans` 的值了。

```PYTHON
class Solution:
    def longestPalindrome(self, s):
        ans = 0
        count = collections.Counter(s)
        for v in count.values():
            ans += v // 2 * 2
            if ans % 2 == 0 and v % 2 == 1:
                ans += 1
        return ans
```



### [349. 两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/)

给定两个数组，编写一个函数来计算它们的交集。

**示例 1:**

```powershell
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
```

**示例 2:**

```powershell
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
```

**说明:**

- 输出结果中的每个元素一定是唯一的。
- 我们可以不考虑输出结果的顺序。

#### 方法一：暴力寻找

幼稚的方法:  根据第一个数组` nums1` 迭代并检查每个值是否存在在 `nums2` 内。如果存在,  将值添加到输出。这样的方法会导致$ O(n \times m) $的时间复杂性，其中 `n `和 `m` 是数组的长度。

```python
from typing import List

class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        array = []
        for m in nums1:
            if m in nums2 and m not in array:
                array.append(m)
        return array
```

#### 方法二：两个 set

使用集合 `set`，在 $O(1)$ 时间复杂度实现操作。

两个数组转换为集合 `set`，然后迭代较小的集合检查是否存在在较大集合中。平均情况下，这种方法的时间复杂度为 $O(n+m)$。

<img src="LeetCode -- Python 学习之旅.assets/image-20200322190251197.png" alt="image-20200322190251197" style="zoom:67%;" /><img src="LeetCode -- Python 学习之旅.assets/image-20200322190312233.png" alt="image-20200322190312233" style="zoom:67%;" /><img src="LeetCode -- Python 学习之旅.assets/image-20200322190329810.png" alt="image-20200322190329810" style="zoom:67%;" />

```python
from typing import List

class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        array = []
        count = 0
        if (not nums1) or (not nums2):
            return array
        nums1.sort()
        nums2.sort()
        s1 = 0; s2 = 0
        while (s1 < len(nums1)) and (s2 < len(nums2)):
            if nums1[s1] < nums2[s2]:
                s1 += 1
            elif nums1[s1] > nums2[s2]:
                s2 += 1
            else:
                array.append(nums1[s1])
                s1 += 1
                s2 += 1
                count += 1
                if(count > 1) and (array[count - 1] == array[count - 2]):  # 去重
                    count -= 1
                    del array[count - 1]  # 删除List指定位置的元素
            print(s1, s2)
        return array
```

### [892. 三维形体的表面积](https://leetcode-cn.com/problems/surface-area-of-3d-shapes/)

在 `N * N` 的网格上，我们放置一些 `1 * 1 * 1 ` 的立方体。

每个值 `v = grid[i][j]` 表示 `v` 个正方体叠放在对应单元格 `(i, j)` 上。

请你返回最终形体的表面积。

**示例 1：**

```
输入：[[2]]
输出：10
```

**示例 2：**

```
输入：[[1,2],[3,4]]
输出：34
解释：
```

**示例 3：**

```
输入：[[1,0],[0,2]]
输出：16
解释：6 + 10
```

**示例 4：**

```
输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
```

**示例 5：**

```
输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46
```

**提示：**

- `1 <= N <= 50`
- `0 <= grid[i][j] <= 50`

```python
class Solution:
    def surfaceArea(self, grid: List[List[int]]) -> int:
        area = 0
            for i in range(len(grid)):
              for j in range(len(grid)):
                level = grid[i][j]
                if level > 0:
                  area += (level << 2) + 2   # 加上上底、下底面积，四个侧面积
                  area -= min(level, grid[i - 1][j]) << 1 if(i > 0) else 0  # 减去上贴合的侧面积
                  area -= min(level, grid[i][j - 1]) << 1 if(j > 0) else 0  # 减去左贴合的侧面积
            return area
```

按位运算:  `>>`：右移，`<<`：左移；
			例如`4`，二进制为`100`, `4<<2 = 16`，因为二进制变为了`10000`

### [914. 卡牌分组](https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/)

给定一副牌，每张牌上都写着一个整数

此时，你需要选定一个数字 `X`，使我们可以将整副牌按下述规则分成 1 组或更多组：

- 每组都有 `X` 张牌。
- 组内所有的牌上都写着相同的整数。

仅当你可选的 `X >= 2` 时返回 `true`。

**示例 1：**

```
输入：[1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
```

**示例 2：**

```
输入：[1,1,1,2,2,2,3,3]
输出：false
解释：没有满足要求的分组。
```

**示例 3：**

```
输入：[1]
输出：false
解释：没有满足要求的分组。
```

**示例 4：**

```
输入：[1,1]
输出：true
解释：可行的分组是 [1,1]
```

**示例 5：**

```
输入：[1,1,2,2,2,2]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[2,2]
```

**提示：**

1. `1 <= deck.length <= 10000`
2. `0 <= deck[i] < 10000`

#### 方法一：暴力

枚举所有可行的 `X`，判断是否有满足条件的 `X` 即可

**算法**

从$ 2 $开始，从小到大枚举 `X`。

由于每一组都有 `X` 张牌，那么 `X` 必须是卡牌总数 `N` 的约数。

其次，对于写着数字 `i` 的牌，如果有 $count_i$ 张，由于题目要求「组内所有的牌上都写着相同的
整数」，那么 `X` 也必须是 $count_i $ 的约数，即：

​																					$count_i \quad mod\quad X==0$

对于每一个枚举到的 `X`，我们只要先判断 `X` 是否为 `N` 的约数，然后遍历所有牌中存在的数字 `i`，看它们对应牌的数量 $count_i$ 是否满足上述要求。如果都满足等式，则 `X` 为符合条件的解，否则需要继续令 `X` 增大，枚举下一个数字。

```python
from typing import List
from collections import Counter

class Solution:
  def hasGroupsSizeX(self, deck: List[int]) -> bool:
    count = Counter(deck)
    lens = len(deck)
    for X in range(2, lens + 1):
      if lens % X == 0:
         if all(v % X == 0 for v in count.values()):
           return True
    return False
  
if __name__ == "__main__":
  ss = Solution()
  # [1,2,3,4,4,3,2,1]
  # [1,1,1,2,2,2,3,3]
  # [1]
  # [1,1]
  # [1,1,2,2,2,2]
  arr = [1,1,2,2,2,2]
```

 **复杂度分析**

时间复杂度：$O(N^2)$ 其中 $N$是卡牌个数。最多枚举 $N$个可能的 $X$，对于每个 $X$，要遍历的数字 $i $最多有 $N$ 个。

#### 方法二：最大公约数

只有当$ X $为所有$count_i$ 的约数，即所有 $count_i$ 的最大公约数的约数时，才存在可能的分组。

公式化来说，我们假设牌中存在的数字集合为 `a, b, c, d, e`，那么只有当 `X` 为<img src="LeetCode -- Python 学习之旅.assets/image-20200328091411906.png" alt="image-20200328091411906" style="zoom:80%;" />
的约数时才能满足要求。

因此我们只要求出所有$count_i$  最大公约数 $g$，判断$ g$ 是否大于等于 $2$即可，如果大于等于 $2$，则满足条件，否则不满足。

```python
from typing import List
from collections import Counter
from functools import reduce
from math import gcd

class Solution:
  def hasGroupSizeX(self, deck: List[int]) -> bool:
    vals = Counter(deck).values()
    return reduce(gcd, vals) >= 2
```

**复杂度分析**

时间复杂度：$O(N \log C)$，其中 $N$是卡牌的个数，$C$是数组 $deck$ 中数的范围，在本题中$ C$ 的值为 $10000$。求两个数最大公约数的复杂度是$ O(\log C)$，需要求最多$ N - 1$ 次。

空间复杂度：$O(N + C)$ 或$ O(N$)。

[面试题62. 圆圈中最后剩下的数字](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

$0,1,…….,n-1$这$n$个数字排成一个圆圈，从数字$0$开始，每次从这个圆圈里删除第$m$个数字。求出这个圆圈里剩下的最后一个数字。

例如，$0、1、2、3、4$这$5$个数字组成一个圆圈，从数字$0$开始每次删除第$3$个数字，则删除的前4个数字依次是$2、0、4、1$，因此最后剩下的数字是3。

**示例 1：**

```
输入: n = 5, m = 3
输出: 3
```

**示例 2：**

```
输入: n = 10, m = 17
输出: 2
```

**限制：**

- `1 <= n <= 10^5`
- `1 <= m <= 10^6`

#### 约瑟夫环

约瑟夫问题比较难想的点有两个：

1. 当数到最后一个结点不足m个时，需要跳到第一个结点继续数。
2. 每轮都是上一轮被删结点的下一个结点开始数 m 个。

第一点比较好解决，可以通过取余来完成。
第二点的解决方案是：将删除结点的后继作为下一轮的第一个结点，后续结点依次排列。这样每轮都是从首结点开始数 m 个了
<img src="LeetCode -- Python 学习之旅.assets/image-20200330151035919.png" alt="image-20200330151035919" style="zoom:70%;" />

通过观察上图中的**结点对应关系**可以发现：设下一轮的最后结点编号为 p，那么当前一轮的最后结点为**从被删除结点向后偏移 p+1 处的结点** ！！！

换一个更好用代码实现的描述方式：**从被删除结点的下一个结点偏移 p 处的结点**，编号为 
$ ((m 取余 n) + p) 取余 n $

设函数$ f(n,m)$ 输出最后结点的编号，结点编号从 $0$ 开始，$n$ 为结点个数，$m $为删除步长。
<img src="LeetCode -- Python 学习之旅.assets/image-20200330151115823.png" alt="image-20200330151115823" style="zoom:80%;" /><img src="LeetCode -- Python 学习之旅.assets/image-20200330151149018.png" alt="image-20200330151149018" style="zoom:70%;" />

```python
class Solution:
  def lastRemaining(self, n: int, m: int) -> int:
    return self.f(n, m)

  def f(self, n: int, m: int) -> int:
    if n == 0:
      return 0
    return (m + self.f(n-1, m)) % n
```



### [1365. 有多少小于当前数字的数字](https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/)

给你一个数组 `nums`，对于其中每个元素 `nums[i]`，请你统计数组中比它小的所有数字的数目
换而言之，对于每个 `nums[i]` 你必须计算出有效的 `j` 的数量，其中 `j` 满足 `j != i` **且** `nums[j] < nums[i]` 

**示例 1：**

```powershell
输入：nums = [8,1,2,2,3]
输出：[4,0,1,1,3]
解释： 
对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
对于 nums[1]=1 不存在比它小的数字。
对于 nums[2]=2 存在一个比它小的数字：（1）。 
对于 nums[3]=2 存在一个比它小的数字：（1）。 
对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
```

**示例2：**

```powershell
输入：nums = [7,7,7,7]
输出：[0,0,0,0]
```

#### 方法一：暴力

比较容易想到的一个方法是，对于数组中的每一个元素，我们都遍历数组一次，统计小于当前元素的数的数目。

#### 方法二：快速排序

我们也可以将数组排序，并记录每一个数在原数组中的位置。对于排序后的数组中的每一个数，我们找出其左侧第一个小于它的数，这样就能够知道数组中小于该数的数量。



### [463. 岛屿的周长](https://leetcode-cn.com/problems/island-perimeter/)

给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域

网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

**示例 :**

```
输入:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

输出: 16
```

#### 题解

岛屿问题是一类经典的网格搜索类问题。类似的岛屿题目还有：
200.Number of Islands 岛屿数量
695.Max Area of Island 岛屿最大面积

要求解这道题，我们首先来看如何在网格上做 DFS，再看如何在 DFS 的时候求岛屿的周长

首先，每个方格与其上下左右的四个方格相邻，则 DFS 每次要分出四个岔：

```python
def dfs(grid: List[List[int]], r: int, c: int):
	dfs(grid, r-1, c) // 上相邻
	dfs(grid, r+1, c) // 下相邻
	dfs(grid, r, c-1) // 左相邻
 	dfs(grid, r, c+1) // 右相邻
```

边界条件：上下左右边缘的不能遍历是否有邻居，使用“先污染后治理”的办法，如果坐标合适，直接返回；同样，如果该点不是岛屿（值为0），也直接返回。

![标记已遍历方格](LeetCode -- Python 学习之旅.assets/602b96c3605464103d5693777713eab579d4b7321d8c44bd6429d56ad4f035e4.gif)

```python
if (not(0<=r and r < grid.length and 0<=c and c < grid[0].length)): 
    return 1  # 如果是边界上的岛屿，周长直接 + 1
```

判断一个岛屿的邻近是岛屿还是水域，是岛屿的话，周长不增加，如果是水域，那么周长增加1；

![img](LeetCode -- Python 学习之旅.assets/e0e2314bb62cb06383e6128a6ba2b75e7c942cc5a36dedc32d0b39868a597629.jpg)

```python
if (grid[r][c] == 0):   # 从一个岛屿方格走向水域方格，周长加 1
	return 1
if (grid[r][c] != 1)   # 如果该岛屿已经遍历过了，直接返回
	return
```

完整代码：

```python
class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        for r in range(len(grid)):  # rows  或者 grid.shape[0] (numpy)
            for c in range(len(grid[0])):   # columns 或者 grid.shape[1]
                if grid[r][c] == 1:
                    return self.dfs(grid, r, c)
        return 0
    
    def dfs(self, grid: List[List[int]], r: int, c: int) -> int:
        if (not(0 <= r and r < len(grid) and 0 <= c and c < len(grid[0]))):
            return 1
        if grid[r][c] == 0:
            return 1
        if grid[r][c] != 1:
            return 0
        grid[r][c] =2
        return self.dfs(grid, r-1, c) + self.dfs(grid, r+1, c) + self.dfs(grid, r, c-1) + self.dfs(grid, r, c+1)
```

## 中等

### 1.无重复字符最长子串（滑动窗口）

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

示例 1:

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

示例 2:

```
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```


示例 3:

```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。   
请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```



#### 滑动窗口

就是一个队列,比如例题中的 `abcabcbb`，进入这个队列（窗口）为 `abc` 满足题目要求，当再进入 `a`，队列变成了 `abca`，这时候不满足要求。所以，我们要移动这个队列！

如何移动？只要把队列的左边的元素移出就行了，然后后面的字符继续进入，重复此过程，直到满足要求！

一直维持这样的队列，找出队列出现最长的长度时候，求出解！

时间复杂度：$O(n)$

##### 方法一：

```python
def  lengthOfLongestSubstring(self, s: str) -> int:
    if not s: return 0
    left = 0
    lookup = set()
    n = len(s)
    max_len = 0
    cur_len = 0
    for i in range(n):                 #一直循环到字符串的最后一个字符
        cur_len += 1
        while s[i] in lookup:      # 现在队列为abc, a进入队列时有重复
            lookup. remove(s[left])    # 将最左边的字符从队列中删除
            left += 1			 
            cur_len -= 1
         if cur_len > max_len: max_len = cur_len
          lookup.add(s[i])               #将本次循环的字符加入队列中（不论是否重复）
       return max_len
```

##### 方法二：

```python
def lengthOfLongestSubstring (self,  s):
    '''
    :tyoe s: str
    :rtype: int
    '''
    from collections import defaultdict
    lookup = defaultdict(int)
    start = 0
    end = 0
    counter = 0
    max_len = 0
    while end < len(s)
     	if lookup[s[end]] >  0:   
            	counter += 1	    # 查dict()里有重复的Key，counter + 1 （counter用来监视队列中有没有重复）
                lookup[s[end]] += 1         # 字典中增加一个键值s[end]
                end += 1
                while counter > 0:             # 当dict()中有重复的Key时，开始循环 （counter = n, 表示有n个重复值）
                 	if lookup[s[start]] > 1:     
                        	counter -= 1	
                            lookup[s[start]] -= 1	# 删除队列第一个字符
                            start += 1			# 字符串指针左移一位
                  max_len = max(max_len, end  - start)
        return max_len
```



###  [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)

给定不同面额的硬币 `coins `和一个总金额` amount`。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 `-1`

**示例 1:**

```
输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
```

**示例 2:**

```
输入: coins = [2], amount = 3
输出: -1
```

**说明**:
你可以认为每种硬币的数量是无限的。

### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**示例:**

```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

**说明:**

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 $O(n^2)$ 。

**进阶:** 你能将算法的时间复杂度降低到 $O(nlog(n)) $吗?

#### 解法：动态规划

**状态定义：**

`dp[i]` 的值代表 `nums` 前 `i` 个数字的最长子序列长度。
**转移方程**： 设 `j∈[0,i)`，考虑每轮计算新 `dp[i]` 时，遍历 `[0,i)` 列表区间，做以下判断：

1.当 `nums[i] > nums[j]` 时： `nums[i]` 可以接在 `nums[j]` 之后（此题要求严格递增），此情况下最长上升子序列长度为 `dp[j]+1 `；
2.当 `nums[i] <= nums[j]` 时： `nums[i]` 无法接在 `nums[j]`之后，此情况上升子序列不成立，跳过。

上述所有情况 $ 1. $下计算出的 `dp[j] + 1` 的最大值，为直到 `i` 的最长上升子序列长度（即 `dp[i]` ）。实现方式为遍历 `j` 时，每轮执行 `dp[i] = max(dp[i], dp[j] + 1)`。

**初始状态**：$dp[i]$ 所有元素置 $1$，含义是每个元素都至少可以单独成为子序列，此时长度都为 $1$。

**返回值：**

返回 `dp` 列表中的最大值，即可得到全局最长上升子序列长度。

 **复杂度分析：**

时间复杂度 $O(N^2)$： 遍历计算 `dp` 列表需 `O(N)`，计算每个 `dp[i]` 需 `O(N)`。
空间复杂度 $O(N)$ ： `dp` 列表占用线性大小额外空间。



```python
from typing import List

class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        if not nums: return 0
        dp = [1] * len(nums)
        for i in range(len(nums)):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)
```

### [945. 使数组唯一的最小增量](https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/)

给定整数数组 A，每次 *move* 操作将会选择任意 `A[i]`，并将其递增 `1`

返回使 `A` 中的每个值都是唯一的最少操作次数。

**示例 1:**

```powershell
输入：[1,2,2]
输出：1
解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
```

**示例 2:**

```powershell
输入：[3,2,1,2,1,7]
输出：6
解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
```

**提示：**

1. `0 <= A.length <= 40000`
2. `0 <= A[i] < 40000`

#### 一、排序O(nlogn)

逻辑：先排序，再依次遍历数组元素，若当前元素小于等于它前一个元素，则将其变为前一个数+1。

```python
from typing import List

class Solution:
    def minIncrementForUnique(self, A: List[int]) -> int:
        #  先排序
        A.sort()
        move = 0
        # 遍历数组，若当前的元素小于等于他的前一个元素，+= (前一个数+1)
        for i in range(1, len(A)):
            if A[i] <= A[i - 1]:
                move += A[i - 1] + 1 - A[i]
                A[i] = A[i - 1] + 1
        return move
```

**复杂度分析**

时间复杂度：$O(N\log N$)，其中 $N$是数组 $A$ 的长度，即排序的时间复杂度。

空间复杂度：$O(\log N)$，排序需要额外 $O(\log N)$的栈空间

### [1162. 地图分析](https://leetcode-cn.com/problems/as-far-from-land-as-possible/)

你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。

我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：`(x0, y0)` 和 `(x1, y1)` 这两个区域之间的距离是 `|x0 - x1| + |y0 -y1|` 。

如果我们的地图上只有陆地或者海洋，请返回 `-1`。

<img align="left" src="LeetCode -- Python 学习之旅.assets/image-20200329095755674.png" alt="image-20200329095755674" style="zoom:67%;" />

**提示：**

1. `1 <= grid.length == grid[0].length <= 100`
2. `grid[i][j]` 不是 `0` 就是 `1`

#### 广度优先搜索

DFS和BFS是两种搜索树和图的基本策略，见名知其义， 深搜和广搜，一种往深处搜，一种往边上搜。 **DFS常用于暴力搜索所有状态，BFS常用于搜索到达某一状态的最短路径。**

##### DFS **（Depth-First-Search）**

DFS 在访问图中某一起始顶点 `v `后，由` v `出发，访问它的任一邻接顶点 `w1`；再从 `w1` 出发，访问与 `w1`邻接但还没有访问过的顶点 `w2`；然后再从` w2` 出发，进行类似的访问，… 如此进行下去，直至到达所有的邻接顶点都被访问过的顶点` u` 为止。
 接着，退回一步，退到前一次刚访问过的顶点，看是否还有其它没有被访问的邻接顶点。如果有，则访问此顶点，之后再从此顶点出发，进行与前述类似的访问；如果没有，就再退回一步进行搜索。重复上述过程，直到连通图中所有顶点都被访问过为止。

例如下图，其深度优先遍历顺序为` 1->2->4->8->5->3->6->7`

<img align="left" src="LeetCode -- Python 学习之旅.assets/3661808-441e2e1044af36d2.webp" alt="img" style="zoom:67%;" />

##### BFS （Breadth-First-Search）

一般用队列数据结构来辅助实现`BFS`算法。
从根节点开始，沿着树(图)的宽度遍历树(图)的节点。
`BFS `可以看成是**层序遍历**。从某个结点出发，`BFS `首先遍历到距离为 `1` 的结点，然后是距离为` 2、3、4……` 的结点。因此，BFS 可以用来求最短路径问题。BFS 先搜索到的结点，一定是距离最近的结点。

如下图，其广度优先算法的遍历顺序为：`1->2->3->4->5->6->7->8`
<img src="LeetCode -- Python 学习之旅.assets/3661808-7f5c9490d17f2177.webp" alt="img" style="zoom:67%;" /><img src="LeetCode -- Python 学习之旅.assets/0ef5121d0b1b70d60967e0f8081df945dbbca0a4b8fff26b8ca0e09c649e9210.jpg" alt="BFS 与最短路径" style="zoom: 30%;" />

这道题实际上就是求**海洋格子到陆地格子的最长路径**。BFS 能求最短路径，自然也能求最长路径。

```python
# 伪代码
while queue 非空:
	node = queue.pop()
    for node 的所有相邻结点 m:
        if m 未访问过:
            queue.push(m)
```

如果用上面这种写法来遍历的话，我们是无法区分 `BFS`遍历中的**每一“层”**的。这是因为，遍历的时候，第 $1 $层的结点还没出完队列，第 $2$ 层的结点就进来了。这个队列中第 $1$ 层和第 $2$ 层的结点会紧挨在一起，无法区分，也就无法知道每个结点的距离 $depth $了。

修改代码:  在每一层遍历开始前，记录队列中的结点数量 $n$ ，然后一口气处理完这一层的 $n$ 个结点。这样，我们就知道这 $ n$ 个结点位于同一层了。然后遍历下一层的时候，把变量 $depth$ 加一。
代码框架是这样的

```python
# 伪代码
depth = 0 # 记录遍历到第几层
while queue 非空:
    depth++
    n = queue 中的元素个数
    循环 n 次:
        node = queue.pop()
        for node 的所有相邻结点 m:
            if m 未访问过:
                queue.push(m)
```

#### 本题题解

有了计算最短路径的层序 `BFS `代码框架，写这道题就很简单了。这道题的主要思路是：

一开始，我们找出所有陆地格子，将它们放入队列，作为第 `0` 层的结点。
然后进行` BFS` 遍历，每个结点的相邻结点可能是**上、下、左、右**四个方向的结点，**注意判断结点位于网格边界的特殊情况**。
当遍历结束时，$ 当前的遍历层数就是海洋格子到陆地格子的最远距离 $。
注意：为了在遍历中不重复访问海洋格子，我们将已经遍历过的海洋格子的值改为 `2`，和原来海洋格子里的 `0` 区别开来。

```python
class Solution:
  def maxDistance(self, grid: List[List[int]]) -> int:
    N = len(grid)
    queue = []
    for i in range(N): # 将所有的陆地格子加入队列之中
      for j in range(N):
        if grid[i][j] == 1:
          queue.append((i, j))
    # 如果只有陆地或者海洋，返回-1
    if len(queue) == 0 or len(queue) == N * N:
      return -1

    distance = -1
    while len(queue) > 0:
      distance += 1
      # 一次性取出n个结点，以实现层序遍历
      n = len(queue)
      for i in range(n):
        r, c = queue.pop(0)
        #遍历上面单元格
        if r-1 >= 0 and grid[r-1][c] == 0:
          grid[r-1][c] = 2
          queue.append((r-1, c))
        if c-1 >= o and grid[r][c-1] == 0:
          grid[r][c-1] == 2
          queue.append((r, c-1))
        if r+1 < N and grid[r+1][c] == 0:
          grid[r+1][c] == 2
          queue.append((r+1, c))
        if c+1 < N and grid[r][c+1] == 0:
          grid[r][c+1] == 2
          queue.append((r, c+1))
          
    return distance
```



### [1111. 有效括号的嵌套深度](https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/)

**嵌套深度** `depth` 定义：即有效括号字符串嵌套的层数，`depth(A)` 表示有效括号字符串 `A` 的嵌套深度。详情参见题末「**嵌套深度**」部分。

有效括号字符串类型与对应的嵌套深度计算方法如下图所示：

<img src="LeetCode -- Python 学习之旅.assets/1111.png" alt="img" style="zoom: 15%;" />

给你一个「有效括号字符串」 `seq`，请你将其分成两个不相交的有效括号字符串，`A` 和 `B`，并使这两个字符串的深度最小。

- 不相交：每个 `seq[i]` 只能分给 `A` 和 `B` 二者中的一个，不能既属于 `A` 也属于 `B` 。
- `A` 或 `B` 中的元素在原字符串中可以不连续。
- `A.length + B.length = seq.length`
- 深度最小：`max(depth(A), depth(B))` 的可能取值最小。 

划分方案用一个长度为 `seq.length` 的答案数组 `answer` 表示，编码规则如下：

- `answer[i] = 0`，`seq[i]` 分给 `A` 。
- `answer[i] = 1`，`seq[i]` 分给 `B` 。
- 即：为 0 的部分对应 seq 的括号是 A 字符串，为 1 的部分对应 seq 的括号是 B 字符串。

如果存在多个满足要求的答案，只需返回其中任意 **一个** 即可。

**示例 1：**

```
输入：seq = "(()())"
输出：[0,1,1,1,1,0]
```

**示例 2：**

```
输入：seq = "()(())()"
输出：[0,0,0,1,1,0,1,1]
解释：本示例答案不唯一。
按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A), depth(B)) = 1 。 
```

`1 < seq.size <= 10000`

#### 方法：用栈进行括号匹配

**解题关键**：深度最小：`max(depth(A), depth(B))` 的可能取值最小，即**让 A 字符串和 B 字符串的 depth 尽可能的接近**------栈上的左括号只要按奇偶分配给A和B就可以

```python
from typing import List
class Solution:
    def maxDepthAfterSplit(self, seq: str) -> List[int]:
		ans = []
		depth = 0
		for c in seq:
  			if c == '(':
  				depth += 1
  				ans[depth] = depth % 2
			if c == ')':
  				ans[depth] = depth % 2
				depth -= 1
		return ans
```

复杂度分析

时间复杂度：$O(n)$，其中 $n$ 为字符串的长度。我们只需要遍历括号字符串一次。

空间复杂度：$O(1)$。除答案数组外，我们只需要常数个变量。

### [289. 生命游戏](https://leetcode-cn.com/problems/game-of-life/)

给定一个包含 $m × n $个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：$1$ 即为活细胞（$live$），或 $0 $即为死细胞（$dead$）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；

根据当前状态，写一个函数来计算面板上所有细胞的**下一个（一次更新后的）状态**。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

```powershell
输入： 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出：
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
  ]
```

#### 卷积

**什么是卷积？**

用一个模板($kernel$)和一幅图像进行卷积，对于图像上的一个点，让模板的原点和该点重合，然后模板上的点和图像上对应的点相乘，然后各点的积相加，就得到该点的卷积值。对图像上的每个点都这样处理。(注：这里的卷积是CV中常用的卷积和数字信号处理中的略有区别)

下面的图是一个没有补零(`zero padding​)`的2维卷积的示意图，深蓝色的$3x3$的正方形就是所说的$kernel$，下面的浅蓝色就是图像，通过滑动$kernel$并且将$kernel$的对应位置和它覆盖区域的对应位置的数值相乘并加和，就可以得到卷积后某一个位置的值，大家可以自己看着图计算一下

<img src="LeetCode -- Python 学习之旅.assets/acff40cd915c2a16d52eb33d9b152e9b6cff1bbd5bb12b5679d254e926be2eb0-v2-84a92b2e7cce6f31ad9fba1e57841198_b.webp" alt="v2-84a92b2e7cce6f31ad9fba1e57841198_b.webp" style="zoom:67%;" />

下图展示的是带`zero padding`的2d卷积操作，也是为了方便处理我们的数据(本题中同样采用补零，如果不在原始的$board$的周围补零，对于$board$最外围的一圈值处理起来比较麻烦，而通过补零我们可以统一进行处理)

<img src="LeetCode -- Python 学习之旅.assets/163bfccfd90c7e720b493186760a00a26c238007de6d35911855d4360242b16b-6e5c4d003f584cf1af342eae71dc31aa_th.jpg.gif" alt="6e5c4d003f584cf1af342eae71dc31aa_th.jpg.gif" style="zoom:67%;" />

```python
class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        import numpy as np
        r,c=len(board),len(board[0])
        #下面两行做zero padding
        board_exp=np.array([[0 for _ in range(c+2)] for _ in range(r+2)])
        board_exp[1:1+r,1:1+c]=np.array(board)
        #设置卷积核
        kernel=np.array([[1,1,1],[1,0,1],[1,1,1]])
        #开始卷积
        for i in range(1,r+1):
            for j in range(1,c+1):
                #统计细胞周围8个位置的状态
                temp_sum=np.sum(kernel*board_exp[i-1:i+2,j-1:j+2])
                #按照题目规则进行判断
                if board_exp[i,j]==1:
                    if temp_sum<2 or temp_sum>3:
                        board[i-1][j-1]=0
                else:
                    if temp_sum==3:
                        board[i-1][j-1]=1     
```





## 困难

### 1.最小覆盖字串（滑动窗口）

给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

**示例：**

```
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
```

**说明：**

- 如果 S 中不存这样的子串，则返回空字符串 `""`。
- 如果 S 中存在这样的子串，我们保证它是唯一的答案。

```python
def minWindow(self, s: 'str', t: 'str') -> 'str':
    from collections import defaultdict
    lookup = defaultdict(int)
    for c in t:
        lookup[c] += 1
    start = 0
    end = 0
    min_len = float ("inf")
    counter = len(t)
    res = ""
    while end < len(s):
        if lookup[s[end]] > 0:
            counter -= 1
        lookup[s[end]] -= 1
        end += 1
        while counter == 0:
            if min_len > end - start:
                min_len = end - start
                res = s[start: end]
            if lookup[s[end]] == 0:
                  counter += 1
           lookup[s[start]] += 1
           start += 1
      return res
    
```

### [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水

上面是由数组` [0,1,0,2,1,0,1,3,2,1,2,1] `表示的高度图，在这种情况下，可以接 $6 $个单位的雨水（蓝色部分表示雨水）。

**示例:**

```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

#### 解法1：按列求

每一列的水，只需要关注当前列，以及左边最高的墙，右边最高的墙就够了，然后再看那一边更矮。

- 较矮的墙的高度大于当前列的墙的高度:
  较矮的一边，也就是左边的墙的高度，减去当前列的高度就可以了。如`2-1=1`, 此单位可以存1单位的水

- 矮的墙的高度小于当前列的墙的高度：正在求的列不会有水，因为它大于了两边较矮的墙。

<img src="LeetCode -- Python 学习之旅.assets/image-20200404150049608.png" alt="image-20200404150049608" style="zoom:67%;" />

- 较矮的墙的高度等于当前列的墙的高度：和上一种情况是一样的，不会有水

所以确定了最左、最右最高的墙，以及确定两边谁更高之后，只需要`当前列<更矮的一边`，就可以存水

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        ans = 0
        for i in range(1, len(height) - 1):
            max_left, max_right = 0, 0
            for j in range(i - 1, -1, -1):
                max_left = height[j] if (height[j] >max_left) else max_left
            for j in range(i + 1, len(height)):
                max_right = height[j] if (height[j] > max_right) else max_right
            less = min(max_left, max_right)
            ans += less - height[i] if (height[i] < less) else 0
        return ans
```

时间复杂度：$O(n²）$，遍历每一列需要 $n$，找出左边最高和右边最高的墙加起来刚好又是一个 $n$，所以是$ n²$。

空间复杂度：$O(1）$

#### 解法2: 动态规划

- 首先用两个数组，`max_left [i] `代表第 `i` 列左边最高的墙的高度，`max_right[i]` 代表第 `i` 列右边最高的墙的高度。
- `max_left [i] = Max(max_left [i-1],height[i-1])`。它**前边的墙的左边的最高高度**和**它前边的墙的高度**选一个较大的，就是当前列左边最高的墙了。
- `max_right[i] = Max(max_right[i +1], height[i + 1])`。它**后边的墙的右边的最高高度**和它**后边的墙的高度**选一个较大的，就是当前列右边最高的墙了。

这样，我们再利用解法1的算法，就不用在 `for` 循环里每次重新遍历一次求 `max_left` 和 `max_right` 了。

```python
class Solution:
  def trap(self, height: List[int]) -> int:
    ans = 0
    max_left, max_right = [0]*len(height), [0]*len(height)
    for i in range(1, len(height) -1):
      max_left[i] = max(max_left[i - 1], height[i - 1])
    for i in range(len(height) - 2, 0, -1):
      max_right[i] = max(max_right[i + 1], height[i + 1])
    for i in range(1, len(height) - 1):
      less = min(max_right[i], max_left[i])
      ans += less - height[i] if (less > height[i]) else 0
    return ans
```

时间复杂度：$O(n)$。

空间复杂度：$O(n)$，用来保存每一列左边最高的墙和右边最高的墙。

#### 解法3：双指针

动态规划中，我们常常可以对**空间复杂度**进行进一步的优化。
例如这道题中，可以看到，`max_left [ i ]` 和 `max_right [ i ]` 数组中的元素我们其实只用一次，然后就再也不会用到了。
所以我们可以不用数组，只用一个元素就行了。

`left = 1 right = height.length - 2; # 加左右右指针进去`

` max_left = Math.max(max_left, height[left - 1]);`

` max_right = Math.max(max_right, height[right + 1]);`

时间复杂度：$ O(n)$。

空间复杂度：$ O(1)$。

