/*
You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
Return the minimal number of characters that you need to change to divide the string.

 

Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
Example 2:

Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
Example 3:

Input: s = "leetcode", k = 8
Output: 0
 

Constraints:

1 <= k <= s.length <= 100.
s only contains lowercase English letters.
*/

//https://leetcode.com/problems/palindrome-partitioning-iii/discuss/498677/Step-by-Step-solution-DP-(Java) Thanks to qwerkjl112


class Solution {
    public int palindromePartition(String s, int k) {
        
        if (s.length() < k) {
            return 0;
        }
        
        this.dpPalindrome = new int[s.length()][s.length()];
        calculatePalindromeCost(s);
        
        int[][] dp = new int[k + 1][s.length()];
        
         for (int index = 0; index < s.length(); ++index) {
             dp[1][index] = dpPalindrome[0][index];
         }
        for (int group = 2; group <= k; ++group) {
            for (int end = group - 1; end < s.length(); ++end) {
                int minCost = s.length();
                for (int start = end - 1; start >= group - 2; --start) {
                    minCost = Math.min(minCost, dp[group - 1][start] + dpPalindrome[start + 1][end]);
                }  
                dp[group][end] = minCost;
            }
        }
        
        return dp[k][s.length() - 1];
    }
    
    private int[][] dpPalindrome;
    
    private void calculatePalindromeCost(String s) {
        for (int left = s.length() - 1; left >= 0; --left) {
            for (int right = left + 1; right < s.length(); ++right) {
                this.dpPalindrome[left][right] = s.charAt(left) == s.charAt(right) ? this.dpPalindrome[left + 1][right - 1] : 1 + this.dpPalindrome[left + 1][right - 1];
            }
        }
    }
}