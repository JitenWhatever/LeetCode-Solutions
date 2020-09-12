/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        
        if(strs == null || strs.length == 0) {
            return "";
        } 
        
        return helper(strs, 0, strs.length - 1);
        
    }
    
    private String helper(String[] strs, int l, int h) {
        if(l == h) {
            return strs[l];
        }
        
        int mid =  (l + h)/2;
        
        String s1 =  helper(strs, l, mid);
        String s2 = helper(strs, mid + 1, h);
        
        return getPrefix(s1, s2);
    }
    
    private String getPrefix(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());
       
        for(int index = 0; index < len; ++index) {
            if(s1.charAt(index) != s2.charAt(index)) {
                return s1.substring(0, index);
            }
        }
        
        return s1.substring(0, len);
    }
}