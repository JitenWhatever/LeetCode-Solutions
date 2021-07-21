/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        
        int[] hash = new int[26];
        
        if (s.length() != t.length()) {
            return false;
        }
        
        for (int index = 0; index < s.length(); ++index) {
            ++hash[s.charAt(index) - 'a'];
            --hash[t.charAt(index) - 'a'];
        }
        
        for (int num : hash) {
            if (num != 0) {
                return false;
            }
        }
        
        return true;
    }
}