/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 

Example 1:
https://assets.leetcode.com/uploads/2020/10/05/mat.jpg

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
Output: true

Example 2:
https://assets.leetcode.com/uploads/2020/10/05/mat2.jpg

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
Output: false
Example 3:

Input: matrix = [], target = 0
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
0 <= m, n <= 100
-10^4 <= matrix[i][j], target <= 10^4
*/

class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int H = matrix.length;
        int W = matrix[0].length;
        
        int low = 0;
        int high = H*W - 1;
        
        while(low <= high) {
            int mid = low + (high - low)/2;
            int r = mid / W;
            int c = mid % W;
            if(matrix[r][c] == target) {
                return true;
            }
            else if(matrix[r][c] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        return false;
    }
}