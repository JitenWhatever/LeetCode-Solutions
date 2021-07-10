/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2:

Input: nums = []
Output: []
Example 3:

Input: nums = [0]
Output: []
 

Constraints:

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/



// O(n^2)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        
        if (Objects.isNull(nums) || nums.length < 3) {
            return triplets; 
        }
        
        Arrays.sort(nums);  // Array Modification
        
        for (int index = 0; index < nums.length - 2 && nums[index] <= 0; ++index) {
            if(index == 0 || nums[index] != nums[index - 1]) {
                int left = index + 1, right = nums.length - 1;
                
                while(left < right) {
                    if (nums[index] + nums[left] + nums[right] == 0) {
                        triplets.add(Arrays.asList(nums[index], nums[left], nums[right]));
                        
                        while (left < right && nums[left] == nums[left + 1]) {
                            ++left;
                        }
                        
                        while (left < right && nums[right] == nums[right - 1]) {
                            --right;
                        }
                        
                        ++left;
                        --right;
                    } else if (nums[index] + nums[left] + nums[right] > 0) {
                        --right;
                    } else {
                        ++left;
                    }
                }
            }
        }
        return triplets;
    }
}


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        
        if (Objects.isNull(nums) || nums.length < 3) {
            return triplets; 
        }
        
        Arrays.sort(nums);
        
        for (int index = 0; index < nums.length - 2 && nums[index] <= 0; ++index) {
            if(index == 0 || nums[index] != nums[index - 1]) {
                Set<Integer> seen = new HashSet<>();
                
                for(int itr = index + 1; itr < nums.length; ++itr) {
                    int target = -(nums[index] + nums[itr]);
                    
                    if (seen.contains(target)) {
                        triplets.add(Arrays.asList(nums[index], nums[itr], target));
                        while(itr + 1  < nums.length && nums[itr] == nums[itr + 1]) {
                            ++itr;
                        }
                    }
                    
                    seen.add(nums[itr]);
                }
            }
        }
        
        
        return triplets;
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> triplets = new HashSet<>();
        Set<Integer> repeat = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        
        if (Objects.isNull(nums) || nums.length < 3) {
            return new ArrayList(triplets); 
        }
            
        for (int index = 0; index < nums.length; ++index) {
            if (repeat.add(nums[index])) {
                for (int itr = index + 1; itr < nums.length; ++itr) {
                    int target = -(nums[index] + nums[itr]);
                    
                    if (seen.containsKey(target) && seen.get(target) == index) {
                        List<Integer> triplet = Arrays.asList(nums[index], nums[itr], target);
                        Collections.sort(triplet);
                        triplets.add(triplet);
                    }
                    
                    seen.put(nums[itr], index);
                }
            }
        }
        
        
        
        return new ArrayList(triplets);
    }
}