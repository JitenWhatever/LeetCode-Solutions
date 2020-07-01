/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 

Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

// as long as we find unique element expand the right boundry of window, and increase left when duplicate element occurs
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> hash = new HashSet<>();
        
        int window = 0, right = 0, left = 0;
        
        while(right < s.length()) {
            if(!hash.contains(s.charAt(right))) {
                hash.add(s.charAt(right++));
                
                window = Math.max(window, hash.size());
            }
            else if(left < s.length()){
                hash.remove(s.charAt(left++));
            }
        }
        
        return window;
    }
}