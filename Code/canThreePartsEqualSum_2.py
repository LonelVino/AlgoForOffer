from typing import List

class Solution:
  def canThreePartsEqualSum(self, A: List[int]) -> bool:
    sum = 0
    for i in A:
      sum += i
    if sum % 3 != 0:
      return False
    
    s = 0
    flag = 0
    for i in A:
      s += i
      if s == sum/3:
        s = 0
        flag += 1
    
    return flag >= 3

if __name__ == "__main__":
    ss = Solution()
    equa = [0,2,1,-6,6,-7,9,1,2,0,1]
    output = ss.canThreePartsEqualSum(equa)
    print (output)
