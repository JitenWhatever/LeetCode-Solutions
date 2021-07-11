/*
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        for (int index_i = 1; index_i <= word1.length(); ++index_i) {
            for (int index_j = 1; index_j <= word2.length(); ++index_j) {        
                if (word1.charAt(index_i - 1) == word2.charAt(index_j - 1)) {
                    dp[index_i][index_j] = 1 + dp[index_i - 1][index_j - 1];
                } else {
                    dp[index_i][index_j] = Math.max(dp[index_i - 1][index_j], dp[index_i][index_j - 1]);
                }
            }
        }
        
        return word1.length() + word2.length() - 2*dp[word1.length()][word2.length()];
    }
}

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        for (int index_i = 0; index_i <= word1.length(); ++index_i) {
            for (int index_j = 0; index_j <= word2.length(); ++index_j) { 
                
                if (index_i == 0 || index_j == 0) {
                    dp[index_i][index_j] = index_i + index_j;
                    continue;
                }
                
                if (word1.charAt(index_i - 1) == word2.charAt(index_j - 1)) {
                    dp[index_i][index_j] = dp[index_i - 1][index_j - 1];
                } else {
                    dp[index_i][index_j] = Math.min(dp[index_i - 1][index_j], dp[index_i][index_j - 1]) + 1;
                }
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}