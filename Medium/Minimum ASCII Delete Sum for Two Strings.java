/*
Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:

Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:

Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 

Constraints:

1 <= s1.length, s2.length <= 1000
s1 and s2 consist of lowercase English letters.
*/

class Solution {
    public int minimumDeleteSum(String s1, String s2) {        
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        
        for (int index_i = 0; index_i <= s1.length(); ++index_i) {
            for (int index_j = 0; index_j <= s2.length(); ++index_j) {
                if (index_i == 0 && index_j == 0) {
                    dp[index_i][index_j] = 0;
                    continue;
                }
                
                if (index_i == 0) {
                    dp[index_i][index_j] = dp[index_i][index_j - 1] + (int)s2.charAt(index_j - 1);
                } else if (index_j == 0) {
                    dp[index_i][index_j] = dp[index_i - 1][index_j] + (int)s1.charAt(index_i - 1);
                } else if (s1.charAt(index_i - 1) == s2.charAt(index_j - 1)) {
                    dp[index_i][index_j] = dp[index_i - 1][index_j - 1];
                } else {
                    dp[index_i][index_j] = Math.min((int)s1.charAt(index_i - 1) + dp[index_i - 1][index_j], 
                                                   (int)s2.charAt(index_j - 1) + dp[index_i][index_j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        
        dp = new Integer[this.s1.length() + 1][this.s2.length() + 1];
        
        recurse(0, 0);
        
        return dp[0][0];
    }
    
    private Integer[][] dp;
    private String s1, s2;
    
    private int getASCIISum(String str, int index) {
        
        int sumASCII = 0;
        
        while (index < str.length()) {
            sumASCII += (int)str.charAt(index++);
        }
        
        return sumASCII;
    }
    
    private int recurse(int index_i, int index_j) {
        if (Objects.nonNull(dp[index_i][index_j])) {
            return dp[index_i][index_j];
        }
        
        if (index_i == this.s1.length()) {
            return dp[index_i][index_j] = getASCIISum(this.s2, index_j);
        }
        
        if (index_j == this.s2.length()) {
            return dp[index_i][index_j] = getASCIISum(this.s1, index_i);
        }
        
        if (this.s1.charAt(index_i) == this.s2.charAt(index_j)) {
            return dp[index_i][index_j] = recurse(index_i + 1, index_j + 1);
        }
        
        return dp[index_i][index_j] = Math.min(recurse(index_i + 1, index_j) + (int)this.s1.charAt(index_i), 
                                              recurse(index_i, index_j + 1) + (int)this.s2.charAt(index_j));
    }
}