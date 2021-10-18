/*
Given an array of strings words (without duplicates), 
return all the concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

 

Example 1:

Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Example 2:

Input: words = ["cat","dog","catdog"]
Output: ["catdog"]
 

Constraints:

1 <= words.length <= 10^4
0 <= words[i].length <= 1000
words[i] consists of only lowercase English letters.
0 <= sum(words[i].length) <= 10^5
*/

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        
        for (int i = 0; i < words.length; i++) {
            if (canFormWord(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }
        
        return result;
    }
    
    private boolean canFormWord(String word, Set<String> dict) {
        if (dict.isEmpty()) {
            return false;
        }
        
        boolean[] dp = new boolean[word.length() + 1];
	    dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
	        for (int j = 0; j < i; j++) {
		        if (!dp[j]) continue;
		        if (dict.contains(word.substring(j, i))) {
		            dp[i] = true;
		            break;
		        }
	        }
	    }
	    return dp[word.length()];
    }
}

