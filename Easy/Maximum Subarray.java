/*
Given an integer array nums, find the contiguous subarray (containing at least one number) 
which has the largest sum and return its sum.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
 

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 
Follow up: If you have figured out the O(n) solution, \
try coding another solution using the divide and conquer approach, which is more subtle.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            for (int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }
        
        return maxSubarray;
    }
}

class Solution {
    public int maxSubArray(int[] nums) {
        Integer result = Integer.MIN_VALUE, max_so_far = 0;
        
        for(Integer num : nums){
            max_so_far = Math.max(num, max_so_far + num);
            result = Math.max(result, max_so_far);
        }
        
        return result;
    }
}


class Solution {
    public int maxSubArray(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    private int divideAndConquer(int[] nums, int left, int right) {
        
        if(left >  right) {
            return Integer.MIN_VALUE;
        }
        
        int mid = (right + left) / 2;
        
        int leftMax = 0, rightMax = 0, currentSum = 0;
        
        for(int index = mid - 1; index >= left; --index) {
            currentSum += nums[index];
            leftMax = Math.max(leftMax, currentSum);
        }
        
        currentSum = 0;
        
         for(int index = mid + 1; index <= right; ++index) {
            currentSum += nums[index];
            rightMax = Math.max(rightMax, currentSum);
        }
    
        int max = nums[mid] + leftMax + rightMax; // combined sum 
        
        int leftMaxSum = divideAndConquer(nums, left, mid - 1);
        int rightMaxSum = divideAndConquer(nums, mid + 1, right);
        
        return Math.max(max, Math.max(leftMaxSum, rightMaxSum));
    }
}