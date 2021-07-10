/*

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. 
For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; // length of longest subsequence ending at i
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for(int i = 0; i < nums.length; ++i) {
            for(int j = i - 1; j >= 0; --j) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        
        List<Integer> subSeq = new ArrayList<>(); // length matter sequence might not be correct. [3, 4, 5, 1]
        
        subSeq.add(nums[0]);
        
        for (int index = 1; index < nums.length; ++index) {
            if (nums[index] > subSeq.get(subSeq.size() - 1)) {
                subSeq.add(nums[index]);
            } else {
                int itr = 0;
                
                while (nums[index] > subSeq.get(itr)) {
                    ++itr;
                }
                
                subSeq.set(itr, nums[index]);
            }
        }
        return subSeq.size();
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        
        List<Integer> subSeq = new ArrayList<>(); // length matter sequence might not be correct. [3, 4, 5, 1]
        
        subSeq.add(nums[0]);
        
        for (int index = 1; index < nums.length; ++index) {
            if (nums[index] > subSeq.get(subSeq.size() - 1)) {
                subSeq.add(nums[index]);
            } else {
                int itr = lowerBound(subSeq, nums[index]);
                subSeq.set(itr, nums[index]);
            }
        }
        return subSeq.size();
    }
    
    private int lowerBound(List<Integer> subSeq, int num) {
        int low = 0, high = subSeq.size() - 1;
        while(low < high) {
            int mid = low + (high - low)/2;
            if (subSeq.get(mid) == num) {
                return mid;
            }
            
            if (subSeq.get(mid) < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}


class Solution {
    public int lengthOfLIS(int[] nums) {
        
        List<Integer> subSeq = new ArrayList<>(); // length matter sequence might not be correct. [3, 4, 5, 1]
        
        for (int index = 0; index < nums.length; ++index) {
            int itr = Collections.binarySearch(subSeq, nums[index]);
            if(itr < 0) {
                itr = ~itr;
            }
            if (itr == subSeq.size()) {
                subSeq.add(nums[index]);
            } else {
                subSeq.set(itr, nums[index]);
            }
        }
        return subSeq.size();
    }
}