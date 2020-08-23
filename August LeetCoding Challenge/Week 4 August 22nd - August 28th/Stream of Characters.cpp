/*
Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, 
including this letter just queried) spell one of the words in the given list.
 

Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 

Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
*/


class StreamChecker {
public:
    struct TrieNode {
        TrieNode* child[26];
        bool isWord = false;
    };
    
    TrieNode* root = NULL;
    string queryString = "";
    
    void insert(vector<string>& words) {
        
        for(string word : words) {
            TrieNode* node = root;
            for(int index = word.length() - 1; index >= 0; --index) {
                if(node->child[word[index] - 'a'] == NULL) {
                    node->child[word[index] - 'a'] = new TrieNode();
                }
                
                node = node->child[word[index] - 'a'];
            }
            
            node->isWord = true;
        }
    }
    
    StreamChecker(vector<string>& words) {
        root =  new TrieNode();
        
        insert(words);
    }
    
    bool query(char letter) {
        queryString += letter;
        // cout<<queryString<<"\n";
        TrieNode* node = root;
        for(int index = queryString.length() - 1; index >= 0; --index) {
            node = node->child[queryString[index] - 'a'];
            
            if(node != NULL && node->isWord) {
                return true;
            }
            
            if(node == NULL) {
                return false;
            }
            
        }
        
        return false;
    }
    
};

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker* obj = new StreamChecker(words);
 * bool param_1 = obj->query(letter);
 */