import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
A transformation sequence from word beginWord to word 
endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, 
return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>(wordList);
        
        if (!visited.contains(endWord)) {
            return 0;   
        }
        
        Queue<String> Q = new LinkedList<>();
        visited.remove(beginWord);
        Q.add(beginWord);
        
        int level = 0;
        
        while (!Q.isEmpty()) {
            int size = Q.size();
            ++level;
            
            while (size-- > 0) {
                String currentWord = Q.poll();
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                List<String> nbrs = calculateNeighbors(currentWord);
                for (String nbr : nbrs) {
                    if (visited.contains(nbr)) {
                        visited.remove(nbr);
                        Q.add(nbr);
                    }
                }
            }
        }
        
        return 0;
        
    }
    
    private List<String> calculateNeighbors(String currentWord) {
        char[] chars = currentWord.toCharArray();
        List<String> nbrs = new ArrayList<>();
        
        for (int itr = 0; itr < chars.length; ++itr) {
            char ch = chars[itr];
            for (char c = 'a'; c <= 'z'; ++c) {
                if (ch != c) {
                    chars[itr] = c;
                    nbrs.add(new String(chars));
                }
            }
            chars[itr] = ch;
        }
        
        return nbrs;
    }


// Time Complexity: O(M^2*N)
// Space Complexity: O(M^2*N)

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>(wordList);
        
        if (!visited.contains(endWord)) {
            return 0;
        }
        
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int len = 1;
        
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            
            Set<String> newBeginSet = new HashSet<>();
            ++len;
            for (String word : beginSet) {
                List<String> nbrs = calculateNeighbors(word);
                for (String nbr : nbrs) {
                    if (endSet.contains(nbr)) {
                        return len;
                    }
                    if (visited.contains(nbr)) {
                        newBeginSet.add(nbr);
                        visited.remove(nbr);
                    }
                }
            }
            beginSet = newBeginSet;
        }
        
        return 0;
        
    }

}

// Time Complexity: O(M^2*N)
// Space Complexity: O(M^2*N)