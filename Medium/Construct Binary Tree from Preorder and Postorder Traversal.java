/*
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
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
    
    private int preIndex = 0, postIndex = 0;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        
        return helper(pre, post);
    }

    private TreeNode helper(int[] pre, int[] post) {
        
        TreeNode root = new TreeNode(pre[preIndex++]);
        
        if(root.val != post[postIndex]) root.left = helper(pre, post);
        if(root.val != post[postIndex]) root.right = helper(pre, post);;
        
        ++postIndex;
        return root;
    }
}