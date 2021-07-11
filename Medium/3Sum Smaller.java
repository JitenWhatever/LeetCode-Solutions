/*
Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i != j != k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

 

Example 1:

Input: nums = [-2,0,1,3], target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]
Example 2:

Input: nums = [], target = 0
Output: 0
Example 3:

Input: nums = [0], target = 0
Output: 0
 

Constraints:

n == nums.length
0 <= n <= 3500
-100 <= nums[i] <= 100
-100 <= target <= 100
*/

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length < 3) {
            return 0;
        }
        
        
        Arrays.sort(nums);
        
        int triplets = 0;
        
        for (int index = 0; index < nums.length - 2; ++index) {
            int left = index + 1, right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                
                if (sum < target) {
                   triplets += (right - left) ;
                    ++left;
                } else {
                    --right;
                }
            }
        }
        
        return triplets;
    }
    
}