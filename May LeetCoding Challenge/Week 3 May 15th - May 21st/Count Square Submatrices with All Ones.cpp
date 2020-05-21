/*
Given a m * n matrix of ones and zeros, 
return how many square submatrices have all ones.

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.

Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.

Constraints:
    1 <= arr.length <= 300
    1 <= arr[0].length <= 300
    0 <= arr[i][j] <= 1
*/

class Solution {
public:
    int countSquares(vector<vector<int>>& matrix) {
        
        int H = matrix.size();
        int W = matrix[0].size();
        int result = 0;
        
        for(int row = 0; row < H; ++row) {
            for(int col = 0; col < W; ++col) {
                if(matrix[row][col] == 0) continue;
                
                if(row == 0 || col == 0) {
                    ++result;
                    continue;
                }
                
                matrix[row][col] += min({matrix[row][col - 1], matrix[row - 1][col], matrix[row - 1][col - 1]});
                result += matrix[row][col];
            }
        }
        
        return result;
    }
};