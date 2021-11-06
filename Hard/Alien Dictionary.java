/*
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, 
where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. 
If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, 
the letter in s comes before the letter in t in the alien language. 
If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.

 

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
*/

class Solution {
    public String alienOrder(String[] words) {
        
        this.init(words);
       
        for (int index = 0; index < words.length - 1; ++index) {
            String word1 = words[index];
            String word2 = words[index + 1];
            
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            for (int itr = 0; itr < Math.min(word1.length(), word2.length()); ++itr) {
                if (word1.charAt(itr) != word2.charAt(itr)) {
                    graph.get(word1.charAt(itr)).add(word2.charAt(itr));
                    inDegree.put(word2.charAt(itr), inDegree.getOrDefault(word2.charAt(itr), 0) + 1);
                    break;
                }
            }  
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> Q = new LinkedList<>();
        
        
        for (Character ch : inDegree.keySet()) {
            if (inDegree.get(ch).equals(0)) {
                Q.add(ch);
            }
        }
        // System.out.println(inDegree);
        while (!Q.isEmpty()) {
            Character ch = Q.poll();
            sb.append(ch);
            for (Character nbr : graph.get(ch)) {
                inDegree.put(nbr, inDegree.get(nbr) - 1);
                if (inDegree.get(nbr).equals(0)) {
                    Q.add(nbr);
                }
            }
        }
        
        if (sb.length() < inDegree.size()) {
            return "";
        }
        
        return sb.toString();
       
    }
    
    private Map<Character, List<Character>> graph = new HashMap<>();
    private Map<Character, Integer> inDegree = new HashMap<>();
    
    private void init(String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }
    }

}

class Solution {
    
    private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
    private Map<Character, Boolean> seen = new HashMap<>();
    private StringBuilder output = new StringBuilder();
    
    public String alienOrder(String[] words) {
        
        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (String word : words) {
            for (char c : word.toCharArray()) {
                reverseAdjList.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }
        
        // Step 2: DFS to build up the output list.
        for (Character c : reverseAdjList.keySet()) {
            boolean result = dfs(c);
            if (!result) return "";
        }
        
        
        if (output.length() < reverseAdjList.size()) {
            return "";
        }
        return output.toString();
    }
    
    // Return true iff no cycles detected.
    private boolean dfs(Character c) {
        if (seen.containsKey(c)) {
            return seen.get(c); // If this node was grey (false), a cycle was detected.
        }
        seen.put(c, false);
        for (Character next : reverseAdjList.get(c)) {
            boolean result = dfs(next);
            if (!result) return false;
        }
        seen.put(c, true);
        output.append(c);
        return true;
    }    
}