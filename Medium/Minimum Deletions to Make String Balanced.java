/*
You are given a string s consisting only of characters 'a' and 'b'​​​​.

You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.

Return the minimum number of deletions needed to make s balanced.

 

Example 1:

Input: s = "aababbab"
Output: 2
Explanation: You can either:
Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
Example 2:

Input: s = "bbaaaaabb"
Output: 2
Explanation: The only solution is to delete the first two characters.
 

Constraints:

1 <= s.length <= 105
s[i] is 'a' or 'b'​​.
*/


class Solution {
    public int minimumDeletions(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        int[] dp = new int[s.length() + 1]; // dp[i] min chars to remove to make string (0, i) valid.
        
        int countOfb = 0;
        for (int index = 0; index < s.length(); ++index) {
            if (s.charAt(index) == 'a') {
                dp[index + 1] = Math.min(dp[index] + 1, countOfb);
            } else {
                dp[index + 1] = dp[index];
                ++countOfb;
            }
        }
        
        return dp[s.length()];
    }
}

class Solution {
    public int minimumDeletions(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
            
        Stack<Character> stck = new Stack<>();
        
        int minCharsToDelete = 0;
        
        for (int index = s.length() - 1; index >= 0; --index) {
            if (!stck.isEmpty() && stck.peek() == 'a' && s.charAt(index) == 'b') {
                stck.pop();
                ++minCharsToDelete;
            } else {
                stck.push(s.charAt(index));
            }
        }
        
        
        return minCharsToDelete;
    }
}