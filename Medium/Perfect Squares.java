/*
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; 
in other words, it is the product of some integer with itself. 
For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 

Constraints:

1 <= n <= 10^4
*/

class Solution {
    public int numSquares(int n) {
        
        if (n < 4) {
            return n;
        }
        
        int[] dp = new int[n + 1];
        
        int[] coins = new int[n + 1];
        
        for (int num = 0; num <= n; ++num) {
            coins[num] = num*num;
        }
        
        for (int num = 0; num < 4; ++num) {
            dp[num] = num;
        }
        
        dp[4] = 1;
        
        for (int amount = 5; amount <= n; ++amount) {
            dp[amount] = amount;
            for (int coin : coins) {
                if (amount >= coin)
                dp[amount] = Math.min(dp[amount], dp[amount - coin] + 1);
            }
        }
        
        return dp[n];
    }
}


public int numSquares(int n) {
    Queue<Integer> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    q.offer(0);
    visited.add(0);
    int depth = 0;
    while(!q.isEmpty()) {
        int size = q.size();
        depth++;
        while(size-- > 0) {
            int u = q.poll();
            for(int i = 1; i*i <= n; i++) {
                int v = u+i*i;
                if(v == n) {
                    return depth;
                }
                if(v > n) {
                    break;
                }
                if(!visited.contains(v)) {
                    q.offer(v);
                    visited.add(v);
                }
            }
        }
    }
    return depth;
}