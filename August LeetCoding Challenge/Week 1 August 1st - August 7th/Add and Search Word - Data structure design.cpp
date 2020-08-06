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
public:
    
    struct TrieNode {
       bool isEnd;
        map<char, TrieNode*> childs;
    };
    /** Initialize your data structure here. */
    TrieNode* root ;
    WordDictionary() {
        root = new TrieNode();
    }
    
    void insert(string word) {
        TrieNode* node = root;
        for(char ch : word) {
            if(!node->childs[ch]) {
                node->childs[ch] = new TrieNode();
            }
            
             node = node->childs[ch];
        }
        
        node->isEnd = true;
    }
    
    bool dfs(string word, int index, TrieNode* node) {
        if(index == word.length()) {
            return node->isEnd;
        }
        
        if(word[index] != '.') {
            
            if(!node->childs.count(word[index])) {
                return false;
            }
            
            return dfs(word, index + 1, node->childs[word[index]]);
        }
        
        for(auto itr : node->childs) {
            
            if(dfs(word, index + 1, itr.second)) {
                return true;
            }
        }
        
        
        return false;
        
    }
    /** Adds a word into the data structure. */
    void addWord(string word) {
        insert(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    bool search(string word) {
        TrieNode* node = root;
        return dfs(word, 0, node);
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */