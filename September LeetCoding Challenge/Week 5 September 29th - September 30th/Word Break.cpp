/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        for(string str : wordDict) {
            words.insert(str);
        }
        
        return recurse(s);
    }
    
    set<string> words;
    map<string, bool> hash;
    
    bool recurse(string s) {
        if(words.count(s)) {
            return true;
        }
        
        if(hash.count(s)) {
            return hash[s];
        }
        
        for(int len = 1; len <= s.length(); ++len) {
            if(words.count(s.substr(0, len)) && recurse(s.substr(len))) {
                hash[s] = true;
                return true;
            }
        }
        
        hash[s] = false;
        
        return false; 
    }
};