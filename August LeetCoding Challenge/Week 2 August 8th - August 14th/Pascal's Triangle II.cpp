/*
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
*/

class Solution {
public:
    vector<int> getRow(int rowIndex) {
        vector<int> result(rowIndex + 1);
        result[0] = 1;
        int p = 1;
        for(int r = 1; r <= rowIndex; ++r) {
            long c = (long)p*(rowIndex - r + 1)/r;
            result[r] = (int)c;
            p = result[r];
        }
        
        return result;
    }
};