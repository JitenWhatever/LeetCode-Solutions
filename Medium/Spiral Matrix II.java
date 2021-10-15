/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg

Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        
        int totalElements = 0, left = 0, up = 0, right = n - 1, down = n - 1;
        
        while (totalElements < n * n) {
            for (int k = left; k <= right && totalElements < n * n; ++k) {
                ++totalElements;
                matrix[up][k] = totalElements;
              // System.out.print(totalElements + " : " + up + " " + k + " right\n");
            }
            
            // System.out.print("\n");
            
            
            ++up;
            
            for (int k = up; k <= down && totalElements < n * n; ++k) {
                ++totalElements;
                matrix[k][right] = totalElements;
               // System.out.print(totalElements + " : " + k + " " + right + "\n");
            }
             System.out.print("\n");
            --right;
            
            for (int k = right; k >= left && totalElements < n * n; --k) {
                ++totalElements;
                matrix[down][k] = totalElements;
                // System.out.print(totalElements + " : " + down + " " + k + "\n");
            }
             // System.out.print("\n");
            --down;
            
            for (int k = down; k >= up && totalElements < n * n; --k) {
                    ++totalElements;
                    matrix[k][left] = totalElements;
                // System.out.print(totalElements + " : " + k + " " + left + " up\n");
            }
            ++left;
        }
        
        return matrix;
    }
}

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int row = 0;
        int col = 0;
        while (cnt <= n * n) {
            result[row][col] = cnt++;
            int r = Math.floorMod(row + dir[d][0], n);
            int c = Math.floorMod(col + dir[d][1], n);

            // change direction if next cell is non zero
            if (result[r][c] != 0) d = (d + 1) % 4;

            row += dir[d][0];
            col += dir[d][1];
        }
        return result;
    }
}