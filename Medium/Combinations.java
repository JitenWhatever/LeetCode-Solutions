/*
Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
1 <= k <= n
*/

//26ms O(k*NCK)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> combinations = new ArrayList<>();
        
        generateCombinations(n, 1, k, new ArrayList<>(), combinations);
        
        return combinations;
    }
    
    
    private void generateCombinations(int n, int currentNumber, int k, List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (k == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return ;
        }
        
        if (k < 0) {
            return ;
        }
        
        for (int number = currentNumber; number <= n; ++number) {
            currentCombination.add(number);
            generateCombinations(n, number + 1, k - 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}