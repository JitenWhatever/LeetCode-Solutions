/*
Given a binary array nums, return the maximum number of consecutive 1's in the array.

Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. 
The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:

Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.

Note:
    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        
        int maximumLength = 0, maximumLengthSoFar = 0;
        
        for(int num : nums) {
            if(num == 1) {
                ++maximumLengthSoFar;
            }
            else {
                maximumLengthSoFar = 0;
            }
            
            maximumLength = Math.max(maximumLength, maximumLengthSoFar);
            
        }
    
        return maximumLength;
    }
}