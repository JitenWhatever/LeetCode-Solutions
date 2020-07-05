/*
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  
If this is impossible, return -1 instead.

 

Example 1:
https://assets.leetcode.com/uploads/2019/02/16/oranges.png

Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, 
because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
*/

class Solution {
    
    private boolean isValid(int r, int c, int[][] grid) {
        
        int H = grid.length, W = grid[0].length;
        
        if(r  >= 0 && r < H && c >= 0 && c < W && grid[r][c] == 1) {
            return true;
        }
            
        return false;
    }
    
    public int orangesRotting(int[][] grid) {
        int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        
        int minutes = 0;
        int fresh = 0;
        
        Queue<int[]> Q = new LinkedList<>();
        
         for(int r = 0; r < grid.length; ++r) {
            for(int c = 0; c < grid[r].length; ++c) {
                if(grid[r][c] == 2) {
                    Q.offer(new int[]{r, c});
                }
                if(grid[r][c] == 1) {
                    ++fresh;
                }
            }
        }
        
        while(!Q.isEmpty() && fresh > 0) {
            
            int len = Q.size();
            
            for(int index = 0; index < len; ++index) {
                
                int[] rotten = Q.poll();
                
                for(int d = 0; d < 4; ++d) {
                    int r = rotten[0] + direction[d][0];
                    int c = rotten[1] + direction[d][1];
                    
                    if(isValid(r, c, grid)) {
                        Q.offer(new int[]{r, c});
                        grid[r][c] = 2;
                        --fresh;
                    }
                }
            }
            
            ++minutes;
        }
        
        if(fresh != 0) {
            return -1;
        }
        
        return minutes;
    }
}