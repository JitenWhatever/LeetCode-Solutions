/*
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 

Note:
1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
*/

class Solution {
    public int longestStrChain(String[] words) {
        
        int N = words.length;
        Map<String, Integer> positions = new HashMap<>();
        graph = new ArrayList[N];
        score = new int[N];
        // Arrays.fiil(score, -1);
        
        for(int node = 0; node < N; ++node) {
            graph[node] = new ArrayList();
        }
        
        for(int index = 0; index < N; ++index) {
            positions.put(words[index], index);
        }
        
        for(int index = 0; index < N; ++index) {
            String word = words[index];
            for(int pos = 0; pos < word.length(); ++pos) {
                String tmp = word.substring(0, pos) + word.substring(pos +  1);
                
                // System.out.println("Jiten : " + tmp);
                if(positions.containsKey(tmp)) {
                    graph[positions.get(tmp)].add(index);
                }
            }
        }
        int result = 0;
        
        for(int node = 0; node < N; ++node) {
            result = Math.max(result, dfs(node));
        }
        return result;
    }
    private List<Integer>[] graph;
    private int[] score;
    
    private int dfs(int u) {
        if(score[u] > 0) {
            return score[u];
        }
        
        score[u] = 1;
        
        for(int v : graph[u]) {
            score[u] = Math.max(dfs(v) + 1, score[u]);
        }
        
        return score[u];
        
    }
}
