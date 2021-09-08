/*
Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once. You can return the answer in any order.

You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.

 

Example 1:

Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:

Input: nums = [-1,0]
Output: [-1,0]
Example 3:

Input: nums = [0,1]
Output: [1,0]
 

Constraints:

2 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each integer in nums will appear twice, only two integers will appear once.

*/

class Solution {
    public int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        
        Map<Integer, Integer> numberToCountMap = new HashMap<>();
        
        for (int num : nums) {
            numberToCountMap.put(num, numberToCountMap.getOrDefault(num, 0) + 1);
        }
        
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : numberToCountMap.entrySet()) {
            if (entry.getValue() == 1) {
                result[index++] = entry.getKey();
            }
            if (index == 2) {
                break;
            }
        }
        
        return result;
    }
}

class Solution {
    public int[] singleNumber(int[] nums) {
        int n = 0, m = 0, mask = 0;
        
        for(int num : nums) {
            mask ^= num;
        }
        
        mask ^= (mask & (mask - 1));
        
        for(int num : nums) {
            if((num & mask) != 0) {
                n ^= num;
            }
            else {
                m ^= num;
            }
        }
        
        int[] res = new int[2];
        
        res[0] = n;
        res[1] = m;
        
        return res;
    }
}

class Solution {
  public int[] singleNumber(int[] nums) {
    // difference between two numbers (x and y) which were seen only once
    int bitmask = 0;
    for (int num : nums) bitmask ^= num;

    // rightmost 1-bit diff between x and y
    int diff = bitmask & (-bitmask);

    int x = 0;
    // bitmask which will contain only x
    for (int num : nums) if ((num & diff) != 0) x ^= num;

    return new int[]{x, bitmask^x};
  }
}