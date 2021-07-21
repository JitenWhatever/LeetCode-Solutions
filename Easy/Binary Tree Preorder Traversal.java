/*
Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
    1
     \
      2
     /  
    3

Input: root = [1,null,2,3]
Output: [1,2,3]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
Example 4:
1
 \
  2  

Input: root = [1,2]
Output: [1,2]
Example 5:
    1
   /
  2
Input: root = [1,null,2]
Output: [1,2]
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 

Follow up: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (Objects.isNull(root)) {
            return preOrder;
        }
        preOrderRecursive(root, preOrder);
        Stack<TreeNode> stck = new Stack<>();
        stck.push(root);
        while(!stck.isEmpty()) {
            root = stck.pop();
        
            preOrder.add(root.val);
            
            if (Objects.nonNull(root.right)) {
                stck.push(root.right);
            }
            
            if (Objects.nonNull(root.left)) {
                stck.push(root.left);
            }
        }
        
        return preOrder;
    }
    
    private void preOrderRecursive(TreeNode root, List<Integer> preOrder) {
        if (Objects.isNull(root)) {
            return ;
        } 
        
        preOrder.add(root.val);
        preOrderRecursive(root.left, preOrder);
        preOrderRecursive(root.right, preOrder);
    }
}