/*
wo images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  
After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes: 

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
*/

class Solution {
public:
    int largestOverlap(vector<vector<int>>& A, vector<vector<int>>& B) {
        
        int maxCount = INT_MIN;
        
        for(int row = 0; row < A.size(); ++row) {
            for(int col = 0; col < A.size(); ++col) {
                maxCount = max(maxCount, getCount(row, col, A, B));
                maxCount = max(maxCount, getCount(row, col, B, A));
            }
        }
        
        
        return maxCount;
    }
    
    int getCount(int x, int y, vector<vector<int>> A, vector<vector<int>> B) {
        
        int brow  = 0;
        int bcol = 0;
        int count = 0;
        
        for(int arow = x; arow < A.size(); ++arow) {
            bcol = 0;
            for(int acol = y; acol < A.size(); ++acol) {
                if(A[arow][acol] == 1 && A[arow][acol] == B[brow][bcol]) {
                    ++count;
                }
                ++bcol;
            }
            ++brow;
        }
        
        return count;
    }
};