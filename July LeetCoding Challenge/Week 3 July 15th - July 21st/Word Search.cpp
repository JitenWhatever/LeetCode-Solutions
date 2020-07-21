/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 

Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
*/

class Solution {
private:
     bool dfs(int r , int c , vector<vector<char>>& board, string word , int index) {
        int n = board.size() , m = board[0].size();
        
        if(index == word.length()) {
            return true;
        } 
        
        if(r < 0 || r >= n || c < 0 || c >= m || board[r][c] != word[index]){
             return false;
        }
        
        char ch = board[r][c];
        board[r][c] = '#';
        
        bool found = dfs(r + 1, c, board, word, index + 1)
                   || dfs(r, c + 1, board, word, index + 1)
                   || dfs(r - 1, c, board, word, index + 1)
                   || dfs(r, c - 1, board, word, index + 1);
        
        board[r][c] = ch;
        
        return found;
    }
public:
     bool exist(vector<vector<char>>& board, string word) {
        if(board.size() == 0) return false;
        
        for(int r = 0 ; r < board.size(); r++) {
            for(int c = 0 ; c < board[r].size(); c++) {
                if(board[r][c] == word[0] && dfs(r, c, board, word, 0)) {
                    return true;
                }
            }
        }       
        
        return false;        
        
    }
};