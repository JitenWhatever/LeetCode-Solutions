import java.util.ArrayList;
import java.util.List;

/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 
Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/

class Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    
    List<List<Integer>> result = new ArrayList<>();
    
     int n = nums.length;

    for (int bit = 0; bit < (1 << n); ++bit) {

      List<Integer> curr = new ArrayList<>();
      for (int index = 0; index < n; ++index) {
          if((bit & (1 << index)) != 0) curr.add(nums[index]);
      }
      result.add(curr);
    }
    return result;
  }

  // Time complexity: O(N×2^N)
  // Space complexity: O(N×2^N)
  public List<List<Integer>> subsets1(int[] nums) {
    List<List<Integer>> output = new ArrayList<>();
    output.add(new ArrayList<Integer>());

    for (int num : nums) {
      List<List<Integer>> newSubsets = new ArrayList<>();
      for (List<Integer> curr : output) {
        newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
      }
      for (List<Integer> curr : newSubsets) {
        output.add(curr);
      }
    }
    return output;
  }


   // Time complexity: O(N×2^N)
  // Space complexity: O(N)
  List<List<Integer>> output = new ArrayList<>();
  int n, k;

  public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
    // if the combination is done
    if (curr.size() == k) {
      output.add(new ArrayList<>(curr));
      return;
    }
    for (int i = first; i < n; ++i) {
      // add i into the current combination
      curr.add(nums[i]);
      // use next integers to complete the combination
      backtrack(i + 1, curr, nums);
      // backtrack
      curr.remove(curr.size() - 1);
    }
  }

  public List<List<Integer>> subsets2(int[] nums) {
    n = nums.length;
    for (k = 0; k < n + 1; ++k) {
      backtrack(0, new ArrayList<Integer>(), nums);
    }
    return output;
  }
}