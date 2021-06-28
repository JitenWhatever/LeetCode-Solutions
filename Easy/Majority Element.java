/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
*/


class Solution {
    public int majorityElement(int[] nums) {
        
        int mindex = 0;
        int count = 1;
        for(int index = 0; index < nums.length; ++index) {
            if(nums[mindex] == nums[index]) {
                ++count;
            }
            else {
                --count;
                if(count == 0) {
                    count = 1;
                    mindex = index;
                }
            }
        }
        
        return nums[mindex];
        
    }
}

// Divide And Conquer

class Solution {
    public int majorityElement(int[] nums) {
        
        if(Objects.isNull(nums) || nums.length == 0) {
            return -1;
        }
        
        return divideAndConquer(nums, 0, nums.length - 1);
    }
    
    private int divideAndConquer(int[] nums, int left, int right) {
        
        if(left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        int leftMajor = divideAndConquer(nums, left, mid);
        int rightMajor = divideAndConquer(nums, mid + 1, right);
        
        int leftCount = getCount(nums, leftMajor, left, right);
        int rightCount = getCount(nums, rightMajor, left, right);
        
        
        return leftCount > rightCount ? leftMajor : rightMajor;
    }
    
    private int getCount(int[] nums, int num, int left, int right) {
        int count = 0;
        
        for(int index = left; index <= right; ++index) {
            if(nums[index] == num) {
                ++count;
            }
        }
        
        return count;
    }
}