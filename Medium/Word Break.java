/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
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


public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}