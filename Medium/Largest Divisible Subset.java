/*
Given a set of distinct positive integers, find the largest 
subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)

Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
*/

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
        int n = nums.length;
        
     
        
        if(n == 0) {
            return  new ArrayList<>();
        }
        
        if(n < 2) {
            return Arrays.asList(nums[0]);
        }
        
        Arrays.sort(nums);
        int[] dp = new int[n];
        int[] prevIndex  = new int[n];
        
        Arrays.fill(dp, 1);
        Arrays.fill(prevIndex, -1);
        
        int max_index = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < i; ++j) {
                if(nums[i]%nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prevIndex[i] = j;
                }
            }
            
            if(dp[max_index] < dp[i]) {
                max_index = i;
            }
        }
        
         List<Integer> result = new ArrayList<>();
        while(max_index >= 0) {
            result.add(nums[max_index]);
            max_index = prevIndex[max_index];
        }
        
        return result;
    }
}