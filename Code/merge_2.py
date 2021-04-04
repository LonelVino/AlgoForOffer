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
