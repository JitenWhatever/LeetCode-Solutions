/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> words = new HashSet<>(wordDict);
        HashMap<Integer, List<String>> cache = new HashMap<>();
        
        return wordBreakUtil(s, 0, words, cache);
    }
    
    private List<String> wordBreakUtil(String s, int left, Set<String> words, Map<Integer, List<String>> cache) {
        
        if(cache.containsKey(left)) {
            return cache.get(left);
        }
        
        List<String> result = new ArrayList<>();
        
        if(left == s.length()) { // string exhausted
            result.add("");
        }
        
        for(int right = left + 1; right <= s.length(); ++right) {
            String prefix =  s.substring(left, right);
            
            if(words.contains(prefix)) {
                List<String> suffixes = wordBreakUtil(s, right, words, cache);
                
                for(String suffix : suffixes) {
                    result.add(prefix + (suffix.equals("") ? "" : " ") + suffix);
                }
            }
        }
        
        cache.put(left, result);
        return result;
    }
}

