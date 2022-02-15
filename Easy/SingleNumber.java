import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:

Input: nums = [2,2,1]
Output: 1
Example 2:

Input: nums = [4,1,2,1,2]
Output: 4
Example 3:

Input: nums = [1]
Output: 1
 

Constraints:

1 <= nums.length <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4
Each element in the array appears twice except for one element which appears only once.
*/

class SingleNumber {
  
    public int singleNumber(int[] nums) {
        int singleNumber = 0;
        for(int num : nums){
            singleNumber ^= num;
        }
        
        return singleNumber;
    }

  public int singleNumber1(int[] nums) {
    List<Integer> no_duplicate_list = new ArrayList<>();

    for (int i : nums) {
      if (!no_duplicate_list.contains(i)) {
        no_duplicate_list.add(i);
      } else {
        no_duplicate_list.remove(Integer.valueOf(i));
      }
    }
    return no_duplicate_list.get(0);
  }


  public int singleNumber2(int[] nums) {
    HashMap<Integer, Integer> hash_table = new HashMap<>();

    for (int i : nums) {
      hash_table.put(i, hash_table.getOrDefault(i, 0) + 1);
    }
    for (int i : nums) {
      if (hash_table.get(i) == 1) {
        return i;
      }
    }
    return 0;
  }


  public int singleNumber3(int[] nums) {
    int sumOfSet = 0, sumOfNums = 0;
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
      if (!set.contains(num)) {
        set.add(num);
        sumOfSet += num;
      }
      sumOfNums += num;
    }
    return 2 * sumOfSet - sumOfNums;
  }
}