/*
On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

 

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 

Note:

1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
*/

class Solution {
    public boolean isRobotBounded(String instructions) {
        int N = 0;
        int E = 1;
        int S = 2;
        int W = 3;
        int dir = N;
        int X = 0;
        int Y = 0;
        
        for(char ch : instructions.toCharArray()) {
            if(ch == 'R') {
                dir = (dir + 1)%4;
            }
            else if(ch == 'L') {
                dir = (dir - 1 + 4)%4;
            }
            else if(dir == N) {
                ++Y;
            }
            else if(dir == E) {
                ++X;
            }
            else if(dir == S) {
                --Y;
            }
            else {
                --X;
            }
        }
        
        if((X == 0 && Y == 0) || dir != 0) {
            return true;
        }
    
        return false;
    }
}