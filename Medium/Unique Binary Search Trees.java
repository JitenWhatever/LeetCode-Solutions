/*
Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

Input: n = 3
Output: 5

Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 19

*/

class Solution {
    public int numTrees(int n) {
         
        if(n < 2) {
            return 2;
        }
        
        long countBST = 1;
      // 2nCk = [n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1] 
        for(int i = 0; i < n; ++i) {
            countBST *= (2*n - i) ;
            countBST /= (i + 1);
        }
        
        countBST /= (n + 1);
        
        return (int)countBST;
    }
}

public class Solution {
  public int numTrees(int n) {
    int[] G = new int[n + 1];
    G[0] = 1;
    G[1] = 1;

    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= i; ++j) {
        G[i] += G[j - 1] * G[i - j];
      }
    }
    return G[n];
  }
}