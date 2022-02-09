import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

0 <= i < j < nums.length
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.

Example 1:

Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:

Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:

Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1)

Constraints:

1 <= nums.length <= 10^4
-10^7 <= nums[i] <= 10^7
0 <= k <= 10^7
*/

class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> hash = new HashMap<>();
        
        for(int num : nums) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }
        
        int result = 0;
        for(int key : hash.keySet()) {
            if(k > 0 && hash.containsKey(key + k) || k == 0 && hash.get(key) > 1) {
                ++result;
            }
        }
        
        return result;
    }

    public int findPairs1(int[] nums, int k) {

        Arrays.sort(nums);

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                if (Math.abs(nums[j] - nums[i]) == k)
                    result++;
            }
        }

        return result;
    }

    public int findPairs2(int[] nums, int k) {

        Arrays.sort(nums);

        int left = 0, right = 1;
        int result = 0;

        while (left < nums.length && right < nums.length) {
            if (left == right || nums[right] - nums[left] < k) {
                // List item 1 in the text
                right++;      
            } else if (nums[right] - nums[left] > k) {
                // List item 2 in the text
                left++;       
            } else {
                // List item 3 in the text
                left++;
                result++;
                while (left < nums.length && nums[left] == nums[left - 1])
                    left++;
            }
        }
        return result;
    }

}