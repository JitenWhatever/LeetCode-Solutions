/*
Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.

A word is a maximal substring consisting of non-space characters only.

 

Example 1:

Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
Example 2:

Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
Example 3:

Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
 

Constraints:

1 <= s.length <= 104
s consists of only English letters and spaces ' '.
There will be at least one word in s.
*/

class Solution {
    public int lengthOfLastWord(String s) {
        if(Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        int index = s.length() - 1, lengthOfLastWord = 0;
        while(index >= 0 && s.charAt(index) == ' ') { // remove trailing zeros
            --index;
        }
        
        for(int i = index; i >= 0; --i) {
            if(s.charAt(i) != ' ') {
                ++lengthOfLastWord;
            }
            else {
                break;
            }
        }
        
        return lengthOfLastWord;
    }
}

class Solution {
    public int lengthOfLastWord(String s) {
        int p = s.length(), length = 0;
        while (p > 0) {
            p--;
            // we're in the middle of the last word
            if (s.charAt(p) != ' ') {
                length++;
            }
            // here is the end of last word
            else if (length > 0) {
                return length;
            }
        }
        return length;
  }
}

class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();  // trim the trailing spaces in the string
        return s.length() - s.lastIndexOf(" ") - 1;
    }
}