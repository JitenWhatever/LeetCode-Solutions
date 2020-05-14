/*
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    private Integer maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        
        if(root == null) {
            return 0;
        }
        helperMaxPathSum(root);
        
        return maxPathSum;
    }
    
    private Integer helperMaxPathSum(TreeNode root){
        
        if(root == null) {
            return 0;
        }
        
        Integer left = helperMaxPathSum(root.left);
        Integer right = helperMaxPathSum(root.right);
        
        maxPathSum = Math.max(maxPathSum, root.val + left + right);
        
        return Math.max(0, Math.max(left, right) + root.val);
    }
}