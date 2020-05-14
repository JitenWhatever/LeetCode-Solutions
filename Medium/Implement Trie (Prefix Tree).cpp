/*
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true

Note:
    You may assume that all inputs are consist of lowercase letters a-z.
    All inputs are guaranteed to be non-empty strings.
*/

class Trie {
private:
    struct TrieNode{
        struct TrieNode *child[26];
        bool isEnd;
    };
    
    TrieNode *searchPrefix(string word) {
        TrieNode *node = root;
        for(char ch : word) {
            if(!node->child[ch - 'a']){
                return NULL;
            }
            
            node = node->child[ch - 'a'];
        }
        
        return node;
    }
public:
    /** Initialize your data structure here. */
    TrieNode *root = NULL;
    
    Trie() {
        root = new TrieNode();   
    }
    
    /** Inserts a word into the trie. */
    void insert(string word) {
        TrieNode *node = root;
        
        for(char ch : word) {
            if(!node->child[ch - 'a']) {
                node->child[ch - 'a'] = new TrieNode();
            }
            
            node = node->child[ch - 'a'];
        }
    
        node->isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    bool search(string word) {
        TrieNode *node = searchPrefix(word);
        
        return (node != NULL && node->isEnd) ;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string prefix) {
        return (searchPrefix(prefix) != NULL);
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */