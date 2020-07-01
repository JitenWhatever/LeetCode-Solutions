/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/
// simple store each element in map with index and find the remaining number
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();
        int[] result = new int[2];
        
        for(int index = 0; index < nums.length; ++index) {
            if(hash.containsKey(target - nums[index])) {
                result[0] = hash.get(target - nums[index]);
                result[1] = index;
                break;
            }
            
            hash.put(nums[index], index);
        }
        
        return result;
    }
}