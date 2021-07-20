/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/


class Solution {
    
    private Integer[] dp;
    
    public int rob(int[] nums) {
        
        this.dp = new Integer[100];
            
        return this.recurse(0, nums);
    }
    
    private int recurse(int index, int[] nums) {
    
        if (index >= nums.length) {
            return 0;
        }
        
        if (Objects.nonNull(dp[index])) {
            return dp[index];
        }
        
        return dp[index] = Math.max(recurse(index + 1, nums), nums[index] + recurse(index + 2, nums));
    }
}

class Solution {
    public int rob(int[] nums) {
        
        if (nums.length < 2) {
            return nums[0];
        }
        if (nums.length < 3) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length]; // dp[i] maximum profit after robbing 0 to i houses
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        
        for (int house = 2; house < nums.length; ++house) {
            dp[house] = Math.max(nums[house] + dp[house - 2], dp[house - 1]);
        }
        
        return dp[nums.length - 1];
    }
}