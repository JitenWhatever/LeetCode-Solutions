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
    

public:
    int numSquares(int n) {
        
        
        if(n < 4) {
            return n;
        }

        vector<int> coins;
        for(int num = 1;  num * num <= n; ++num) {
            coins.push_back(num * num);
        }
        
        vector<int> dp(n + 1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        
        for(int amount = 4; amount <= n; ++amount) {
            dp[amount] = amount;
            for(int coin : coins) {
                if(amount >= coin) {
                    dp[amount] = min(dp[amount], dp[amount - coin] + 1);
                }
            }
        }
        
        return dp[n];
    }
};