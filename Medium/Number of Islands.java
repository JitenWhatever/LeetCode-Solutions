/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/

class Solution {
    public int numIslands(char[][] grid) {
        
        if(grid.length == 0) return 0;
        
        int rows = grid.length, columns = grid[0].length;
        int numberOfIslands = 0;
        for(int row = 0; row < rows; ++row) {
            for(int column = 0; column < columns; ++column) {
                
                numberOfIslands += dfs(grid, row, column);
            }
        }
        
        return numberOfIslands;
    }
    
    
    private int dfs(char[][] grid, int row, int column){
        
        if(row < 0 || row >= grid.length || column < 0 || column >= grid[row].length || grid[row][column] != '1'){
            return 0;
        }
        
        grid[row][column] = '0';
        
        dfs(grid, row + 1, column);
        dfs(grid, row, column + 1);
        dfs(grid, row - 1, column);
        dfs(grid, row, column - 1);
        
        return 1;
    }
}
