/*
Given two strings S and T, return if they are equal when both are typed into empty text editors.
# means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:
    1 <= S.length <= 200
    1 <= T.length <= 200
    S and T only contain lowercase letters and '#' characters.

Follow up:
    Can you solve it in O(N) time and O(1) space?
*/
class Solution {
    public boolean backspaceCompare(String S, String T) { // O(N) time and O(1)
        Integer indexS = S.length() - 1, indexT = T.length() - 1;
        Integer backSpaceInS = 0, backSpaceInT = 0;
        while(indexS >= 0 || indexT >= 0) {
            while(indexS >= 0){
                if(S.charAt(indexS) == '#') {
                    ++backSpaceInS;
                    --indexS;
                }
                else if(backSpaceInS > 0) {
                    --backSpaceInS;
                    --indexS;
                }
                else {
                    break;
                }
            }
            
            while(indexT >= 0){
                if(T.charAt(indexT) == '#') {
                    ++backSpaceInT;
                    --indexT;
                }
                else if(backSpaceInT > 0) {
                    --backSpaceInT;
                    --indexT;
                }
                else {
                    break;
                }
            }
            
            if(((indexS >= 0) && (indexT >= 0)) && S.charAt(indexS) != T.charAt(indexT)) {
                return false;
            }
            
            if((indexS >= 0) != (indexT >= 0)) {
                return false;
            }
            
            --indexS;
            --indexT;
        }
        
        return true;

        // return helper(S).equals(helper(T)); // O(N) time and O(N)
    }

    private String helper(String str) {
        Stack<Character> stack = new Stack<Character>();
        
        for(Integer index = 0; index < str.length(); ++index) {
            if(str.charAt(index) != '#') {
                stack.push(str.charAt(index));
            }
            else if(!stack.isEmpty()) {
                stack.pop();
            }
        }
        
        return String.valueOf(stack);
    }
}