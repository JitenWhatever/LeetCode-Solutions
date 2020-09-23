/*
Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.

Example 1:

Input: mat = [[1,0,1],
              [1,1,0],
              [1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2. 
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
Example 2:

Input: mat = [[0,1,1,0],
              [0,1,1,1],
              [1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3. 
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2. 
There are 2 rectangles of side 3x1. 
There is 1 rectangle of side 3x2. 
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
Example 3:

Input: mat = [[1,1,1,1,1,1]]
Output: 21
Example 4:

Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
Output: 5
 

Constraints:

1 <= rows <= 150
1 <= columns <= 150
0 <= mat[i][j] <= 1
*/

class Solution {
    public int numSubmat(int[][] mat) {
        if(mat == null || mat.length == 0) {
            return 0;
        }
        
        int n = mat.length;
        int m = mat[0].length;
        
        int[][] dp = new int[n][m];
        
        for(int r = 0; r < n; ++r) {
            int ones = 0;
            for(int c = 0; c < m; ++c) {
                if(mat[r][c] == 1) {
                    ++ones;
                }
                else {
                    ones = 0;
                }
                
                dp[r][c] = ones;
            }
        }
        
        int subMatrices = 0;
        for(int r = 0; r < n; ++r) {
            for(int c = 0; c < m; ++c) {
                int result = Integer.MAX_VALUE;    
                for(int k = r; k < n; ++k) {
                    result = Math.min(result, dp[k][c]);
                    subMatrices += result;
                }
            }
        }
        
        return subMatrices;
    }
}
