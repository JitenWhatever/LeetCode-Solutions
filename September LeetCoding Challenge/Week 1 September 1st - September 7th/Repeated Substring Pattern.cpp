/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

class Solution {
public:
    bool repeatedSubstringPattern(string s) {
        if(s.length() < 2) {
            return false;
        }
        int len = s.length();
        
        for(int index = len/2; index >= 1; --index) {
            if(len%index == 0) {
                int reps = len/index;
                string str = s.substr(0, index);
                // cout<<s<<" : "<<str<<"\n";
                string temp = "";
                while(reps) {
                    --reps;
                    temp = temp + str;
                }
                
                // cout<<s<<" : "<<temp<<"\n";
                if(isEquals(s, temp)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    bool isEquals(string s1, string s2) {
        for(int index = 0; index < s1.length(); index++) {
            if(s1[index] != s2[index]) {
                return false;
            }
        }
        
        return true;
    }
};