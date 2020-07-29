/* 
Given a binary tree, return the sum of values of its deepest leaves.
 

Example 1:
https://assets.leetcode.com/uploads/2019/07/31/1483_ex1.png


Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
 

Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
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
    public int deepestLeavesSum(TreeNode root) {
       
        h = height(root);
        helper(root, 1);
        
        return sum;
    }
    
    private int h;
    private int sum = 0;
    
    private void helper(TreeNode root, int depth) {
        
        if(root == null) {
            return ;
        }
        if(depth == h) {
            sum += root.val;
            return ;
        }
        
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }
    
    private int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left = height(root.left);
        int right = height(root.right);
        
        return Math.max(left, right) + 1;
    }
}