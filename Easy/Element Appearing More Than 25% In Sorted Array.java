/*
Given an integer array sorted in non-decreasing order, 
there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.

 

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
 

Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
*/

class Solution {
    public int findSpecialInteger(int[] nums) {
       int count = 1, req = nums.length/4;
        
        for(int index = 1; index < nums.length; ++index) {
            if(nums[index] == nums[index - 1]) {
                count++;
            }
            else {
                count = 1;
            }
            
            if(count > req) {
                return nums[index];
            }
        }
        
        return nums[0];
    }
}