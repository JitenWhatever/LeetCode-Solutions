/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

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
    
    private boolean dfs(int r, int c, char[][] board, int currentIndex, String word) {
        
        if(currentIndex == word.length()) {
            return true;
        }
        
        if(r < 0 || r >= board.length || c < 0 || c >= board[r].length || board[r][c] != word.charAt(currentIndex)) {
            return false;
        }
        
        char ch = board[r][c];
        board[r][c] = '#';

        ++currentIndex;
        boolean found = ( dfs(r + 1, c, board, currentIndex, word) || dfs(r - 1, c, board, currentIndex, word) || dfs(r, c + 1, board, currentIndex, word) || dfs(r, c - 1, board, currentIndex, word) );
        board[r][c] = ch;
        return found;   
    }
    public boolean exist(char[][] board, String word) {
        
        for(int r = 0; r < board.length; ++r) {
            for(int c = 0; c < board[r].length; ++c) {
                if(board[r][c] == word.charAt(0) && dfs(r, c, board, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }
}