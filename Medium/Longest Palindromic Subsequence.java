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
        
        if (Objects.isNull(s) || s.isEmpty()) {
           return 0; 
        }
        
        dp = new Integer[s.length()][s.length()];
        
        recurse(s, 0, s.length() - 1);
        
        return dp[0][s.length() - 1];
    }
    
    private Integer dp[][];
    
    private int recurse(String s, int left, int right) {
          
        if(left > right) {
            dp[left][right] = 0;
            
            return dp[left][right];
        }
        
        if(left == right) {
            return dp[left][right] = 1;
        }
        
        if (Objects.nonNull(dp[left][right])) {
           return dp[left][right]; 
        }
        
        if (s.charAt(left) == s.charAt(right)) {
            return dp[left][right] = 2 + recurse(s, left + 1, right - 1);
        }
        
        return dp[left][right] = Math.max(recurse(s, left + 1, right), recurse(s, left, right - 1));
    }
}


// dp[i][j]  = maximum lenght palidromic subsequence of substring (i, j)

class Solution {
    public int longestPalindromeSubseq(String s) {
        
        if (Objects.isNull(s) || s.isEmpty()) {
           return 0; 
        }
        
        int[][] dp = new int[s.length()][s.length()];
        
        for(int left = s.length() - 1; left >= 0; --left) {
            dp[left][left] = 1;
            for(int right = left + 1; right < s.length(); ++right) {
                if(s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = 2 + dp[left + 1][right - 1];
                } else {
                    dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);
                }
            }
        }
       
        return dp[0][s.length() - 1];
    }    
}
