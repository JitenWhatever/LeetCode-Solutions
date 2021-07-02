/*
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 

Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 

Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
*/


class Solution {
    public int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1;
        int[] result = new int[nums.length];
        for(int index = nums.length - 1; index >= 0; --index) {
            if(nums[l] * nums[l] > nums[r] * nums[r]) {
                result[index] = nums[l]*nums[l++];
            } else {
                result[index] = nums[r]*nums[r--];
            }
        }
        return result;
    }
}