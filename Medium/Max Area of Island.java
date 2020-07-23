/*
Given a non-empty 2D array grid of 0's and 1's, 
an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
*/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        
        int result = 0;
        for(int r = 0; r < grid.length; ++r) {
            for(int c = 0; c < grid[r].length; ++c) {
                if(grid[r][c] == 1) {
                    maxArea = 0;
                    dfs(r, c, grid);
                    
                    result = Math.max(result, maxArea);
                }
            }
        }
        
        return result;
    }
    
    private int maxArea;
    private int[] rows = {-1, 1, 0, 0};
    private int[] cols = {0, 0, -1, 1};
    
    private void dfs(int r, int c, int[][] grid) {
        int H = grid.length, W = grid[0].length;
        if(r < 0 || r >= H || c < 0 || c >= W || grid[r][c] != 1) {
            return ;
        }
        
        grid[r][c] = 0;
        ++maxArea;
        for(int dir = 0; dir < 4; ++dir) {
            dfs(r + rows[dir], c + cols[dir], grid);
            
        }
        
    }
}