/*
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].
*/

class Solution {
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0) {
            return 0;
        }
        
        int[] dp = new int[pairs.length]; // max len ending at pair i;
        
        Arrays.fill(dp, 1);
        
        Arrays.sort(pairs, (a, b) -> {
            return a[0] - b[0];
            });
        
        int maxLen = 0;
        for(int i = 0; i < pairs.length; ++i) {
            for(int j = 0; j < i; ++j) {
                if(pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
}