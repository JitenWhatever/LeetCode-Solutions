/*
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.


Example 1:
https://assets.leetcode.com/uploads/2020/09/02/interleave.jpg

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true
 

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.
 

Follow up: Could you solve it using only O(s2.length) additional memory space?
*/


// TLE
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        return recurse(0, 0, "");
    }
    
    private String s1, s2, s3;
    // O(2^(m + n))
    private boolean recurse(int i, int j, String currentResult) {
        if (currentResult.equals(this.s3) && i == this.s1.length() && j == this.s2.length()) {
            return true;
        }
        boolean result = false;
        if (i < this.s1.length()) {
            result |= recurse(i + 1, j, currentResult + this.s1.charAt(i));
        }
         if (j < this.s2.length()) {
            result |= recurse(i, j + 1, currentResult + this.s2.charAt(j));
        }
        
       return result;        
    }
}

//  1 MS 
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.dp = new Boolean[this.s1.length()][this.s2.length()];
        return recurse(0, 0, 0);
    }
    
    private String s1, s2, s3;
    private Boolean[][] dp;
    
    private boolean recurse(int i, int j, int k) {
        if (i == this.s1.length()) {
            return this.s2.substring(j).equals(this.s3.substring(k));
        }
    
        if (j == this.s2.length()) {
            return this.s1.substring(i).equals(this.s3.substring(k));
        }
        
        if (Objects.nonNull(dp[i][j])) {
            return dp[i][j];
        }
        
        boolean result = false;
        result |= (this.s3.charAt(k) == this.s1.charAt(i) && recurse(i + 1, j, k + 1));
        if (!result) {
            result |= (this.s3.charAt(k) == this.s2.charAt(j) && recurse(i, j + 1, k + 1));
        }
        
       return dp[i][j] = result;        
    }
}

// 2d DP 3 ms
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.dp = new boolean[s1.length() + 1][s2.length() + 1];
        
        for (int i = 0; i <= s1.length(); ++i) {
            for (int j = 0; j <= s2.length(); ++j) {
                if (i == 0 && j == 0) {
                    dp[i][j] =true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) || dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        
        return dp[s1.length()][s2.length()];
    }
    
    private boolean[][] dp;
   
}


// 1d DP 2MS
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.dp = new boolean[s2.length() + 1];
        
        for (int i = 0; i <= s1.length(); ++i) {
            for (int j = 0; j <= s2.length(); ++j) {
                if (i == 0 && j == 0) {
                    dp[j] =true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) || dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        
        return dp[s2.length()];
    }
    
    private boolean[] dp;
   
}