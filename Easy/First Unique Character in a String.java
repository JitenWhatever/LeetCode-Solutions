/*
Given a string s, return the first non-repeating character in it and return its index. If it does not exist, return -1.

 

Example 1:

Input: s = "leetcode"
Output: 0
Example 2:

Input: s = "loveleetcode"
Output: 2
Example 3:

Input: s = "aabb"
Output: -1
 

Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
*/

class Solution {
    public int firstUniqChar(String s) {
       
        int[] frequency = new int[26]; 
        for(char ch: s.toCharArray()) {
                frequency[ch - 'a']++;
        }
        
        int index = 0;
        for(char ch : s.toCharArray()) {
            if(frequency[ch - 'a'] == 1) {
                return index;
            }
            index++;
        }
        return -1;
    }
}