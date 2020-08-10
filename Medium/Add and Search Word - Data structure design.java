/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/

class WordDictionary {

    TrieNode root = null;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char ch : word.toCharArray()) {
            if(node.child[ch - 'a'] == null) {
                node.child[ch - 'a'] = new TrieNode();
            }
            
            node = node.child[ch - 'a'];
        }
        
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word, 0);
        
    }
    private boolean dfs(TrieNode node, String word, int index) {
        
        if(node == null) {
            return false;
        }
        if(index == word.length()) {
            return node.isWord;
        }
        
        char ch = word.charAt(index);
        if(ch != '.') {
            if(node.child[ch - 'a'] == null) {
                return false;
            }
            
            return dfs(node.child[ch - 'a'], word, index + 1);
        }
        
        
        for(int itr = 0; itr < 26; itr++) {
            if(dfs(node.child[itr], word, index + 1)) {
                return true;
            }
        }
            
        return false;
    }
}

class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord = false;
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */