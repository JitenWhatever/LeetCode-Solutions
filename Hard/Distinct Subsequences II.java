/*
Given a string s, count the number of distinct, non-empty subsequences of s .

Since the result may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: s = "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 

 

Note:

s contains only lowercase letters.
1 <= s.length <= 2000
*/

class Solution {
    public int distinctSubseqII(String S) {
        int MOD = 1_000_000_007;
        int N = S.length();
        int[] dp = new int[N+1];
        dp[0] = 1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < N; ++i) {
            int x = S.charAt(i) - 'a';
            dp[i+1] = dp[i] * 2 % MOD;
            if (last[x] >= 0)
                dp[i+1] -= dp[last[x]];
            dp[i+1] %= MOD;
            last[x] = i;
        }

        dp[N]--; // "" string
        if (dp[N] < 0) dp[N] += MOD;
        return dp[N];
    }

     public int distinctSubseqII(String S) {
        long end[] = new long[26], mod = (long)1e9 + 7;  // end[i] subsequence end with i;
        for (char c : S.toCharArray())
            end[c - 'a'] = Arrays.stream(end).sum()%mod + 1;
        return (int)(Arrays.stream(end).sum() % mod);
    }
}
