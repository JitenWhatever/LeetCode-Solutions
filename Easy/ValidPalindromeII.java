/*
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false
 
Constraints:

1 <= s.length <= 10^5
s consists of lowercase English letters.
*/

class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while(low < high) {
            if(s.charAt(low) != s.charAt(high)) {
               return (helper(s, low + 1, high) || helper(s, low, high - 1));
            }
            
            ++low;
            --high;
        }
        return true;
    }
    
    private boolean helper(String s, int l, int r) {
        while(l < r) {
            if(s.charAt(l++) != s.charAt(r--)) {
               return false;
            }
        }
        return true;
    }
}