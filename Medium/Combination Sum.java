/**
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
Each element of candidate is unique.
1 <= target <= 500
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        result = new ArrayList<>();
        
        List<Integer> current = new ArrayList<>();
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
            current.add(candidates[i]);
            generate(candidates, i, target - candidates[i], current);
            current.remove(current.size() - 1);
        }
    }
}