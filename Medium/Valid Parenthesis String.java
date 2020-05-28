/*
Given a string containing only three types of characters: '(', ')' and '*', 
write a function to check whether this string is valid. 

We define the validity of a string by these rules:
   
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:

Input: "()"
Output: True

Example 2:

Input: "(*)"
Output: True

Example 3:

Input: "(*))"
Output: True

Note:
    The string size will be in the range [1, 100].
*/

class Solution {
    public boolean checkValidString(String s) {
        int minOpenBracket = 0, maxOpenBracket = 0;
        
        for(char ch : s.toCharArray()) {
            if(ch == '('){
                ++minOpenBracket;
            }
            else {
                --minOpenBracket;
            }
            
            if(ch != ')'){
                ++maxOpenBracket;
            }
            else {
                --maxOpenBracket;
            }
            
            if(maxOpenBracket < 0) {
                return false;
            }
            minOpenBracket = Math.max(minOpenBracket, 0);
        }
        
        return minOpenBracket == 0;
    }
}