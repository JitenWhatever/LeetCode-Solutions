/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        this.result = new ArrayList<>();
        
        backtrack(new char[2*n], 0);
        
        return result;
    }
    
    private List<String> result;
    
    private void backtrack(char[] currentResult, int index) {
        if (index == currentResult.length) {
            if (isvalid(currentResult)) {
                result.add(new String(currentResult));
            }
        } else {
            currentResult[index] = '(';
            backtrack(currentResult, index + 1);
            currentResult[index] = ')';
            backtrack(currentResult, index + 1);
        } 
    }
    
    private boolean isvalid (char[] s) {
        int balance = 0;
        
        for (char ch : s) {
            if (ch == '(') {
                ++balance;
            } else {
                --balance;
            }
            
            if (balance < 0) {
                return false;
            }
        }
        
        return balance == 0;
    }
}


class Solution {
    public List<String> generateParenthesis(int n) {
        this.result = new ArrayList<>();
        
        backtrack("", 0, 0, n);
        
        return result;
    }
    
    private List<String> result;
    
    private void backtrack(String currentResult, int open, int close, int n) {
        if (2*n == currentResult.length()) {
            result.add(currentResult);
            return;
        } 
        
        if (open < n) {
             backtrack(currentResult + "(", open + 1, close, n);   
        }
        
        if (close < open) {
            backtrack(currentResult + ")", open, close + 1, n);  
        }
    }
    
}