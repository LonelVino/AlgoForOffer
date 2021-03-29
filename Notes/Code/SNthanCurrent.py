'''
Date: 2020-10-25 23:30:42
LastEditors: Lonel Vino
LastEditTime: 2020-10-25 23:44:54
FilePath: \Python\SNthanCurrent.py
'''

from typing import List 
class Solution:

  def smallerNumbersThanCurrent(self, nums: List[int]) -> List[int]:
    lens = len(nums)
    res = list(range(lens))
    for i in range(lens):
      cnt = 0
      for j in range (lens):
        if nums[j] < nums[i]:
          cnt = cnt + 1
      res[i] = cnt
    return res

if __name__ == '__main__':
  ss = Solution()
  nums1 = [8,1,2,2,3]  # 4,0,1,1,3]
  nums2 = [6,5,4,8]  # [2,1,0,3]
  nums3 = [7,7,7,7]   # [0,0,0,0]
  res1 = ss.smallerNumbersThanCurrent(nums1)
  print(res1)
  res2 = ss.smallerNumbersThanCurrent(nums2)
  print(res2)
  res3 = ss.smallerNumbersThanCurrent(nums3)
  print(res3)

    