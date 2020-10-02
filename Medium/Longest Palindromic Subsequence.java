/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
 

Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
*/

class Solution {
    public int longestPalindromeSubseq(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int N = s.length();
        dp = new Integer[N][N];
        
        recurse(s, 0, N - 1); // 51 ms
    
        
        return dp[0][N - 1];
    }
    
    private Integer[][] dp;
    
    private int recurse(String s, int l, int r) {
        if(l > r) {
            dp[l][r] = 0;
            return dp[l][r];
        }
        
        if(l == r) {
            
            dp[l][r] = 1;
            return dp[l][r];
        }
        
        if(dp[l][r] != null) {
            return dp[l][r];
        }
        
        if(s.charAt(l) == s.charAt(r)) {
            dp[l][r] = 2 + recurse(s, l + 1, r - 1);
            
            return dp[l][r];
        }
        
        dp[l][r] =  Math.max(recurse(s, l + 1, r), recurse(s, l, r - 1));
        
        return dp[l][r];
    }
}