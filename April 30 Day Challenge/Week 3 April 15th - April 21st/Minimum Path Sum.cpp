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
public:
    int minPathSum(vector<vector<int>>& grid) {
        if(grid.size() == 0) return 0;
        
        int N = grid.size();
        int M = grid[0].size();
        
        vector<vector<int>> dp(N, vector<int>(M, 0));
        
        for(int i=0;i<N; i++){
            for(int j=0; j<M; j++){
                
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                    continue;
                }
                else if(i==0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                    continue;
                }
                else if(j==0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                    continue;
                }
                else{
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
                
            }
        }
        return dp[N-1][M-1];
    }
};