import collections
from collections import Counter

class Solution:
    def majorityElement(self, nums):
        counts = collections.Counter(nums)
        print (counts.keys())
        return max(counts.keys(), key=counts.get)

if __name__ == "__main__":
    ss = Solution()
    nums = [3,2,1,2,2,2,2,4]
    output = ss.majorityElement(nums)
    print (output)
