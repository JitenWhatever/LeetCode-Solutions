/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?.
Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> result;
        if(nums.size() < 3) {
            return result;
        }
        sort(nums.begin(), nums.end());
        
        for(int index = 0; index < nums.size() - 2; ++index) {
            if(index == 0 || (index > 0 && nums[index] != nums[index -1])) {
                int low = index + 1, high = nums.size() - 1;
                
                int target = -nums[index];
                while(low < high) {
                    if(nums[low] + nums[high] == target) {
                        result.push_back({nums[index], nums[low], nums[high]});
                        while(low < high && nums[low] == nums[low + 1]) {
                            ++low;
                        }
                        
                        while(low < high && nums[high] == nums[high - 1]) {
                            --high;
                        }
                        ++low;
                        --high;
                    }
                    else if(nums[low] + nums[high] > target) {
                        --high;
                    }
                    else {
                        ++low;
                    }
                }
            }
        }
        
        return result;
    }
};

