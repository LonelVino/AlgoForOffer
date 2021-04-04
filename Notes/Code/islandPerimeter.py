'''
Date: 2020-10-30 12:33:30
LastEditors: Lonel Vino
LastEditTime: 2020-10-30 14:48:06
FilePath: \Python\islandPerimeter.py
'''
from typing import List

class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        for r in range(len(grid)):  # rows  或者 grid.shape[0] (numpy)
            for c in range(len(grid[0])):   # columns 或者 grid.shape[1]
                if grid[r][c] == 1:
                    return self.dfs(grid, r, c)
        return 0
    
    def dfs(self, grid: List[List[int]], r: int, c: int) -> int:
        if (not(0 <= r and r < len(grid) and 0 <= c and c < len(grid[0]))):
            return 1
        if grid[r][c] == 0:
            return 1
        if grid[r][c] != 1:
            return 0
        grid[r][c] =2
        return self.dfs(grid, r-1, c) + self.dfs(grid, r+1, c) + self.dfs(grid, r, c-1) + self.dfs(grid, r, c+1)

if __name__ == '__main__':
    ss = Solution()
    grid = [[0, 1, 0, 0],
            [1, 1, 1, 0],
            [0, 1, 0, 0],
            [1, 1, 0, 0]]
    res = ss.islandPerimeter(grid)
    print(res)
