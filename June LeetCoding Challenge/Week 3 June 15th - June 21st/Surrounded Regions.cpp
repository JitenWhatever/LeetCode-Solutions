/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/

class Solution {

private: 
    void dfs(int r, int c, vector<vector<char>>& board){
    
        int H = board.size();
        int W = board[0].size();

        if(r < 0 || r >= H || c < 0 || c >= W || board[r][c] != 'O') {
            return ;
        }
        
        board[r][c] = '#';
        
        dfs(r + 1, c, board);
        dfs(r - 1, c, board);
        dfs(r, c + 1, board);
        dfs(r, c - 1, board);
    }
public:
    void solve(vector<vector<char>>& board) {
        
        int H = board.size();
         if(H == 0) {
            return ;
        }
        
        int W = board[0].size();
        
         if(W == 0) {
            return ;
        }
        
        for(int r = 0; r < H; ++r) {
            if(board[r][0] == 'O') {
                dfs(r, 0, board);
            } 
            
            if(board[r][W - 1] == 'O') {
                dfs(r, W - 1, board);
            }
        }
        
         for(int c = 0; c < W; ++c) {
            if(board[0][c] == 'O') {
                dfs(0, c, board);
            } 
             
            if(board[H - 1][c] == 'O') {
                dfs(H - 1, c, board);
            }
        }
        
        
        for(int r = 0; r < H; ++r) {
            for(int c = 0; c < W; ++c) {
                if(board[r][c] == 'O') {
                    board[r][c] = 'X';
                }
                
                if(board[r][c] == '#') {
                    board[r][c] = 'O';
                }
            }
        }
    }
};

