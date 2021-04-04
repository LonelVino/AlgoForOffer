from typing import List

class Solution:
  def maxProfit(self, prices: List[int]) -> int:
    maxprofit = 0
    inf = int(1e9)
    minprice = inf
    for i in range(len(prices)):
      if minprice > prices[i]:
        minprice = prices[i]
      if maxprofit < (prices[i] - minprice):
        maxprofit = prices[i] - minprice
    return maxprofit

if __name__ == "__main__":
  ss = Solution()
  prices = [1,23,4.5,32.3,1]
  output = ss.maxProfit(prices)
  print(output)
