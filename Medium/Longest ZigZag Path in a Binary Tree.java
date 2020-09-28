/*
Given a binary tree root, a ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right then move to the right child of the current node otherwise move to the left child.
Change the direction from right to left or right to left.
Repeat the second and third step until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

 

Example 1:
https://assets.leetcode.com/uploads/2020/01/22/sample_1_1702.png


Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

Example 2:
https://assets.leetcode.com/uploads/2020/01/22/sample_2_1702.png


Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

Example 3:

Input: root = [1]
Output: 0
 

Constraints:

Each tree has at most 50000 nodes..
Each node's value is between [1, 100].
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
    public int longestZigZag(TreeNode root) {
        
        solve(root);
        
        return result;
    }
    
    private int result = 0;
    
    private int[] solve(TreeNode root) {
        if(root == null) {
            return new int[]{-1, -1};
        }
        
        int[] left = solve(root.left);
        int[] right = solve(root.right);
        
        int a = left[1] + 1;
        int b = right[0] + 1;
        
        result = Math.max(result, a);
        result = Math.max(result, b);
        
        return new int[]{a, b};
    }
}