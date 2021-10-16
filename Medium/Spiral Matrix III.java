/*
You start at the cell (rStart, cStart) of an rows x cols grid facing east. 
The northwest corner is at the first row and column in the grid, 
and the southeast corner is at the last row and column.

You will walk in a clockwise spiral shape to visit every position in this grid. 
Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). 
Eventually, we reach all rows * cols spaces of the grid.

Return an array of coordinates representing the positions of the grid in the order you visited them.

Example 1:
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/08/24/example_1.png

Input: rows = 1, cols = 4, rStart = 0, cStart = 0
Output: [[0,0],[0,1],[0,2],[0,3]]

Example 2:
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/08/24/example_2.png

Input: rows = 5, cols = 6, rStart = 1, cStart = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 

Constraints:

1 <= rows, cols <= 100
0 <= rStart < rows
0 <= cStart < cols
*/

class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        int move = 0, currDir = 0, totalElments = rows * cols - 1, index = 0;
        result[index++] = new int[]{rStart, cStart};
        
        while (totalElments > 0) {
            if (currDir == 0 || currDir == 2) {
                ++move;
            }
            
            for (int step = 0; step < move; ++step) {
                rStart += dirs[currDir][0];
                cStart += dirs[currDir][1];
                
                if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                    result[index++] = new int[]{rStart, cStart};
                    --totalElments;
                }
            }
            
            currDir = (currDir + 1) % 4;
            
        }
        
        return result;
    }
}

class Solution {
    int idx;
    int[][] ret;
    
    private void add (int r, int c, int R, int C) {
        if (r >= R || r < 0 || c >= C || c < 0) return;
        ret[idx][0] = r;
        ret[idx++][1] = c;
    }
    
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int r = r0, c = c0, len = 1;
        ret = new int[R * C][2];
        while (idx < (R * C )) {         
            for (int k = 0; k < len; k++) add(r, c++, R, C);          
            for (int k = 0; k < len; k++) add(r++, c, R, C);
            len++;
            for (int k = 0; k < len; k++) add(r, c--, R, C);
            for (int k = 0; k < len; k++) add(r--, c, R, C);
            len++;   
        }
        return ret;
    }
}