/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 

https://assets.leetcode.com/uploads/2018/10/12/histogram.png
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 

https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png
The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10
*/

class Solution {
    public int largestRectangleArea(int[] heights) {
     
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

