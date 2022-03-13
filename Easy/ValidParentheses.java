/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
 

Constraints:

1 <= s.length <= 10^4
s consists of parentheses only '()[]{}'.
*/

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        
        for(char ch : s.toCharArray()) {
           if(ch == '(' || ch == '{' || ch == '[') {
               stack.push(ch);
           } else {
               if(stack.isEmpty()) {
                   return false;
               }
               Character c = stack.pop();
               if((ch == ')' && c != '(') || (ch == '}' && c != '{') || (ch == ']' && c != '[')) {
                   return false;
               }
           }
        }
        
        return stack.isEmpty();
    }
}