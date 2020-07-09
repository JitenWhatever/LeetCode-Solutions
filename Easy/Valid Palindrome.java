/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 

Constraints:

s consists only of printable ASCII characters.
*/

class Solution {
    public boolean isPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        
        while(low < high) {
            while(low < high && !Character.isLetterOrDigit(s.charAt(high))){
                --high;
            }
            while(low < high && !Character.isLetterOrDigit(s.charAt(low))){
                ++low;
            }
             
            if(Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high)) ) {
                return false;
            }
                  
            ++low;
            --high;
        }
        
        return true;
    }
}