/*
Given a string s, remove duplicate letters so that every letter appears once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
*/

class Solution {
public:
    string removeDuplicateLetters(string s) {
        if(s.length() == 0) {
            return "";
        }
        
        vector<int> count(26);
        vector<bool> assigned(26);
        
        for(char ch : s) {
            count[ch - 'a']++;
        }
        
        string result;
        
        for(char ch : s) {
            count[ch - 'a']--;
            
            if(assigned[ch - 'a']) {
                continue;
            }
            
            while(result.length() > 0 && result[result.length() - 1] > ch && count[result[result.length() - 1] - 'a'] > 0) {
                assigned[result[result.length() - 1] - 'a'] = false;
                // cout<<result[result.length() - 1]<<" : ";
                result.pop_back();
                
            }
            assigned[ch - 'a'] = true;
          
            result.push_back(ch);
        }
        
        return result;
    }
};