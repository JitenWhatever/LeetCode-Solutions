/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:

Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:

Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000
*/

class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        Integer sum = 0, maxSubArray = 0;    
        hash.put(0, -1);
        
        for(Integer index = 0; index < nums.length; ++index) {
            sum += (nums[index] == 0 ? -1 : 1);
            if(hash.containsKey(sum)) {
                maxSubArray = Math.max(maxSubArray, index - hash.get(sum));
            }
            else {
                 hash.put(sum, index);
            }
        }
        
        return maxSubArray;
    }
}