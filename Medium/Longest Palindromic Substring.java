/*
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/


class Solution {
    public String longestPalindrome(String s) {
        
        if(s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0, end = 0;
        
        for(int index = 0; index < s.length(); ++index) {
            int len1 = getPalindromelenthFromCenter(index, index, s);
            int len2 = getPalindromelenthFromCenter(index, index + 1, s);
            
            int len = Math.max(len1, len2);
            
            if(len > end - start) {
                start = index - (len - 1)/2;
                end = index + len/2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    int getPalindromelenthFromCenter(int low, int high, String s) {
       
        while(low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            --low;
            ++high;
        }
        
        return (high - low - 1);
    }
}
