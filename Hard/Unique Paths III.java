/*
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20
*/

class Solution {
    
    private int paths;
    
    private void dfs(int r, int c, int[][] grid, int sqrLeft) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == -1) {
            return ;
        }
        
        if(grid[r][c] == 2 && sqrLeft == 1) {
            ++paths;
            return;
        }
        int value = grid[r][c];
        grid[r][c] = -1;
        --sqrLeft;
        
        dfs(r + 1, c, grid, sqrLeft);
        dfs(r - 1, c, grid, sqrLeft);
        dfs(r, c + 1, grid, sqrLeft);
        dfs(r, c - 1, grid, sqrLeft);
        
        grid[r][c] = value;
    }
    
    public int uniquePathsIII(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        
        int r = 0, c = 0;
        int sqrLeft = 0;
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[i].length; ++j) {
                if(grid[i][j] >= 0) {
                    ++sqrLeft;
                }
                
                if(grid[i][j] == 1) {
                    r = i;
                    c = j;
                }
            }
        }
        
        paths = 0;
        
        dfs(r, c, grid, sqrLeft);
        
        return paths;
    }
}