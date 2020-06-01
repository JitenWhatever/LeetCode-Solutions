/*
Invert a binary tree.

Example:

Input:
     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1

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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return root;
        }
        
        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        
        Q.add(root);
        while(!Q.isEmpty()){
            TreeNode node = Q.poll();
            TreeNode dummyNode = node.left;
            node.left = node.right;
            node.right = dummyNode;
            
            if(node.left != null) Q.add(node.left);
            if(node.right != null) Q.add(node.right);
        }
        
        return root;
    }
}