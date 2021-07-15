/*
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input: s = "mississippi", p = "mis*is*p*."
Output: false
 

Constraints:

1 <= s.length <= 20
1 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int N = s.length(), M = p.length();
        boolean[][] dp = new boolean[N + 1][M + 1]; // dp[i][j] string till i matches pattern till j or not
        
        dp[0][0] = true; 
        
        for (int index = 1; index <= M; ++index) {
            if (p.charAt(index - 1) == '*') {
                dp[0][index] = dp[0][index - 2];
            }
        }
        
        for (int index_i = 1; index_i <= N; ++index_i) {
            for (int index_j = 1; index_j <= M; ++index_j) {
                if (s.charAt(index_i - 1) == p.charAt(index_j - 1) || p.charAt(index_j - 1) == '.') {
                    dp[index_i][index_j] = dp[index_i - 1][index_j - 1];
                } else if (p.charAt(index_j - 1) == '*') {
                    dp[index_i][index_j] = dp[index_i][index_j - 2];
                    if (s.charAt(index_i - 1) == p.charAt(index_j - 2) || p.charAt(index_j - 2) == '.') {
                        dp[index_i][index_j] = dp[index_i][index_j] || dp[index_i - 1][index_j];
                    }
                }
            }
        }
        
        return dp[N][M];
    }
}