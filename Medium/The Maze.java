/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
https://assets.leetcode.com/static_assets/public/images/problemset/maze_1_example_1.png

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.
https://assets.leetcode.com/static_assets/public/images/problemset/maze_1_example_2.png

Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/

class Solution {

    public bool hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.size(), n = maze[0].size();
        return dfs(maze, start, destination);
    }

    private int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private boolean dfs(int[][] maze, int[] start, int[] destination) {

        if (start[0] == destination[0] && start[1] == destination[1]) return true; // reach destination

        if (maze[start[0]][start[1]] != 0) return false; // invalid location

        maze[start[0]][start[1]] = -1; // to avoid repeatition

        for (int i = 0; i < 4; ++i) {
            int x = start[0], y = start[1];
            // loop until reach the wall
            while (x+dirs[i] >= 0 && x+dirs[i] < maze.size() && y+dirs[i+1] >= 0 && y+dirs[i+1] < maze[0].size() && maze[x+dirs[i]][y+dirs[i+1]] != 1) {
                x += dirs[i];
                y += dirs[i+1];
            }
            if (dfs(maze, new int[]{x,y},destination)) return true;
        }
        return false;
    } 
}