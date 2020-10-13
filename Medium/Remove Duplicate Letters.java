/*
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
*/

class Solution {
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        
        int[] count = new int[26];
        boolean[] assigned = new boolean[26];
        
        for(char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()) {
            count[ch - 'a']--;
            if(assigned[ch - 'a']) {
                continue;
            }
            
            while(sb.length() > 0 && ch < sb.charAt(sb.length() - 1) && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                assigned[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(ch);
            assigned[ch - 'a'] = true;
        }
        
        return sb.toString();
    }
}