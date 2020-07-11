/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> result;
        
        for(int bit = 0; bit < 1<<nums.size(); ++bit) {
            
            vector<int>  dummy;
            for(int index = 0; index < nums.size(); ++index) {
                
                if(bit & (1 << index) ) {
                    dummy.push_back(nums[index]);
                }
            }
            
            result.push_back(dummy);
            
        }
        
        return result;
    }
};