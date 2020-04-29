/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/
class Solution {
public:
    int maximalSquare(vector<vector<char>>& num) {
        
        if(num.empty()) return 0; 
        int m = num.size();
        int n = num[0].size();
        vector<vector<int>> dp(m, vector<int>(n, 0));
        for(int i=0;i<m;i++){
            if(num[i][0]=='1'){
                dp[i][0] = 1;
            }
            else{
                dp[i][0] = 0;
            }
        } 
        for(int i=0;i<n;i++){
            if(num[0][i]=='1'){
                dp[0][i] = 1;
            }
            else{
                dp[0][i] = 0;
            }
        }
        int max_sqr = 0;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(num[i][j]=='1'){
                    dp[i][j] = 1 + min(dp[i][j-1], min(dp[i-1][j], dp[i-1][j-1]));
                }
                else{
                    dp[i][j] = 0;
                }

               max_sqr = max(max_sqr, dp[i][j]);
            }
        }
        return max_sqr*max_sqr;
    }
};