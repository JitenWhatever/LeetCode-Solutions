/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It is guaranteed the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
*/

class Solution {
    public int numDistinct(String s, String t) {
        
        this.s = s;
        this.t = t;
        dp = new Integer[this.s.length()][this.t.length()];
        
        return recurse(0, 0);
    }
    
    private String s, t;
    private Integer[][] dp;
    
    private int recurse(int i, int j) {
        
        if (i == this.s.length() || j == this.t.length() || this.s.length() - i < this.t.length() - j) {
            return j == this.t.length() ? 1 : 0;
        }
        
        if (Objects.nonNull(this.dp[i][j])) {
            return this.dp[i][j];
        }
        
        int sequences = recurse(i + 1, j);
        
        if (this.s.charAt(i) == this.t.charAt(j)) {
            sequences += recurse(i + 1, j + 1);
        }
        
        return this.dp[i][j] = sequences;
    }
}

class Solution {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        
        int[][] dp = new int[s.length() + 1][t.length() + 1]; // dp[i][j] = subseqnce of t in s from 0 to i of s and 0 j of t
        
        for (int i = 0; i <= s.length(); ++i) {
            for (int j = 0; j <= t.length(); ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = j == 0 ? 1 : 0;
                } else  {
                    dp[i][j] = dp[i - 1][j];
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] += dp[i - 1][j - 1];     
                    }
                }
            }
        }
        
        return dp[s.length()][t.length()];
    }
}