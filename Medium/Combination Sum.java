/**
Given an array of distinct integers candidates and a target integer target, 
return a list of all unique combinations of candidates where the chosen numbers sum to target. 
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. 
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
Example 4:

Input: candidates = [1], target = 1
Output: [[1]]
Example 5:

Input: candidates = [1], target = 2
Output: [[1,1]]
 

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        
        backtrackCombinations(candidates, target, 0, new ArrayList<>(), combinations);
        
        return combinations;
    }
    
    private void backtrackCombinations(int[] nums, int remainingSum, int start , List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (remainingSum == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return ;
        }
        
        if (remainingSum < 0) { // current number cannot be used
            return ;
        }
        
        for (int index = start; index < nums.length; ++index) {
            currentCombination.add(nums[index]);
            backtrackCombinations(nums, remainingSum - nums[index], index, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}