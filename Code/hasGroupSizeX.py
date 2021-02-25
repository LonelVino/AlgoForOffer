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
  print(ss.hasGroupsSizeX(arr))
