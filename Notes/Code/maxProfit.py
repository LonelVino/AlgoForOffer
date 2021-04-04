from typing import List

class Solution:
  def maxProfit(self, prices: List[int]) -> int:
    maxprofit = 0
    for i in range(len(prices)):
      for j in range(len(prices)):
        profit = prices[j] - prices[i]
        if maxprofit < profit:
          maxprofit = profit
    return maxprofit

if __name__ == "__main__":
  ss = Solution()
  prices = [1,4,5,6,3,2]
  output = ss.maxProfit(prices)
  print (output)
