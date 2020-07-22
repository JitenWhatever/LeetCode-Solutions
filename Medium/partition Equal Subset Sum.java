/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
*/

class Solution {
    public boolean canPartition(int[] nums) {
        long totalSum = 0;
        for(int num : nums) {
            totalSum += num;
        }
        
        if(totalSum % 2 != 0) {
            return false;
        }
        totalSum = totalSum / 2;
        
        // return recursion(nums, 0, 0, sum); //TLE
        // return helperBy2DDP(nums, totalSum); // 64ms
        // return helperBy1DDP(nums, totalSum); // 19ms
        // return helperBySetGeneration(nums, totalSum); // doesn't work as size of array is large
    }
    
    private boolean helperBySetGeneration(int[] nums, long totalSum) {
        
        for(long bit = 0; bit < 1<<nums.length; ++bit) {
            long sum = 0;
            for(int index = 0; index < nums.length; ++index) {
                long num = (bit&(1<<index));
                if(num != 0) {
                    sum += nums[index];
                }
            }
            System.out.println(sum + " : " + totalSum + " : " + nums.length);
            if(sum == totalSum) {
                return true;
            }
        }
        
        
        return false;
    }
    
    private boolean helperBy1DDP(int[] nums, int totalSum) {
        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;
        
        for(int index = 0; index < nums.length; ++index) {
            for(int sum = totalSum; sum >= nums[index]; --sum) {
                dp[sum] = dp[sum] || dp[sum - nums[index]];
            }
        }
        
        return dp[totalSum];
    }
   
    private boolean helperBy2DDP(int[] nums, int totalSum) {
        boolean[][] dp = new boolean[nums.length + 1][totalSum + 1];
        for(int index = 0; index <= nums.length; ++index) {
            for(int sum = 0; sum <= totalSum; ++sum) {
                if(sum == 0) {
                    dp[index][sum] = true;
                    continue;
                }
                if(index == 0) {
                    dp[index][sum] = false;
                    continue;
                }
                
                if(nums[index - 1] <= sum) {
                    dp[index][sum] = dp[index - 1][sum];
                    
                    dp[index][sum] |= dp[index - 1][sum - nums[index - 1]];
                }
            }
        }
        
        return dp[nums.length][totalSum];
    }
    
    private boolean recursion(int[] nums, int currentIndex, int currentSum, int requiredSum) {
        if(currentSum == requiredSum) {
            return true;
        }
        
        
        
        if(currentIndex == nums.length || currentSum > requiredSum) {
            return false;
        }
        
        
        return (recursion(nums, currentIndex + 1, currentSum, requiredSum) 
                || recursion(nums, currentIndex + 1, currentSum + nums[currentIndex], requiredSum));
    }
}