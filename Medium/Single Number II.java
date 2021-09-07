/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. 
Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,3,2]
Output: 3
Example 2:

Input: nums = [0,1,0,1,0,1,99]
Output: 99
 

Constraints:

1 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each element in nums appears exactly three times except for one element which appears once.
*/

class Solution {
    public int singleNumber(int[] nums) {
        int res = 0, count = 0, mask = 0;
        
        for(int bit = 31; bit >= 0; --bit) {
            mask = (1<<bit);
            count = 0;
            for(int num : nums) {
                if((num & mask) != 0) {
                    ++count;
                }
            }
            
            
            if((count % 3) != 0) {
                res |= mask;
            }
            
        }
        
        return res;
    }
}

class Solution {
  public int singleNumber(int[] nums) {
    Set<Long> set = new HashSet<>();
    long sumSet = 0, sumArray = 0;
    for(int n : nums) {
      sumArray += n;
      set.add((long)n);
    }
    for(Long s : set) sumSet += s;
    return (int)((3 * sumSet - sumArray) / 2);
  }
}


class Solution {
  public int singleNumber(int[] nums) {
    HashMap<Integer, Integer> hashmap = new HashMap<>();
    for (int num : nums)
      hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);

    for (int k : hashmap.keySet())
      if (hashmap.get(k) == 1) return k;
    return -1;
  }
}


class Solution {
  public int singleNumber(int[] nums) {
    int seenOnce = 0, seenTwice = 0;

    for (int num : nums) {
      // first appearence: 
      // add num to seen_once 
      // don't add to seen_twice because of presence in seen_once

      // second appearance: 
      // remove num from seen_once 
      // add num to seen_twice

      // third appearance: 
      // don't add to seen_once because of presence in seen_twice
      // remove num from seen_twice
      seenOnce = ~seenTwice & (seenOnce ^ num);
      seenTwice = ~seenOnce & (seenTwice ^ num);
    }

    return seenOnce;
  }
}