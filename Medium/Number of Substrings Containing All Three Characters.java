/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:

Input: s = "abc"
Output: 1

Constraints:
    3 <= s.length <= 5 x 10^4
    s only consists of a, b or c characters.
*/

class Solution {
    public int numberOfSubstrings(String s) {
        int[] hash = new int[3];
        
        int windows = 0, left = 0, right = 0, subStrings = 0;
        
        while(right < s.length()) {
            hash[s.charAt(right++) - 'a']++;
            
            while(left < s.length() && (hash[0] != 0 && hash[1] != 0 && hash[2] != 0)) {
                ++windows;
                hash[s.charAt(left++) - 'a']--;
            }
            
            subStrings += windows;
        }
        
        return subStrings;
    }
}