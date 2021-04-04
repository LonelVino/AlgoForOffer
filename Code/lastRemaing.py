class Solution:

  def lastRemaining(self, n: int, m: int) -> int:
    return self.f(n, m)

  def f(self, n: int, m: int) -> int:
    if n == 0:
      return 0
    return (m + self.f(n-1, m)) % n


if __name__ == "__main__":
  ss = Solution()
  m = 3
  n = 5
  print(ss.lastRemaining(n, m))
