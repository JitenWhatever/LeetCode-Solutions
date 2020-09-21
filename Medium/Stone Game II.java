/*
Alex and Lee continue their games with piles of stones.  
There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  
The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. 
If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
*/

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        prefixSum = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            prefixSum[i][i] = piles[i];
            for (int j = i + 1; j < n; j++) {
                prefixSum[i][j] = piles[j] + prefixSum[i][j - 1];
            }
        }

        dp = new Integer[n][101];
                
        return recurse(piles, 0, 1);
    }
    
    private Integer[][] dp;
    private int[][] prefixSum;
    
    private int recurse(int[] piles, int index, int M) {
        if (dp[index][M] != null) {
            return dp[index][M];
        }
        
        int n = piles.length;
        
        if (index + 2 * M >= n) {
            dp[index][M] = prefixSum[index][n - 1];
            return dp[index][M];
        }
        
        int ans = 0;
    
        for (int i = 1; i <= 2 * M; i++) {
            ans = Math.max(ans, prefixSum[index][n - 1] - 
                           recurse(piles, index + i, Math.max(M, i)));
        }
        
        dp[index][M] = ans;
        return ans;
    }
}