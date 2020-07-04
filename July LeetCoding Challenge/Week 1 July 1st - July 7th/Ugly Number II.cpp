/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
*/

class Solution {
public:
    int nthUglyNumber(int n) {
        int dp[n];
        
        dp[0] = 1;
        int next_no_2 = 2, next_no_3 = 3, next_no_5 = 5;
        int index_2 = 0, index_3 = 0, index_5 = 0;
        for(int index = 1; index < n; ++index) {
            int next_ugly = min({next_no_2, next_no_3, next_no_5});
            
            dp[index] = next_ugly;
            
            if(next_ugly == next_no_2) {
                ++index_2;
                next_no_2 = dp[index_2]*2;
            }
            if(next_ugly == next_no_3) {
                ++index_3;
                 next_no_3 = dp[index_3]*3;
            }
            if(next_ugly == next_no_5) {
                ++index_5;
                 next_no_5 = dp[index_5]*5;
            }
        }
        
        return dp[n - 1];
    }
};