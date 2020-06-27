/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9
*/

class Solution {
    public int numSquares(int n) {
        
        if(n < 4) {
            return n;
        }
        
        List<Integer> coins = new ArrayList<>();
        
        for(int num = 1; num * num <= n; ++num) {
            coins.add(num * num);
        }
        
        
        int[] dp = new int[n + 1];
        for(int num = 0; num < 4; ++num) {
            dp[num] = num;
        }
        
        dp[4] = 1;
        
        for(int amount = 5; amount <= n; ++amount) {
            dp[amount] = amount;
            for(int coin : coins) {
                if(amount >= coin) {
                    dp[amount] = Math.min(dp[amount], dp[amount - coin] + 1);
                }
            }
        }
        
        return dp[n];
    }
}