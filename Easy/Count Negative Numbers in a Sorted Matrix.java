/*
Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise. 

Return the number of negative numbers in grid.

 

Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0
Example 3:

Input: grid = [[1,-1],[-1,-1]]
Output: 3
Example 4:

Input: grid = [[-1]]
Output: 1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100
*/

class Solution {
    public int countNegatives(int[][] grid) {
        
        if(grid == null || grid.length == 0) {
            return 0;
        }
        
        int negativeNumbers = 0;
        
        for(int row = 0; row < grid.length; ++row) {
            int index = firstIndex(grid[row]);
            if(grid[row][index] < 0)
                negativeNumbers += (grid[row].length - index); 
            // System.out.println(index);
        }
        
        return negativeNumbers;
    }
    
    private int firstIndex(int[] nums) {
        int index = -1;
        int low = 0; 
        int high = nums.length - 1;
        
        while(low < high) {
            int mid = low + (high - low) / 2;
            
            if(nums[mid] < 0) {
                high = mid;
            }
            else {
                low = mid + 1;
                index = low;
            }
        }
        
        return low;
    }
}