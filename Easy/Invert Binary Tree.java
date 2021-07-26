/*
Given the root of a binary tree, invert the tree, and return its root.

Example 1:

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
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

    2            2
   / \     =>   / \
  1   3        3   1
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

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
       if (Objects.isNull(root)) {
           return root;
       } 
        
        Queue<TreeNode> Q = new LinkedList<>();
        
        Q.add(root);
        
        while (!Q.isEmpty()) {
            TreeNode node = Q.poll();
            TreeNode dummy = node.left;
            node.left = node.right;
            node.right = dummy;
            
            if (Objects.nonNull(node.left)) {
                Q.add(node.left);
            }
            
            if (Objects.nonNull(node.right)) {
                Q.add(node.right);
            }
        }
        
        return root;
    }

    public TreeNode invertTreeRecursive(TreeNode root) {
        if (Objects.isNull(root)) {
            return root;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}

