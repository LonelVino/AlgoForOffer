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
