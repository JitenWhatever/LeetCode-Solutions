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