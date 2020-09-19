/*

Given a m * n matrix mat and an integer K, return a matrix answer where each 
answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 

Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
*/

class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int[][] result = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                mat[i][j] += mat[i][j - 1];
            }
        }
        for (int i = 1; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] += mat[i - 1][j];
            }
        }
        
         for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int x1 = i - K - 1;
                int y1 = j - K - 1;
                int x2 = i + K > mat.length - 1 ? mat.length - 1 : i + K;
                int y2 = j + K > mat[0].length - 1 ? mat[0].length - 1 : j + K;
                
                int p = x1 < 0 || y1 < 0 ? 0 : mat[x1][y1];
                int t = x1 < 0 ? 0 : mat[x1][y2];
                int l = y1 < 0 ? 0 : mat[x2][y1];
                int b = mat[x2][y2];
                
                result[i][j] = b - t - l + p;
            }
        }
        
        return result;
    }
}
