/*
You are starving and you want to eat food as quickly as possible. 
You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, 
return -1.

Example 1:
https://assets.leetcode.com/uploads/2020/09/21/img1.jpg

Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],
["X","O","O","#","O","X"],
["X","X","X","X","X","X"]]
Output: 3

Explanation: It takes 3 steps to reach the food.
Example 2:
https://assets.leetcode.com/uploads/2020/09/21/img2.jpg

Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.

Example 3:
https://assets.leetcode.com/uploads/2020/09/21/img3.jpg

Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
Example 4:

Input: grid = [["O","*"],["#","O"]]
Output: 2
Example 5:

Input: grid = [["X","*"],["#","X"]]
Output: -1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*'.
*/

class Solution {
    public int getFood(char[][] grid) {
        int[] start = null;
        
        int minPathCost = -1,  N = grid.length, M = grid[0].length;
        
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c] == '*') {
                    start = new int[]{r, c};
                    break;
                }
            }
            
            if (start != null) {
                break;
            }
        }
        
        Queue<int[]> Q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        Q.add(start);
        
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int steps = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            
            while (size-- > 0) {
                int[] loc = Q.poll();
                int r = loc[0], c = loc[1];
                if (grid[r][c] == '#') {
                    return steps;
                }
                
                for (int[] dir : dirs) {
                    int rn = r + dir[0];
                    int cn = c + dir[1];
                    
                    if (isValid(rn, cn, N, M, grid) && !visited[rn][cn]) {
                        visited[rn][cn] = true;
                        Q.add(new int[]{rn, cn});
                    }
                }
            }
            
            steps++;
        
        }
        
        return minPathCost;
    }
    
    private boolean isValid(int r, int c, int N, int M, char[][] grid) {
        return r >= 0 && r < N && c >= 0 && c < M && grid[r][c] != 'X';
    }
}