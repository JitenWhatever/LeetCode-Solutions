/*
Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 

Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 

Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
*/

class Solution {
    public int mctFromLeafValues(int[] arr) {
        
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        dp = new TreeNode[arr.length][arr.length];
    
        return recurse(0, arr.length - 1, arr).result;
    }
    
    class TreeNode {
        int factor;
        int result;
        
        TreeNode(int f, int r) {
            factor = f;
            result = r;
        }
    }
    
    TreeNode[][] dp;
    private TreeNode recurse(int start, int end, int[] arr) {
        
        if( dp[start][end] != null) {
            return  dp[start][end];
        }
        if(start > end) {
            return new TreeNode(1, 0);
        }
        
        if(start == end) {
            return new TreeNode(arr[start], 0);
        }
        TreeNode min = null;
        
        for(int index = start; index < end; ++index) {
            TreeNode left = recurse(start, index, arr);
            TreeNode right = recurse(index + 1, end, arr);
            
            TreeNode root = new TreeNode(Math.max(left.factor, right.factor), left.result + right.result + left.factor * right.factor);
            
            if(min == null) {
                min = root;
            }
            else if(min.result > root.result){
                min = root;
            }
        }
        
        dp[start][end] = min;
        
        return  dp[start][end];
    }
}