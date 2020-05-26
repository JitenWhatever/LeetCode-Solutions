/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/

class Solution {
    public int minDistance(String A, String B) {
        int N = A.length(), M = B.length();
        
        int[][] dp = new int[N + 1][M + 1];
        
        for(int index_a = 0; index_a <= N; ++index_a) {
            for(int index_b = 0; index_b <= M; ++index_b) {
                if(index_a == 0 && index_b == 0) {
                    dp[index_a][index_b] = 0;
                    continue;
                }
                
                if(index_a == 0) {
                    dp[index_a][index_b] = 1 + dp[index_a][index_b - 1];
                    continue;
                }
                
                if(index_b == 0) {
                    dp[index_a][index_b] = 1 + dp[index_a - 1][index_b];
                    continue;
                }
                
                if(A.charAt(index_a - 1) == B.charAt(index_b - 1)) {
                    dp[index_a][index_b] = dp[index_a - 1][index_b - 1]; 
                }
                else {
                    dp[index_a][index_b] = Math.min(dp[index_a - 1][index_b - 1], Math.min(dp[index_a - 1][index_b], dp[index_a][index_b - 1])) + 1;
                }
                
            }
        }
        
        return dp[N][M];
    }
}