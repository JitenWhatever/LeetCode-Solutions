import java.util.HashMap;
import java.util.Map;

/*
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 
Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
*/

class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
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