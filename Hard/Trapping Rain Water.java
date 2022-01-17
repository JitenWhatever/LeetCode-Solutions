/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it can trap after raining.


Example 1:
https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5
*/

class Solution {
    public int trap(int[] height) {
        
        if (height == null || height.length == 0) {
            return -1;
        }
        
        int result = 0;
        int width = height.length;
        int[] leftMax = new int[width];
        int[] rightMax = new int[width];
        
        leftMax[0] = height[0];
        for (int index = 1; index < width; ++index) {
            leftMax[index] = Math.max(height[index], leftMax[index - 1]);
        }
        
        rightMax[width - 1] = height[width - 1];
         for (int index = width - 2; index >= 0; --index) {
            rightMax[index] = Math.max(height[index], rightMax[index + 1]);
        }
        
         for (int index = 1; index < width - 1; ++index) {
            result += Math.min(leftMax[index], rightMax[index]) - height[index];
        }
        
        
        return result;
    }
}

int trap(vector<int>& height)
{
    int ans = 0, current = 0;
    stack<int> st;
    while (current < height.size()) {
        while (!st.empty() && height[current] > height[st.top()]) {
            int top = st.top();
            st.pop();
            if (st.empty())
                break;
            int distance = current - st.top() - 1;
            int bounded_height = min(height[current], height[st.top()]) - height[top];
            ans += distance * bounded_height;
        }
        st.push(current++);
    }
    return ans;
}

class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, left_max = 0, right_max = 0, result = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    result += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    result += (right_max - height[right]);
                } 
                --right;
            }
        }
        
        return result;
    }
}