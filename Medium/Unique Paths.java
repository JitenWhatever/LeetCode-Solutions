/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Example 1:
https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png

Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
Example 3:

Input: m = 7, n = 3
Output: 28
Example 4:

Input: m = 3, n = 3
Output: 6
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 109.
*/

class Solution {
    public int uniquePaths(int H, int W) {
        
        Integer[][] dp = new Integer[H][W];
        
        for(Integer row = 0; row < H; ++row) {
            for(Integer column = 0; column < W; ++column) {
                if(row == 0 || column == 0) {
                    dp[row][column] = 1;
                    continue;
                }
                dp[row][column] = dp[row - 1][column] + dp[row][column - 1];
            }
        }
        
        return dp[H - 1][W - 1];
    }
}

class Solution {
  public int uniquePaths(int m, int n) {
    if (m == 1 || n == 1) {
      return 1;
    }
    return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
  }
}

/*
Could one do better than \mathcal{O}(N \times M)O(N×M)? The answer is yes.

The problem is a classical combinatorial problem: there are h + vh+v moves to do from start to finish, h = m - 1h=m−1 horizontal moves, and v = n - 1v=n−1 vertical ones. One could choose when to move to the right, i.e. to define hh horizontal moves, and that will fix vertical ones. Or, one could choose when to move down, i.e. to define vv vertical moves, and that will fix horizontal ones.

traversal

In other words, we're asked to compute in how many ways one could choose pp elements from p + kp+k elements. In mathematics, that's called binomial coefficients

C_{h + v}^{h} = C_{h + v}^{v} = \frac{(h + v)!}{h! v!}C 
h+v
h
​
 =C 
h+v
v
​
 = 
h!v!
(h+v)!
​
 

The number of horizontal moves to do is h = m - 1h=m−1, the number of vertical moves is v = n - 1v=n−1. That results in a simple formula

C_{h + v}^{h} = \frac{(m + n - 2)!}{(m - 1)! (n - 1)!}C 
h+v
h
​
 = 
(m−1)!(n−1)!
(m+n−2)!
​
 

The job is done. Now time complexity will depend on the algorithm to compute factorial function (m + n - 2)!(m+n−2)!. In short, standard computation for k!k! using the definition requires \mathcal{O}(k^2 \log k)O(k 
2
 logk) time, and that will be not as good as DP algorithm.

The best known algorithm to compute factorial function is done by Peter Borwein. The idea is to express the factorial as a product of prime powers, so that k!k! can be computed in \mathcal{O}(k (\log k \log \log k)^2)O(k(logkloglogk) 
2
 ) time. That's better than \mathcal{O}(k^2)O(k 
2
 ) and hence beats DP algorithm.

The authors prefer not to discuss here various factorial function implementations, and hence provide Python3 solution only, with built-in divide and conquer factorial algorithm. If you're interested in factorial algorithms, please check out good review on this page.

Implementation
from math import factorial
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        return factorial(m + n - 2) // factorial(n - 1) // factorial(m - 1)

Complexity Analysis

Time complexity: \mathcal{O}((M + N) (\log (M + N) \log \log (M + N))^2)O((M+N)(log(M+N)loglog(M+N)) 
2
 ).

Space complexity: \mathcal{O}(1)O(1).
*/
