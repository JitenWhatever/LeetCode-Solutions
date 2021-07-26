/*
Find the sum of all left leaves in a given binary tree.

Example 1:

    3
   / \
  9  20
    /  \
   15   7

Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:

Input: root = [1]
Output: 0
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
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
    public int sumOfLeftLeaves(TreeNode root) {
        
        int sum = 0;
        if(root == null) {
            return 0;
        }
        
        if(root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);
        
        return sum;
    }
}

class Solution { 
    
  private boolean isLeaf(TreeNode node) {
    return node != null && node.left == null && node.right == null;
  }

  public int sumOfLeftLeaves(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int total = 0;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode subRoot = stack.pop();
      // Check if the left node is a leaf node.
      if (isLeaf(subRoot.left)) {
        total += subRoot.left.val;
      }
      // If the right node exists, put it on the stack.
      if (subRoot.right != null) {    
        stack.push(subRoot.right);
      }
      // If the left node exists, put it on the stack.
      if (subRoot.left != null) {
        stack.push(subRoot.left);
      }
    }

    return total;
  }
}