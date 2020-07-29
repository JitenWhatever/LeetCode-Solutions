/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        
        if(prices.size() < 2) {
            return 0;
        }
        vector<vector<int>> dp(prices.size(), vector<int>(2));
        //dp[i][0] maximum profit on day i holding 0 stocks (sell)
        //dp[i][1] maximum profit on day i holding 1 stocks (buy)
        
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = max(0, dp[0][1] + prices[1]); // didn't buy at 0, and don't buy at 1 or sell at 1
        dp[1][1] = max(dp[0][1], -prices[1]);   // buy at 0, do nothing or do nothing at 0 and buy at 1
        
        for(int day = 2; day < prices.size(); ++day) {
            dp[day][0] = max(dp[day - 1][0], dp[day - 1][1] + prices[day]);
            dp[day][1] = max(dp[day - 1][1], dp[day - 2][0] - prices[day]);
        }
        
        
        return dp[prices.size() - 1][0];
    }
};