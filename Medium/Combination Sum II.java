/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        
        Arrays.sort(candidates);
        generate(candidates, 0, target, current);
        return result;
    }
    
    private List<List<Integer>> result;
    
    private void generate(int[] candidates, int index, int target, List<Integer> current) {
        if(target == 0) {
            result.add(new ArrayList(current));
            return ;
        }
        
        if(target < 0) {
            return ;
        }
        
        for(int i = index; i < candidates.length; ++i) {
            if(i == index || candidates[i] != candidates[i - 1]) {
                current.add(candidates[i]);
                generate(candidates, i + 1, target - candidates[i], current);
                current.remove(current.size() - 1);
            }
        }
    }
}