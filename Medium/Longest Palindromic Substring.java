/*
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/


class Solution {
    public String longestPalindrome(String s) {
        
        if(s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0, end = 0;
        
        for(int index = 0; index < s.length(); ++index) {
            int len1 = getPalindromelenthFromCenter(index, index, s);
            int len2 = getPalindromelenthFromCenter(index, index + 1, s);
            
            int len = Math.max(len1, len2);
            
            if(len > end - start) {
                start = index - (len - 1)/2;
                end = index + len/2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    int getPalindromelenthFromCenter(int low, int high, String s) {
       
        while(low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            --low;
            ++high;
        }
        
        return (high - low - 1);
    }

    public String longestPalindrome(String s) {
    int n = s.length();
    int left = 0, right = 0;
        
    boolean[][] dp = new boolean[n][n];
    // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome    
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                    
            if (dp[i][j] && (j - i + 1 > res.length())) {
                left = i;
                right = j + 1;
            }
        }
    }
        
    return s.substring(left, right);
    }
}

public class Solution {
private int lo, maxLen;

public String longestPalindrome(String s) {
	int len = s.length();
	if (len < 2)
		return s;
	
    for (int i = 0; i < len-1; i++) {
     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
     	extendPalindrome(s, i, i+1); //assume even length.
    }
    return s.substring(lo, lo + maxLen);
}

private void extendPalindrome(String s, int j, int k) {
	while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
		j--;
		k++;
	}
	if (maxLen < k - j - 1) {
		lo = j + 1;
		maxLen = k - j - 1;
	}
}}