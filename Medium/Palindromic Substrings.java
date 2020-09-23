/*

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 100
*/

class Solution {
    public int countSubstrings(String S) {
        if(S == null || S.length() == 0) {
            return 0;
        }
        
        int psubstrings = 0;
        
        for(int center = 0; center < S.length(); ++center) {
            psubstrings += palindrome(center, center, S);
            psubstrings += palindrome(center, center + 1, S);
        }
        
        return psubstrings;
    }
    
    int palindrome(int l , int r, String S) {
        int palindromes = 0;
        while(l >= 0 && r < S.length()) {
            if(S.charAt(l) == S.charAt(r)) {
                --l;
                ++r;
                ++palindromes;
            }
            else {
                break;
            }
        }
        return palindromes;
    }
}