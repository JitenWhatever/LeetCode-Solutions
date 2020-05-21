/*
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        
        int H = matrix.length;
        if(H == 0) return 0;
        int W = matrix[0].length;
        int[][] dp = new int[H][W];
        int maxSquare = Integer.MIN_VALUE;
        
        for(int row = 0; row < H; ++row) {
            for(int col = 0; col < W; ++col) {
    
                if(row == 0 || col == 0) {
                    dp[row][col] = (matrix[row][col] - '0');
                    maxSquare = Math.max(maxSquare, dp[row][col]);
                    continue;
                }
                
                if(matrix[row][col] == '1') {
                    dp[row][col] = 1 + Math.min(dp[row - 1][col - 1], Math.min(dp[row][col - 1], dp[row - 1][col]));
                }
                
                maxSquare = Math.max(maxSquare, dp[row][col]);
                
            }
        }
        
        return (maxSquare * maxSquare);
    }
}