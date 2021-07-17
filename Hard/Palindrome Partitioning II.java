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

// 869ms
class Solution {
    public int minCut(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        this.input = s;
        this.dp = new Integer[this.input.length()][this.input.length()];
        return dfs(0, this.input.length() - 1);
    }
    
    private String input;
    private Integer[][] dp; // minimum cut for string i to j
    
    private int dfs(int start, int minCut) {
        if (start >= this.input.length() || isPalindrome(start, this.input.length() - 1)) {
            return 0;
        }
        
        if (Objects.nonNull(dp[start][this.input.length() - 1])) {
            return dp[start][this.input.length() - 1];
        }
        
        for (int end = start; end < this.input.length(); ++end) {
            if (isPalindrome(start, end)) {
                minCut = Math.min(minCut, 1 + dfs(end + 1, minCut));
            }
        }
        
        return dp[start][this.input.length() - 1] = minCut;
    }
    
    
    
    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (this.input.charAt(left++) != this.input.charAt(right--)) return false;
        }
        
        return true;
    }
}

// 40ms
class Solution {
    public int minCut(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        this.input = s;
        this.dp = new Integer[this.input.length()][this.input.length()];
        this.dpPalindrome = new Boolean[this.input.length()][this.input.length()];
        return dfs(0, this.input.length() - 1);
    }
    
    private String input;
    private Integer[][] dp; // minimum cut for string i to j
    private Boolean[][] dpPalindrome; // substring from i to j is palindrome or not
    
    private int dfs(int start, int minCut) {
        if (start >= this.input.length() || isPalindrome(start, this.input.length() - 1)) {
            return 0;
        }
        
        if (Objects.nonNull(dp[start][this.input.length() - 1])) {
            return dp[start][this.input.length() - 1];
        }
        
        for (int end = start; end < this.input.length(); ++end) {
            if (isPalindrome(start, end)) {
                minCut = Math.min(minCut, 1 + dfs(end + 1, minCut));
            }
        }
        
        return dp[start][this.input.length() - 1] = minCut;
    }
    
    
    
    private boolean isPalindrome(int start, int end) {
        if (start >= end) {
            return true;
        }
        
        if (Objects.nonNull(dpPalindrome[start][end])) {
            return dpPalindrome[start][end];
        }
        
        return dpPalindrome[start][end] = this.input.charAt(start) == this.input.charAt(end) && isPalindrome(start + 1, end - 1);
    }
}

// 35ms space N*N
class Solution {
    public int minCut(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        this.input = s;
        this.dp = new Integer[this.input.length()];
        this.dpPalindrome = new Boolean[this.input.length()][this.input.length()];
        return dfs(0, this.input.length() - 1);
    }
    
    private String input;
    private Integer[] dp; // minimum cut for string i to j
    private Boolean[][] dpPalindrome; // substring from i to j is palindrome or not
    
    private int dfs(int start, int minCut) {
        if (start >= this.input.length() || isPalindrome(start, this.input.length() - 1)) {
            return 0;
        }
        
        if (Objects.nonNull(dp[start])) {
            return dp[start];
        }
        
        for (int end = start; end < this.input.length(); ++end) {
            if (isPalindrome(start, end)) {
                minCut = Math.min(minCut, 1 + dfs(end + 1, minCut));
            }
        }
        
        return dp[start] = minCut;
    }
    
    
    
    private boolean isPalindrome(int start, int end) {
        if (start >= end) {
            return true;
        }
        
        if (Objects.nonNull(dpPalindrome[start][end])) {
            return dpPalindrome[start][end];
        }
        
        return dpPalindrome[start][end] = this.input.charAt(start) == this.input.charAt(end) && isPalindrome(start + 1, end - 1);
    }
}

// 19 ms top-down
class Solution {
    public int minCut(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        int[] dp = new int[s.length()];
        boolean[][] dpPalindrome = new boolean[s.length()][s.length()];
        
        for (int end = 0; end < s.length(); ++end) {
            int minCut = end;
            
            for (int start = 0; start <= end; ++start) {
                if (s.charAt(start) == s.charAt(end) && (end - start < 3 || dpPalindrome[start + 1][end - 1])) {
                    dpPalindrome[start][end] = true;
                    minCut = start == 0 ? 0 : Math.min(minCut, 1 + dp[start - 1]) ;
                }
            }
            
            dp[end] = minCut;
        }
        
        return dp[s.length() - 1]; // dp[i] minicut for string ending at i
      
    }
}


class Solution {

    public int minCut(String s) {
        int cutsDp[];
        cutsDp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            cutsDp[i] = i;
        }
        for (int mid = 0; mid < s.length(); mid++) {
            // check for odd length palindrome around mid index
            findMinimumCuts(mid, mid, cutsDp, s);
            // check for even length palindrome around mid index
            findMinimumCuts(mid - 1, mid, cutsDp, s);

        }
        return cutsDp[s.length() - 1];
    }

    public void findMinimumCuts(int startIndex, int endIndex, int[] cutsDp, String s) {
        for (int start = startIndex, end = endIndex;
            start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end); start--, end++) {
            int newCut = start == 0 ? 0 : cutsDp[start - 1] + 1;
            cutsDp[end] = Math.min(cutsDp[end], newCut);

        }
    }
}