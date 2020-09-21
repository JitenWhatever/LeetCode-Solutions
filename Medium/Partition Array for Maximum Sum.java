/*
Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. 
After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

 

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
*/

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        int[] dp = new int[arr.length]; // dp[index] maximum sum of sub array ending at index  
        
        dp[0] = arr[0];
        int max = arr[0];
        for(int i = 1; i < k; i++) {
            max = Math.max(max, arr[i]);
            dp[i] = max*(i + 1);
        }
        
        
        for(int i = k; i < arr.length; ++i) {
            max = arr[i];
            for(int len = 1; len <= k; ++len) {
                max = Math.max(max, arr[i - len + 1]);
                dp[i] = Math.max(dp[i], dp[i - len] + max*len);
            }
        }
        
        return dp[arr.length - 1];
    }
}

