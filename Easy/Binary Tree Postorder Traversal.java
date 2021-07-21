/*
Given the root of a binary tree, return the postorder traversal of its nodes' values.

Follow up: Recursive solution is trivial, could you do it iteratively?

 

Example 1:
https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg

Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:


Input: root = []
Output: []

Example 3:


Input: root = [1]
Output: [1]

Example 4:
https://assets.leetcode.com/uploads/2020/08/28/pre3.jpg

Input: root = [1,2]
Output: [2,1]

Example 5:
https://assets.leetcode.com/uploads/2020/08/28/pre3.jpg

Input: root = [1,null,2]
Output: [2,1]
 

Constraints:

The number of the nodes in the tree is in the range [0, 100].
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return postOrder;
        }
        
        Stack<TreeNode> stck = new Stack<>();
        stck.push(root);
        while (!stck.isEmpty()) {
            root = stck.pop();
            postOrder.add(0, root.val);
            
            if (Objects.nonNull(root.left)) {
                stck.push(root.left);
            }
            
             if (Objects.nonNull(root.right)) {
                stck.push(root.right);
            }
        }
        
        // postOrderRecursive(root, postOrder);
        
        return postOrder;
        
    }
    
    private void postOrderRecursive(TreeNode root, List<Integer> postOrder) {
        
        if (Objects.isNull(root)) {
            return ;
        }
        
        postOrderRecursive(root.left, postOrder);
        postOrderRecursive(root.right, postOrder);
        postOrder.add(root.val);
    }
}