/*
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Constraints:
    1 <= text1.length <= 1000
    1 <= text2.length <= 1000
    The input strings consist of lowercase English characters only.
*/

class Solution {
    public int longestCommonSubsequence(String A, String B) {
        int N = A.length(), M = B.length();
        
        int[][] dp = new int[N + 1][M + 1];
        
        for(int index_a = 1; index_a <= N; ++index_a) {
            for(int index_b = 1; index_b <= M; ++index_b) {
                if(A.charAt(index_a - 1) == B.charAt(index_b - 1)) {
                    dp[index_a][index_b] = dp[index_a - 1][index_b - 1] + 1;
                }
                else {
                     dp[index_a][index_b] = Math.max(dp[index_a - 1][index_b], dp[index_a][index_b - 1]);
                }
            }
        }
        
        return dp[N][M];
    }
}