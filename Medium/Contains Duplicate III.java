/*

Given an array of integers, find out whether there are two distinct indices i and j in the array 
such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

 

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 

Constraints:

0 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 104
0 <= t <= 231 - 1
*/


class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        hash = new TreeSet<>();
        
       int left = 0, right = 0;
        
        while(right < nums.length) {
            Long lb = hash.ceiling(1L*nums[right] - t);
            if(lb != null && lb <= 1L*nums[right] + t) {
                return true;
            }
            else {
                hash.add(1L*nums[right++]);
            }
            
            if(left < nums.length && hash.size() > k) {
                hash.remove(1L*nums[left++]);
            }
        }
        
        return false;
    }
    
    private TreeSet<Long> hash;
}