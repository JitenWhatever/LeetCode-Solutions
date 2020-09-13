/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int maxProductSoFar = nums[0];
        int minProductSoFar = nums[0];
        
        for(int index = 1; index < nums.length; ++index) {
            int maxprod =  maxProductSoFar;
            maxProductSoFar = Math.max(nums[index], Math.max(maxProductSoFar*nums[index], minProductSoFar * nums[index]));
            minProductSoFar = Math.min(nums[index], Math.min(maxprod*nums[index], minProductSoFar * nums[index]));
            
            maxProduct = Math.max(maxProduct, maxProductSoFar);
        }
        
        return maxProduct;
    }
}