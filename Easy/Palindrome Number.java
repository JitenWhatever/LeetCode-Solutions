/*
Determine whether an integer is a palindrome. 
An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. 
Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?
*/

class Solution {
    public boolean isPalindrome(int x) {
       if(x < 0 || (x % 10 == 0 && x!=0)) { // -1554, 10, 100 etc;
           return false;
       }
        
        int reverse = 0;
        while(x > reverse) {
            reverse = reverse * 10 + x%10;
            x /= 10;
        }
        
        return (x == reverse || x == reverse/10);
    }
    
    public boolean isPalindrome1(int x) { // by converting it to string 
        StringBuilder sb = new StringBuilder();
        if(x < 0) return false;
        
        while(x != 0) {
            sb.append(x%10);
            x = x / 10;
        }
        
        String s = sb.toString();
        
        int low = 0, high = s.length() - 1;
        while(low < high) {
            if(s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}