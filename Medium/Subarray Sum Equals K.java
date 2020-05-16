/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Constraints:
    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        Integer sum = 0, subArrays = 0;;    
        hash.put(0, 1);
        
        for(Integer num : nums) {
            sum += num;
            if(hash.getOrDefault(sum - k, 0) != 0){
                subArrays += hash.get(sum - k);
            }
            
            hash.put(sum, hash.getOrDefault(sum, 0) + 1);
        }
        
        return subArrays;
    }
}

