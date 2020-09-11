/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        
        lps = new int[s.length()];
        
        int maxPS = Integer.MIN_VALUE;
        
        lps(s);
        
        int n = s.length();
        
        int len = lps[n - 1];
       System.out.println(len);
        return len > 0 && n%(n - len) == 0;
    }
    
    private int[] lps;
    private void lps(String s) {
        int len = 0;
        int i = 1;
        
        while(i < s.length()) {
            if(s.charAt(i) == s.charAt(len)) {
                lps[i++] =  ++len;
            }
            else {
                if(len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i++] = len;
                }
            }
        }
    }
}