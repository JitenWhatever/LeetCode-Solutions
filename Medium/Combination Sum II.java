/*
Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        
        Arrays.sort(candidates);
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
            if (index == start || nums[index] != nums[index - 1]) {
                currentCombination.add(nums[index]);
                backtrackCombinations(nums, remainingSum - nums[index], index + 1, currentCombination, combinations);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}