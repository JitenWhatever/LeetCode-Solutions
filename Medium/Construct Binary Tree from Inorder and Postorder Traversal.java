/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }
    
    
    private TreeNode helper(int[] inorder, int[] post, int inStart, int inEnd, int index) {
        
        if(index < 0 || inStart > inEnd) {
            return null;
        }
    
        TreeNode root = new TreeNode(post[index]);
        
        int inIndex = 0;
        for(int itr = inStart; itr <= inEnd; ++itr) {
            if(root.val == inorder[itr]) {
                inIndex = itr;
                break;
            }
        }
    
        root.left = helper(inorder, post, inStart, inIndex - 1, index - 1 - inEnd + inIndex) ;
        root.right = helper(inorder, post, inIndex + 1, inEnd, index - 1);

        return root;
    }
}
