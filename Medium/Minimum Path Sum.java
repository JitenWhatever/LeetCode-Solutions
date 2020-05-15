/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        Integer[][] dp = new Integer[rows][columns];
        
        for(Integer row = 0; row < rows; ++row) {
            for(Integer column = 0; column < columns; ++column) {
                if(row == 0 && column == 0) {
                    dp[row][column] = grid[0][0];
                    continue;
                }
                if(row == 0) {
                    dp[row][column] = dp[row][column - 1] + grid[row][column];
                }
                else if(column == 0) {
                    dp[row][column] = dp[row - 1][column] + grid[row][column];
                }
                else {
                    dp[row][column] = Math.min(dp[row - 1][column], dp[row][column - 1]) + grid[row][column];
                }
            
            }
        }
        return dp[rows - 1][columns - 1];
    }
}