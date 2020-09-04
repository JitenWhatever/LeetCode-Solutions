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
       
        result = new ArrayList<>();
        
        // postOrderRecursive(root); // 0 ms
        
        postOrderIterative(root); // 0 ms
        
        return result;
    }
    
    private List<Integer> result;
    
    private void postOrderRecursive(TreeNode root) {
        if(root == null) {
            return ;
        }
        
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        result.add(root.val);
    }
    
    private void postOrderIterative(TreeNode root) {
        if(root == null) {
            return;
        }
        
        Stack<TreeNode> st = new Stack<>();
        
        st.push(root);
        
        while(!st.isEmpty()) {
            root = st.peek();
            if(root.left == null && root.right == null) {
                st.pop();
                result.add(root.val);
            }
            
            if(root.right != null) {
                st.push(root.right);
                root.right = null;
            }
            
            if(root.left != null) {
                st.push(root.left);
                root.left = null;
            }
        }
    }
}