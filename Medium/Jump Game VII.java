/*
You are given a 0-indexed binary string s and two integers minJump and maxJump. 
In the beginning, you are standing at index 0, which is equal to '0'. 
You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

 

Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
 

Constraints:

2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
*/


class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int N = s.length();
        boolean[] canReach = new boolean[N];
        canReach[0] = (s.charAt(0) == '0');
        int prevPositionToIndex = 0;
        for (int index = 1; index < N; ++index) {
            if (index >= minJump && canReach[index - minJump]) { // position from which can jump to index
                ++prevPositionToIndex;
            }
            if (index > maxJump && canReach[index - maxJump - 1]) { // remove invalid positions  first condition
                --prevPositionToIndex;
            }
            
            canReach[index] = prevPositionToIndex > 0 && s.charAt(index) == '0';  // any index from which can jump to index and index is available
        }
        
        return canReach[N - 1];
    }
}


class Solution {
//   DFS with memo
    public boolean canReach(String s, int minJ, int maxJ) {
        if (s.charAt(s.length()-1) == '1') return false;
        Boolean[] memo = new Boolean[s.length()];
        return dfs(s, 0, minJ, maxJ, memo);
    }

    private boolean dfs(String s, int idx, int minJ, int maxJ, Boolean[] memo) {
        if (memo[idx] != null) return memo[idx];
        if (idx == s.length() - 1) return true;
        boolean reachLast = false;
        for (int j = idx+minJ; j <= Math.min(idx+maxJ, s.length()-1); j++) {
            if (s.charAt(j) == '1') continue; 
            reachLast |= dfs(s, j, minJ, maxJ, memo);
            
        }
        memo[idx] = reachLast;
        return memo[idx];
    }
    
    
    // 2. DP
    public boolean canReach(String s, int minJ, int maxJ) {
        int n = s.length();
        if (s.charAt(n-1) == '1') return false;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = i-minJ; j >= Math.max(0, i-maxJ); j--) {
                if (s.charAt(j) == '1') continue;
                if (dp[j]) dp[i] = true;
            }
        }
        return dp[n-1];
    }
}