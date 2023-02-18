from typing import List
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

if __name__ == "__main__":
    ss = Solution()
    arr = [2, 0, 2]
    print(ss.trap(arr))
