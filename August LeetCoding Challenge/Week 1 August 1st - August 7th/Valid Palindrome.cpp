/*
Given a string, determine if it is a palindrome, 
considering only alphanumeric characters and ignoring cases.

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
public:
    bool isPalindrome(string s) {
        int left = 0, right = s.length() - 1;
        
        while(left < right) {
            while(left < right &&!isalnum(s[left])) {
                ++left;
            }
            while(left < right && !isalnum(s[right])) {
                --right;
            }
            
            if(isdigit(s[left]) && !isdigit(s[right]) || !isdigit(s[left]) && isdigit(s[right])) {
                return false;
            }
            
            if(left < right && tolower(s[left]) != tolower(s[right])) {
                return false;
            }
            
            ++left;
            --right;
        }
        
        return true;
    }
};