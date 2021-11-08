/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example 1:
Input: board = [
    ["A","B","C","E"],
    ["S","F","C","S"],
    ["A","D","E","E"]
    ], 
    word = "ABCCED"
Output: true

Example 2:

Input: board = [
    ["A","B","C","E"],
    ["S","F","C","S"],
    ["A","D","E","E"]
    ], 
    word = "SEE"
Output: true

Example 3:
Input: board = [
    ["A","B","C","E"],
    ["S","F","C","S"],
    ["A","D","E","E"]
    ], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?
*/

class Solution {
    public boolean exist(char[][] board, String word) {
    
        for (int r = 0; r < board.length; ++r) {
            for (int c = 0; c < board[r].length; ++c) {
                if (word.charAt(0) == board[r][c] && dfs(board, r, c, 0, word)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int r, int c, int currentIndex, String word) {
        
        if (currentIndex >= word.length()) {
            return true;
        }
        
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '*' || word.charAt(currentIndex) != board[r][c]) {
            return false;
        }
        
        char ch = board[r][c];
        board[r][c] = '*';
        
        boolean found = (dfs(board, r + 1, c, currentIndex + 1, word) || dfs(board, r - 1, c, currentIndex + 1, word) || 
                        dfs(board, r, c + 1, currentIndex + 1, word) || dfs(board, r, c - 1, currentIndex + 1, word));
        
        board[r][c] = ch;
        
        return found;
    }
}