/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 

Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
*/


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length < 3) {
            return 0;
        }
        
        int closestSum = nums[0] + nums[1] + nums[2];
        
        Arrays.sort(nums);
        
        for (int index = 0; index < nums.length - 2; ++index) {
            int left = index + 1, right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                
                if (sum < target) {
                    ++left;
                } else {
                    --right;
                }
                
                if (Math.abs(target - sum) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
            } 
        }
        
        return closestSum;
    }
}