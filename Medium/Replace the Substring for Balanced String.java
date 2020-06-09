/*
You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.

A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.

Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.

Return 0 if the string is already balanced.

Example 1:

Input: s = "QWER"
Output: 0
Explanation: s is already balanced.

Example 2:

Input: s = "QQWE"
Output: 1
Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.

Example 3:

Input: s = "QQQW"
Output: 2
Explanation: We can replace the first "QQ" to "ER". 

Example 4:

Input: s = "QQQQ"
Output: 3
Explanation: We can replace the last 3 'Q' to make s = "QWER".

Constraints:
    1 <= s.length <= 10^5
    s.length is a multiple of 4
    s contains only 'Q', 'W', 'E' and 'R'.
*/

class Solution {
    public int balancedString(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        char Q = 'Q', W = 'W', E = 'E', R = 'R';
        
        hash.put(Q, 0);
        hash.put(W, 0);
        hash.put(E, 0);
        hash.put(R, 0);
        
        for(char ch : s.toCharArray()) {
            hash.put(ch, hash.get(ch) + 1);
        }
            
        int count = s.length()/4;
        
        int window = Integer.MAX_VALUE, left = 0, right = 0;
        while(right < s.length()) {
            char ch = s.charAt(right++);
            hash.put(ch, hash.get(ch) - 1);
            
            while(left < s.length() && count >= hash.get(Q) && count >= hash.get(W) && count >= hash.get(E) && count >= hash.get(R)) {
                 window = Math.min(window, right - left);
                
                ch = s.charAt(left++);
                hash.put(ch, hash.get(ch) + 1);
            }
        
        }
        return window;
    }
}