/*
Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
Example 4:

Input: s = "g"
Output: 0
Example 5:

Input: s = "no"
Output: 1
 

Constraints:

1 <= s.length <= 500
All characters of s are lower case English letters.
*/

class Solution {
    public int minInsertions(String s) {
        dp = new int[s.length()][s.length()];
        
        for(int index_i = 0; index_i < dp.length; ++index_i) {
            for(int index_j = 0; index_j < dp.length; ++index_j) {
                dp[index_i][index_j] = -1;
            }
        }
        return helper(0, s.length() - 1, s);
    }
    
    int[][] dp = null;
    private int helper(int low, int high, String s) {
        
        if(low >= high) {
            return 0;
        }
        
        if(dp[low][high] != -1) {
            return dp[low][high];
        }
        
        if(s.charAt(low) == s.charAt(high)) {
            dp[low][high] =  helper(low + 1, high - 1, s);
            return dp[low][high];
        }
      
        int len1 = helper(low + 1, high, s);
        int len2 = helper(low, high - 1, s);
        dp[low][high] = Math.min(len1, len2) + 1;
        
        return dp[low][high];
    }
}


class Solution {
    public int minInsertions(String s) {
        int len = s.length();
         int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }
        char[] sa = s.toCharArray();
        for (int i = len - 2; i >=0 ; i--) {
            for (int j = i + 1; j < len; j++) {
                if (sa[i] == sa[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i+1][j],dp[i][j-1]) +1;
                }
            }
        }
        return dp[0][len-1];
    }
}