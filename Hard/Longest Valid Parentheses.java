/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 

Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0
*/

class Solution {
    public int longestValidParentheses(String s) {
  int open = 0;
         int close  =0;
        int result = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                ++open;
            }
            else {
                ++close;
            }
            
            if(open == close) {
                result = Math.max(result, 2*open);
            }
            else if(close >= open) {
                open = 0;
                close = 0;
            }
        }
        
        open = close = 0;
        
        for(int index = s.length() - 1; index >= 0; --index) {
             if(s.charAt(index) == '(') {
                ++open;
            }
            else {
                ++close;
            }
            
            if(open == close) {
                result = Math.max(result, 2*open);
            }
            else if(open >= close) {
                open = 0;
                close =0;
            }
        }
        
        return result;
    }
}

class Solution {
    public int longestValidParentheses(String s) {
         int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}