/*
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        
        this.cache = new HashMap<>();
        this.wordDict = wordDict;
        
        return recurse(s, 0) ;
    }
    
    private Map<Integer, List<String>> cache;
    private List<String> wordDict;
    
    private List<String> recurse(String s, int start) {
        if (cache.containsKey(start)) {
            return cache.get(start);
        }
        
        List<String> result = new ArrayList<>();
        
        if (start == s.length()) {
           result.add("");
            
            return result;
        }
        
        for (int end = start + 1; end <= s.length(); ++end) {
            String prefix = s.substring(start, end);
            
            List<String> suffixes = recurse(s, end);
            if (this.wordDict.contains(prefix)) {
                for (String suffix : suffixes) {
                    result.add(prefix + (suffix.isEmpty() ? "" : " ") + suffix);
                }
            }
        }
        
        cache.put(start, result);
        
        return result;
    }
}

