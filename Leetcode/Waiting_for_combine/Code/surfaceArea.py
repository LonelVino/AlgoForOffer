from typing import List

class Solution:
  def surfaceArea(self, grid: List[List[int]]) -> int:
    area = 0
    for i in range(len(grid)):
      for j in range(len(grid)):
        level = grid[i][j]
        if level > 0:
          area += (level << 2) + 2   # 加上上底、下底面积，四个侧面积
          area -= min(level, grid[i - 1][j]) << 1 if(i > 0) else 0  # 减去上贴合的侧面积
          area -= min(level, grid[i][j - 1]) << 1 if(j > 0) else 0  # 减去左贴合的侧面积
    return area

if __name__  == "__main__":
  ss = Solution()
  grid = [[2,2,2],[2,1,2],[2,2,2]]
  print(ss.surfaceArea(grid))
