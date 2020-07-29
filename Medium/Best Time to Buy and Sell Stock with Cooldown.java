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
    public int maxProfit(int[] prices) {
        if(prices.length < 2) {
            return 0;
        }
        
        int[][] dp = new int[prices.length][2];
        
        // dp[day][0] maximum profit on a day holding 0 stock; 
        // dp[day][1] maximum profit on day holding 1 stock;
        
        dp[0][0] = 0 ;// do nothing at day 0
        dp[0][1] = -prices[0]; // buy at day 0
        dp[1][0] = Math.max(0, dp[0][1] + prices[1]) ;// don't but at 0 and do nothing, sell at day 1
        dp[1][1] = Math.max(dp[0][1], -prices[1]); // but at 0 and do nothing, don't buy at 0, buy at 1
        
        for(int day = 2; day < prices.length; ++day) {
            dp[day][0] = Math.max(dp[day - 1][0], dp[day - 1][1] + prices[day]);
            dp[day][1] = Math.max(dp[day - 1][1], dp[day - 2][0] - prices[day]);
        }
        
        return dp[prices.length - 1][0];
    }
}

