/*
Given an input string, reverse the string word by word.

Example 1:

Input: "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Follow up:

For C programmers, try to solve it in-place in O(1) extra space.
*/

class Solution {
public:
    string reverseWords(string s) {
        
      int left = 0, right = 0, len = s.length();
        
        while (right < len) {
            while (right < len && s[right] == ' ') right++;
            
            if (right < len && left > 0)
                s[left++] = ' ';
            
            int start = left;
            
            while (right < len && s[right] != ' ')
                s[left++] = s[right++];
            
            reverse(s.begin() + start, s.begin() + left);
        }
        s.resize(left);
        reverse(s.begin(), s.end());
        
        return s;
    }

public:
    string reverseWords(string s) {
        
       stringstream words(s); 
        string word,ans = "";
        while (words >> word) {
            ans = word + " " + ans;
        }
        
        return ans.substr(0,ans.length()-1);
    }
};

