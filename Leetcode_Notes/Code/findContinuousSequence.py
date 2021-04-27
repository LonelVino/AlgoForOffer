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

if __name__ == "__main__":
    ss = Solution()
    output = ss.findContinuousSequence(9)
    print (output)
