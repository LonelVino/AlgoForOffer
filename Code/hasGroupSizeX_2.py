from typing import List
from collections import Counter
from functools import reduce
from math import gcd

class Solution:
  def hasGroupSizeX(self, deck: List[int]) -> bool:
    vals = Counter(deck).values()
    return reduce(gcd, vals) >= 2
  
if __name__ == "__main__":
  ss = Solution()
  arr = [1,1,2,2,2,2] 
  print(ss.hasGroupSizeX(arr))

