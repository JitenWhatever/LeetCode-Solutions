/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        
        Integer H = grid.length, W = grid[0].length;
        Integer[][] dp = new Integer[H][W];
    
        for(Integer row = 0; row < H; ++row) {
            for(Integer column = 0; column < W; ++column) {
                if(row == 0 && column == 0) {
                    dp[row][column] = (grid[row][column] == 1) ? 0 : 1;
                    continue;
                }

                if(row == 0){
                    dp[row][column] = (grid[row][column] == 1) ? 0 : dp[row][column - 1];
                    continue;
                }
                
                if(column == 0) {
                    dp[row][column] = (grid[row][column] == 1) ? 0 : dp[row - 1][column];
                    continue;
                }
                
                dp[row][column] = (grid[row][column] == 1) ? 0 : dp[row - 1][column] + dp[row][column - 1];
            }
        }
        
        return dp[H - 1][W - 1];
    }
}