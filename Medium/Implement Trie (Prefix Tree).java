/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. 
There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), 
and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
 

Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
*/

class Trie {

    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = this.root;
        
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            
            node = node.get(ch);
        }
        
        node.setEnd();
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        
        return Objects.nonNull(node) && node.isEnd();
        
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        
        return Objects.nonNull(node);
    }
    
    private TrieNode searchPrefix(String word) {
        TrieNode node = this.root;
        
        for (char ch : word.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        
        return node;
    }
}

class TrieNode {
    private int limit = 26;
    private TrieNode[] links;
    private boolean isEnd;
    
    public TrieNode() {
        this.links = new TrieNode[this.limit];  
    }
    
    public boolean containsKey(char key) {
        return Objects.nonNull(this.links[key - 'a']);
    }
    
    public TrieNode get(char key) {
        return this.links[key - 'a'];
    }
    
    public void put(char key, TrieNode node) {
        this.links[key - 'a'] = node;
    }
    
     
    public void setEnd() {
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */