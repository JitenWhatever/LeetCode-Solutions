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
    
    private HashMap<String, Boolean> hash = new HashMap<>();
    
    
    // 16 ms
     public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        
         dp[0] = true;
        
        for(int len = 1; len <= s.length(); ++len) {
            for(int index = 0; index < len; ++index) {
                if(dp[index] && wordDict.contains(s.substring(index, len))) {
                    dp[len] = true;
                    break;
                }
            }
        }
         
        return dp[s.length()];
    }
    
    // 7 ms
    public boolean wordBreak2(String s, List<String> wordDict) {
       if(wordDict.contains(s)) {
            return true;
        }   
        if(hash.containsKey(s)) {
            return hash.get(s);
        }
        
        for(int len = 1; len <= s.length(); ++len) {
            if(wordDict.contains(s.substring(0, len)) && wordBreak(s.substring(len), wordDict)) {
                hash.put(s, true);
                return true;
            }
        }
        hash.put(s, false);
        return false;
    }
    
    // TLE
    public boolean wordBreak1(String s, List<String> wordDict) {
        if(wordDict.contains(s)) {
            return true;
        }   
        
        for(int len = 1; len <= s.length(); ++len) {
            if(wordDict.contains(s.substring(0, len)) && wordBreak(s.substring(len), wordDict)) {
                return true;
            }
        }
        
        return false;
    }
}