/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. 
We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. 
If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/


class Solution {
    
    private boolean [][] visited = null;
    private void dfs(int r, int c, int dist, int [][] rooms){
        int H = rooms.length, W = rooms[0].length;
        
        if(r < 0 || r >= H || c < 0 || c >= W || visited[r][r] || rooms[r][c] == -1) {
            return ;
        }

        if(rooms[r][c] < dist) {
            return ;
        }

        visited[r][c] = true;
        rooms[r][c] = dist;

        dfs(r - 1, c, dist + 1, rooms);
        dfs(r + 1, c, dist + 1, rooms);
        dfs(r, c - 1, dist + 1, rooms);
        dfs(r, c + 1, dist + 1, rooms);

        visited[r][c] = false;

        return;
    }

    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return ;

        int H = rooms.length, W = rooms[0].length;
        visited = new boolean[H][W];

        for(int r = 0 ; r < H; ++r) {
            for(int c = 0 ; c < W; ++c) {
                if(rooms[r][c] == 0) {
                    dfs(r, c, 0, rooms);
                }
            }
        }
    }
}