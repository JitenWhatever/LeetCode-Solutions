/*
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
https://assets.leetcode.com/uploads/2019/02/16/oranges.png


Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
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
public:
    int orangesRotting(vector<vector<int>>& grid) {
        
        int H = grid.size(), W = grid[0].size();
        queue<pair<int,int>> Q; 
        int fresh = 0;
        for(int r = 0; r < H; ++r) {
            for(int c = 0; c < W; ++c) {
                if(grid[r][c] == 2) {
                    Q.push({r, c});
                }
                
                if(grid[r][c] == 1) {
                    ++fresh;
                }
            }
        }
        
        if(fresh == 0) {
            return 0;
        }
        int minutes = 0;
        while(!Q.empty()) {
            int len = Q.size();
            
            for(int itr = 0; itr < len; ++itr) {
                pair<int, int> rotten = Q.front();
                Q.pop();
                int r = rotten.first;
                int c = rotten.second;
                if(r > 0 && grid[r - 1][c] == 1) {
                    --fresh;
                    grid[r - 1][c] = 2;
                    Q.push({r - 1, c});
                }
                if(c > 0 && grid[r][c - 1] == 1) {
                    --fresh;
                    grid[r][c - 1] = 2;
                    Q.push({r, c - 1});
                }
                if( r < H - 1 && grid[r + 1][c] == 1) {
                    --fresh;
                    grid[r + 1][c] = 2;
                    Q.push({r + 1, c});
                }
                if(c < W - 1 && grid[r][c + 1] == 1) {
                    --fresh;
                    grid[r][c + 1] = 2;
                    Q.push({r, c + 1});
                }
            }
            ++minutes;
            if(fresh == 0) {
                break;
            }
        }
        
        if(fresh > 0) {
            return -1;
        }
        
        return minutes;
    }
};