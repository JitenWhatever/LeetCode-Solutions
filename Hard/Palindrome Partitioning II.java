/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lower-case English letters only.
*/

//TLE
class Solution {
    public int minCut(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        this.input = s;
        return dfs(0, this.input.length() - 1);
    }
    
    private String input;
    
    private int dfs(int start, int minCut) {
        if (start >= this.input.length() || isPalindrome(start, this.input.length() - 1)) {
            return 0;
        }
        
        for (int end = start; end < this.input.length(); ++end) {
            if (isPalindrome(start, end)) {
                minCut = Math.min(minCut, 1 + dfs(end + 1, minCut));
            }
        }
        
        return minCut;
    }
    
    
    
    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (this.input.charAt(left++) != this.input.charAt(right--)) return false;
        }
        
        return true;
    }
}