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
    class TrieNode {
        TrieNode[] child ;
        String word;
        
        public TrieNode() {
            child = new TrieNode[26];
            word  = null;
        }
    }
    
    private void insert(TrieNode root, String[] words) {
        for(String word : words) {
            TrieNode currentNode = root;
            
            for(char ch : word.toCharArray()) {
                int index = ch - 'a';
                if(currentNode.child[index] == null) {
                    currentNode.child[index] = new TrieNode();
                }
                
                currentNode = currentNode.child[index];
            }
            
            currentNode.word = word;
        }
    }
    
    private void dfs(int r, int c, char[][] board, TrieNode currentNode, List<String> result) {
        if(r < 0 || r >= board.length || c < 0 || c >= board[r].length || board[r][c] == '#' || currentNode.child[board[r][c] - 'a'] == null) {
            return ;
        }
        
        char ch = board[r][c];
        
        board[r][c] = '#';
        
        currentNode = currentNode.child[ch - 'a'];
        
        if(currentNode.word != null) {
            result.add(currentNode.word);
            currentNode.word = null;
        }
        
        dfs(r + 1, c, board, currentNode, result);
        dfs(r - 1, c, board, currentNode, result);
        dfs(r, c + 1, board, currentNode, result);
        dfs(r, c - 1, board, currentNode, result);
        board[r][c] = ch;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        
        TrieNode root = new TrieNode();
        insert(root, words);
        List<String> result = new ArrayList<>();
        
        for(int r = 0; r < board.length; ++r) {
            for(int c = 0; c < board[r].length; ++c) {
                int index = board[r][c] - 'a';
                if(root.child[index] != null) {
                    dfs(r, c, board, root, result);
                }
            }
        }
        
        return result;
    }
}