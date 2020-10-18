/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

Example 1:
https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = []
Output: 0
Example 3:

Input: matrix = [["0"]]
Output: 0
Example 4:

Input: matrix = [["1"]]
Output: 1
Example 5:

Input: matrix = [["0","0"]]
Output: 0
 

Constraints:

rows == matrix.length
cols == matrix.length
0 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int maximalRectangle = Integer.MIN_VALUE;
        
        int[] heights = new int[cols];
        
        for(int row = 0; row < rows; ++row) {
            for(int col = 0; col < cols; ++col) {
                if(matrix[row][col] == '1') {
                    heights[col] += 1;
                }
                else {
                    heights[col] = 0;
                }
            }
            
            maximalRectangle =  Math.max(maximalRectangle, largestRectangleArea(heights));
        }
        
        return maximalRectangle;
    }
    
    private int largestRectangleArea(int[] heights) {
     
        if(heights == null || heights.length == 0) {
            return 0;
        }
        
        int largestRectangleArea = Integer.MIN_VALUE;
        
        Stack<Integer> st = new Stack();
        
        int index = 0;
        while(index < heights.length) {
            if(st.empty() || heights[st.peek()] <= heights[index]) {
                st.push(index++);
            }
            else {
                int top = st.pop();
                
                if(st.empty()) {
                    largestRectangleArea = Math.max(largestRectangleArea, index * heights[top]); 
                }
                else {
                    largestRectangleArea = Math.max(largestRectangleArea, (index - st.peek() - 1) * heights[top]);
                }
            }
        }
        
        while(!st.empty()) {
            int top = st.pop();
                
            if(st.empty()) {
                largestRectangleArea = Math.max(largestRectangleArea, index * heights[top]); 
            }
            else {
                largestRectangleArea = Math.max(largestRectangleArea, (index - st.peek() - 1) * heights[top]);
            }
        }
        
        return largestRectangleArea;
    }
}


