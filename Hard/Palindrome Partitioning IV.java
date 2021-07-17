/*
Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.

 

Example 1:

Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
Example 2:

Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.
 

Constraints:

3 <= s.length <= 2000
s​​​​​​ consists only of lowercase English letters.
*/

class Solution {
    public boolean checkPartitioning(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return false;
        }
        
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        for (int start = s.length() - 1; start >= 0; --start) {
            for (int end = start; end < s.length(); ++end) {
                dp[start][end] = s.charAt(start) == s.charAt(end) && (end - start < 3 || dp[start + 1][end - 1]);
            }
        }
        
        for (int start = 1; start < s.length() - 1; ++start) {
            for (int end = start; end < s.length() - 1; ++end) {
                if (dp[0][start - 1] && dp[start][end] && dp[end + 1][s.length() - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}