/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

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
public:
    int dfs(vector<vector<char>> & grid, int  i, int j){
        int N = grid.size();
        int M = grid[0].size();
        
        if(i < 0 || j < 0 || i >= N || j >= M || grid[i][j] == '0'){
            return 0;
        }
        int row[] = {1, -1, 0, 0};
        int col[] = {0, 0, 1, -1};
        grid[i][j] = '0';
            
        for(int k=0;k<4;k++){
            dfs(grid, i + row[k], j + col[k]);
        }
    
        return 1;
    }
    
    int numIslands(vector<vector<char>>& grid) {
        if(grid.size() == 0){
            return 0;
        }
        
        int N = grid.size();
        int M = grid[0].size();
        int isLands = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(grid[i][j] == '1'){
                    isLands += dfs(grid, i, j);
                }
            }
        }
        return isLands;
    }
};