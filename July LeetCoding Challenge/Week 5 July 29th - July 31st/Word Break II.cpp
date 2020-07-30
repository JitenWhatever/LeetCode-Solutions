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
public:
    vector<string> wordBreakUtil(string s, set<string> &words, int index, map<int, vector<string>> &cache) {
        if(cache.count(index)) return cache[index];
        vector<string> result;
        for(int idx = index; idx < s.length(); idx++) {
            string prefix = s.substr(index, idx - index + 1);
            if(words.count(prefix)) {
                if(idx == s.size() - 1) result.push_back(prefix);
                vector<string> tmp = wordBreakUtil(s, words, idx + 1, cache);
                for(string e :tmp) result.push_back(prefix + " " + e);
            }
        }
        return cache[index] = result;
    }
    vector<string> wordBreak(string s, vector<string>& wordDict) {
        set<string> words(wordDict.begin(), wordDict.end());
        map<int, vector<string>> cache;
        return wordBreakUtil(s, words, 0, cache);
    }
    
};