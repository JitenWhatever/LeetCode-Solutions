/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png
How many possible unique paths are there?

Above is a 7 x 3 grid. How many possible unique paths are there?


Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
*/

class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> dp(m, vector<int>(n));
        
        for(int r = 0; r < m; ++r) {
            for(int c = 0; c < n; ++c) {
                if(r == 0 || c == 0) {
                    dp[r][c] = 1;
                    continue;
                }
                
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }
};