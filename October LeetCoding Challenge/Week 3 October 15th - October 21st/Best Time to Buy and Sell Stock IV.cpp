/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 

Constraints:

0 <= k <= 109
0 <= prices.length <= 104
0 <= prices[i] <= 1000
*/

class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        if(prices.size() < 2) {
            return 0;
        }   
        
        int days = prices.size();
        
        if(k >= days) {
            int profit_with_stock = INT_MIN, profit_without_stock = 0;
            
            for(int price : prices) {
                profit_without_stock = max(profit_without_stock, profit_with_stock + price);
                profit_with_stock = max(profit_with_stock, profit_without_stock - price);
            }
            
            return profit_without_stock;
        }
        
        vector<vector<int>> dp(k + 1, vector<int>(days));
        
        for(int t = 1; t <= k; ++t) {
            int maxDiff = -prices[0];
            for(int day = 1; day < days; ++day) {
                dp[t][day] = max(dp[t][day - 1], maxDiff + prices[day]);
                maxDiff = max(maxDiff, dp[t - 1][day] - prices[day]);
            }
        }
        
        return dp[k][days - 1];
    }
};