/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the image below:

https://assets.leetcode.com/uploads/2018/10/12/island.png

*/

class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        
        if(grid.size() == 0 ) return 0;
        
        int H = grid.size(), W = grid[0].size();
        
        int perimeter = 0;
        
        for(int r = 0; r < H; ++r) {
            for(int c = 0; c < W; ++c) {
                if(grid[r][c] == 1) {
                    perimeter += 4;
                
                    if(r > 0 && grid[r - 1][c] == 1) {
                        perimeter -= 2;
                    }
                
                    if(c > 0 && grid[r][c - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }
        
        return perimeter;
    }
};