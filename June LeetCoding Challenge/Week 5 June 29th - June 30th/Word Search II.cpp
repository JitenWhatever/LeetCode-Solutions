/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
*/

class Solution {
private:
    struct TrieNode {
        unordered_map<char, TrieNode*> child;
        // bool isWordEnd;
        string word;
    };
    
    void insert(TrieNode* root, vector<string> words) {
        for(string word : words) {
            TrieNode* currentNode = root;
            
            for(char ch : word) {
                if(!currentNode->child[ch]) {
                    currentNode->child[ch] = new TrieNode();
                }
                
                currentNode = currentNode->child[ch];
            }
            currentNode->word = word;
            // cout<<currentNode->word;
            // currentNode->isWordEnd = true;
        }
    }
    void dfs(int r, int c, TrieNode* currentNode, vector<vector<char>>& board, vector<string>& result) {
        
        if(r < 0 || r >= board.size() || c < 0 ||  c >= board[r].size() || board[r][c] == '#' || !currentNode->child[board[r][c]]) {
            return ;
        }
        
        char ch = board[r][c];
        
        board[r][c] = '#';
        
        currentNode = currentNode->child[ch];
        // cout<<
        if(!(currentNode->word).empty()) {
            result.push_back(currentNode->word);
            currentNode->word = "";
        }
        
        dfs(r + 1, c, currentNode, board, result);
        dfs(r - 1, c, currentNode, board, result);
        dfs(r, c + 1, currentNode, board, result);
        dfs(r, c - 1, currentNode, board, result);
        
        board[r][c] = ch;
    }
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        
        TrieNode* root = new TrieNode();
        vector<string> result;
        insert(root, words);
        for(int r = 0; r < board.size(); ++r) {
            for(int c = 0; c < board[r].size(); ++c) {
                char ch = board[r][c];
                if(root->child[ch]){
                    dfs(r, c, root, board, result);
                }
            }
        }
        return result;
    }
};
