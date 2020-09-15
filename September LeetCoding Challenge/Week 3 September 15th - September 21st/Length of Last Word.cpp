/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
return the length of last word (last word means the last appearing word if we loop from left to right) in the string.

If the last word does not exist, return 0.

Note: A word is defined as a maximal substring consisting of non-space characters only.

Example:

Input: "Hello World"
Output: 5
*/

class Solution {
public:
    int lengthOfLastWord(string s) {
        
        int len = 0;
        
        if(s.length() < 1) {
            return 0;
        }
        int index = s.length() - 1;
        while(index >= 0 && s[index] == ' ') {
            --index;
        }
        for(int i = index; i >= 0; --i) {
            if(s[i] != ' '){
                ++len;
            }
            else {
                break;
            }
        }
        
        return len;
    }
};