/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
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
        result =  new ArrayList<>();
        
        // postOrderRecursive(root); // 0 ms 
        
        postOrderIterative(root); // 0 ms
        
        return result;
    }
    
    private List<Integer> result;
    
    private void postOrderRecursive(TreeNode root) {
        if(root == null) {
            return ;
        }
        
        result.add(root.val);
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
    }
    
    private void postOrderIterative(TreeNode root) {
        if(root == null) {
            return ;
        }
        
        Stack<TreeNode> st = new Stack<>();
        
        st.push(root);
        
        while(!st.isEmpty()) {
            
            root = st.pop();
            result.add(root.val);

            if(root.right != null) {
                st.push(root.right);
            }
            
            if(root.left != null) {
                st.add(root.left);
            }
        }
    }
    
}