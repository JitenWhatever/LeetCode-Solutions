/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

class Solution {
    public int numDecodings(String s) {
        
        int[] dp = new int[s.length() + 1]; // dp[x] numner of ways to decode string of length x.
        
        dp[0] = 1; // empty string
        dp[1] = s.charAt(0) - '0' > 0 ? 1 : 0; // 1 ways if it's a valid char otherwise 0.
        
        for(int len = 2; len <= s.length(); ++len) {
            int oneDigit = Integer.parseInt(s.substring(len - 1, len));
            int twoDigits = Integer.parseInt(s.substring(len - 2, len));
            
            if(oneDigit > 0 && oneDigit < 10) {
                dp[len] += dp[len - 1];
            }
            
            if(twoDigits > 9 && twoDigits <= 26) {
                dp[len] += dp[len - 2];
            }
            // System.out.println(oneDigit + " : " + twoDigits);
            
        }
        
        return dp[s.length()];
    }
}