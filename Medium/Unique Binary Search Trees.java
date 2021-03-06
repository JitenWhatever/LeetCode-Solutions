/*
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

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

*/

class Solution {
    public int numTrees(int n) {
         
        if(n == 0) {
            return 0;
        }
        
        if(n == 1) {
            return 1;
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