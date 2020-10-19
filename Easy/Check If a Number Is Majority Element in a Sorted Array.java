/*
Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.

A majority element is an element that appears more than N/2 times in an array of length N.

Example 1:

Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
Output: true
Explanation: 
The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 > 9/2 is true.
Example 2:

Input: nums = [10,100,101,101], target = 101
Output: false
Explanation: 
The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 > 4/2 is false.
Note:

1 <= nums.length <= 1000
1 <= nums[i] <= 10^9
1 <= target <= 10^9
*/

class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int N = nums.length;

        int low = 0;
        int high = N - 1;

        while(low < high) {
            int mid = low + (high - low)/2;

            if(nums[mid] < target) {
                low = mid + 1;
            }
            else if(nums[mid] >= target){
                high = mid;
            }
        }

        int firstIndex = left;
        int lastIndex = firstIndex + N/2;

        return lastIndex < N && nums[lastIndex] == target;
    }
}