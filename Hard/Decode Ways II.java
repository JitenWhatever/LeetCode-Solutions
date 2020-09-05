/*
A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
*/

class Solution {
    public int numDecodings(String s) {
        
        long[] dp = new long[s.length() + 1] ;
        int MOD = 1000000007;
        
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        
        for(int len = 2; len <= s.length(); ++len) {
            char prev = s.charAt(len - 2);
            char curr = s.charAt(len - 1);
            
            if((curr - '0') > 0 && (curr - '0') < 10){
                dp[len] += dp[len - 1];
            }
            
            if(curr == '*') {
                dp[len] += 9*dp[len - 1];
            }
            
            // System.out.println(dp[len] + " : "  + dp[len - 1]);
            dp[len] += getFactor(prev, curr)*dp[len - 2];
            
            if(dp[len] == 0) {
                return 0;
            }
            
            dp[len] = dp[len]%MOD;
        }
        
        return (int)dp[s.length()];
        
    }
    
    private long getFactor(char prev, char curr) {
        if(prev == '*') {
            if(curr == '*') {
                return 15;
            }
            else if(curr >= '0' && curr <= '6') {
                return 2;
            }
            else if(curr >= '7' && curr <= '9') {
                return 1;
            }
            
        }
        else if(curr == '*') {
            if(prev == '0' || prev >= '3') {
                return 0;
            }
            else if(prev == '1') {
                return 9;
            }
            else if(prev == '2') {
                return 6;
            }
        }
        
        return (10 * (prev - '0') + (curr - '0')) <= 26 && prev != '0' ? 1: 0;
    }
    
}