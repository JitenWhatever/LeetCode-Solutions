/*
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

 

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
 

Constraints:

1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        
        if (Objects.isNull(nums) || nums.length < 4) {
            return quadruplets;
        }
        
        Arrays.sort(nums);
        
        for (int index_i = 0; index_i < nums.length - 3; ++index_i) {
            if(index_i == 0 || nums[index_i] != nums[index_i - 1]) {
                for (int index_j = index_i + 1; index_j < nums.length - 2; ++index_j) {
                    if (index_j == index_i + 1  || nums[index_j] != nums[index_j - 1]) {

                        int left = index_j + 1, right = nums.length - 1;

                        while (left < right) {
                            if (target == nums[index_i] + nums[index_j] + nums[left] + nums[right]) {
                                quadruplets.add(Arrays.asList(nums[index_i], nums[index_j], nums[left], nums[right]));

                                while (left < right && nums[left] == nums[left + 1]) {
                                    ++left;
                                }

                                while (left < right && nums[right] == nums[right - 1]) {
                                    --right;
                                }

                                ++left;
                                --right;
                            } else if (target > nums[index_i] + nums[index_j] + nums[left] + nums[right]) {
                                ++left;
                            } else {
                                --right;
                            }
                        }
                    }
                }
            }
        }
            
        return quadruplets;
    }
}