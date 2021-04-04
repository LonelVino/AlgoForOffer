class Solution:

  def romanToInt(self, s: str) -> int:
    dict1 = {'I': 1, 'IV': 4, 'V': 5, 'IX':9, 'X': 10, 'XL': 40, 'L': 50, 'XC': 90, 
    'C': 100, 'CD': 400, 'D': 500, 'CM': 900, 'M': 1000}
    sum = 0
    i = 0
    while(i < len(s)):
      if s[i:i+2] in dict1.keys():
        sum += dict1[s[i:i+2]]
        i += 2
      elif s[i:i+1] in dict1.keys():
        sum += dict1[s[i]]
        i += 1
    return sum

if __name__ == "__main__":
  ss = Solution()
  ans = ss.romanToInt("DCDIV")
  print(ans)
