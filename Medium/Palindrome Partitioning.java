/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
*/

class Solution {
    public List<List<String>> partition(String s) {
        this.result = new ArrayList<>();
        if (Objects.isNull(s) || s.isEmpty()) {
            return this.result;
        }
        
        this.input = s;
        
        dfs(0, new ArrayList<>());
        
        return this.result;
    }
    
    private String input;
    private List<List<String>> result;
    
    private void dfs(int start, List<String> currentResult) {
        if (start >= this.input.length()) {
            result.add(new ArrayList(currentResult));
        }
        
        for (int end = start; end < this.input.length(); ++end) {
            if (isPalindrome(start, end)) {
                currentResult.add(this.input.substring(start, end + 1));
                
                dfs(end + 1, currentResult);
                
                currentResult.remove(currentResult.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (this.input.charAt(left++) != this.input.charAt(right--)) return false;
        }
        
        return true;
    }
}

class Solution {
    public List<List<String>> partition(String s) {
        this.result = new ArrayList<>();
        if (Objects.isNull(s) || s.isEmpty()) {
            return this.result;
        }
        
        this.input = s;
        this.dp = new boolean[this.input.length()][this.input.length()];
        
        dfs(0, new ArrayList<>());
        
        return this.result;
    }
    
    private String input;
    private List<List<String>> result;
    private boolean[][] dp;
    
    private void dfs(int start, List<String> currentResult) {
        if (start >= this.input.length()) {
            result.add(new ArrayList(currentResult));
        }
        
        for (int end = start; end < this.input.length(); ++end) {
            if (this.input.charAt(start) == this.input.charAt(end) && (end - start < 3 || dp[start + 1][end - 1])) {
                currentResult.add(this.input.substring(start, end + 1));
                dp[start][end] = true;
                dfs(end + 1, currentResult);
                
                currentResult.remove(currentResult.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (this.input.charAt(left++) != this.input.charAt(right--)) return false;
        }
        
        return true;
    }
}