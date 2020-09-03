/*
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot’s position internally. You must solve this problem “blindfolded”. 
In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot’s position.
The robot’s initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
*/


/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
 
class Solution {
     
    private:
        const int MAX = 100005;
        vector<vector<bool>> visited;

        vector<vector<int>> dirs{
            {0, -1}, 
            {-1, 0}, 
            {0, 1}, 
            {1, 0}
        };

    public:
        void cleanRoom(Robot robot) {
            visited = vector<vector<bool>>(MAX, vector<bool>(MAX, false));

            dfs(robot, visited, 0, 0, 0);
        }
     
        void dfs(Robot robot, int dir, int x, int y) {
        
            if(visited[x][y])) {
                return ;
            }
            visited[x][y] = true;
            robot->clean();
            for(int i = 0; i < 4; i++) {
                if(robot->move()) {
                    dfs(robot, dir, x + dirs[dir][0], y + dirs[dir][1]);
                    robot->turnLeft(); // right can also work here
                    robot->turnLeft();
                    robot->move();
                    robot->turnLeft();
                    robot->turnLeft();
                }
               robot->turnLeft();
                dir = (dir + 1) % 4;
            }
        }
}