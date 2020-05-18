/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
In other words, one of the first string's permutations is the substring of the second string.

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False

Note:
    The input strings only contain lower case letters.
    The length of both given strings is in range [1, 10,000].
*/

class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        if(s2.length() < s1.length()) {
            return false;
        }        
        
        vector<int> hash(26, 0);
        for(char ch : s1) {
            hash[ch - 'a']++;
        }
        
        int windowSize = s1.length(), windowLeft = 0, windowRight = 0;
        
        while(windowRight < s2.length()){
            
            if(hash[s2[windowRight++] - 'a']-- >= 1){
                --windowSize;
            }
            
            if(windowSize == 0) {
                return true;
            }
            
            if(windowRight - windowLeft == s1.length() && hash[s2[windowLeft++] - 'a']++ >= 0){
                ++windowSize;
            }
        }
        
        return false;
    }
};