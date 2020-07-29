/*
Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        
        if(prices.length < 2) {
            return 0;
        }
        
        System.out.println(prices.length);
        
        if(k >= prices.length) {
            int profit_with_stock = Integer.MIN_VALUE, profit_without_stock = 0;
            for(int price : prices) {
                profit_with_stock = Math.max(profit_with_stock, profit_without_stock - price);
                profit_without_stock = Math.max(profit_without_stock, profit_with_stock + price);
            }
            
            return profit_without_stock;
        }
        
        int[][] dp = new int[k + 1][prices.length]; // dp[t][day] maximum profit  on day completed t transaction
        
        for(int t = 1; t <= k; t++) {
            int maxDiff = -prices[0];
            for(int day = 1; day < prices.length; ++day) {
                dp[t][day] = Math.max(dp[t][day - 1], prices[day] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[t - 1][day] - prices[day]);
            }
        }
        
        return dp[k][prices.length - 1];
    }
}