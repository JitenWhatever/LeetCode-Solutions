/*
Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.

It is guaranteed that there will be a rectangle with a sum no larger than k.

 

Example 1:
https://assets.leetcode.com/uploads/2021/03/18/sum-grid.jpg

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
Example 2:

Input: matrix = [[2,2,-1]], k = 3
Output: 3
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-100 <= matrix[i][j] <= 100
-105 <= k <= 105
 

Follow up: What if the number of rows is much larger than the number of columns?
*/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        
        if(matrix.length > matrix[0].length) {
            return handleBasedOnColumn(matrix, k);
        } 
        
        return handleBasedOnRow(matrix, k);
    }
    
    private int getMaxSum(int[] nums) {
        int maxSoFar = 0, max = Integer.MIN_VALUE;
        
        for(int num : nums) {
            maxSoFar = Math.max(maxSoFar + num, num);
            max = Math.max(maxSoFar, max);
        }
        
        return max;
    }
    
    private int maxSum = Integer.MIN_VALUE;
    
    private int handleBasedOnRow(int[][] matrix, int k) {
        int[] row = new int[matrix[0].length];
        
        for(int itr = 0; itr < matrix.length; ++itr) {
            Arrays.fill(row, 0);
            
            for(int r = itr; r < matrix.length; ++r) {
                for(int c = 0; c < matrix[r].length; ++c) {
                    row[c] += matrix[r][c];
                }
                
                updateMax(row, k);
                
                if(maxSum == k) {
                    return maxSum;
                }
            }
        }
        
        return maxSum;
    }
    
    private int handleBasedOnColumn(int[][] matrix, int k) {
        int[] col = new int[matrix.length];
        
        for(int itr = 0; itr < matrix[0].length; ++itr) {
            Arrays.fill(col, 0);
            for(int c = itr; c < matrix[0].length; ++c) {
                for(int r = 0; r < matrix.length; ++r) {
                    col[r] += matrix[r][c];
                }
                updateMax(col, k);
                
                if(maxSum == k) {
                    return maxSum;
                }
            }
        }
        
        return maxSum;
    }
    
    private void updateMax(int[] nums, int k) {
        int currentMax = getMaxSum(nums);
        
        if(currentMax <= k) {
            maxSum = Math.max(maxSum, currentMax);
            return ;
        }
        
        TreeSet<Integer> sums = new TreeSet<>();
        
        sums.add(0);
        int prefixSum = 0;
        for(int num : nums) {
            prefixSum += num;
            Integer left = sums.ceiling(prefixSum - k);  // sum - left <= k
            
            if(Objects.nonNull(left)) {
                maxSum = Math.max(maxSum, prefixSum - left);
            }
            
            sums.add(prefixSum);
        }
    }
}