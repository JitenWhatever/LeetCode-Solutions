/**
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
  public List<List<Integer>> subsets(int[] nums) {
    
    List<List<Integer>> result = new ArrayList();
    
     int n = nums.length;

    for (int bit = 0; bit < (1 << n); ++bit) {

      List<Integer> curr = new ArrayList();
      for (int index = 0; index < n; ++index) {
          if((bit & (1 << index)) != 0) curr.add(nums[index]);
      }
      result.add(curr);
    }
    return result;
  }
}