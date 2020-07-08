/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList();
        
        if(nums == null || nums.length < 4) {
            return result;
        }
        
        Arrays.sort(nums);
        // for(int num : nums) {
        //      System.out.println(num);
        // }
       
        for(int index_a = 0; index_a < nums.length - 3; ++index_a) {
            
            if(index_a > 0 && nums[index_a] == nums[index_a - 1]) {
                continue;
            }
                
            for(int index_b = index_a + 1; index_b < nums.length - 2; ++index_b) {
                    
                if(index_b > index_a + 1 && nums[index_b] == nums[index_b - 1]) {
                    continue;
                }
                        
                int low = index_b + 1, high = nums.length - 1;
                        
                while(low < high) {
                    int sum = nums[index_a] + nums[index_b] + nums[low] + nums[high];
                            
                    if(sum == target) {
                        result.add(Arrays.asList(nums[index_a], nums[index_b], nums[low], nums[high]));
                            while(low < high && nums[low] == nums[low + 1]) {
                                ++low;
                            }
                            while(low < high && nums[high] == nums[high - 1]) {
                                --high;
                            }                                
                            ++low;
                            --high;
                        }
                        else if(sum < target) {
                            ++low;
                        }
                        else {
                            --high;
                        }
                    }
                }
            }
        return result;
    }
}