from typing import List
class Solution:
    def maxDepthAfterSplit(self, seq: str) -> List[int]:
		ans = []
		depth = 0
		for c in seq:
  			if c == '(':
  				depth += 1
  				ans[depth] = depth % 2
			if c == ')':
  				ans[depth] = depth % 2
				depth -= 1
		return ans

  			
