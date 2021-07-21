/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
        (1)
            \
            (2)
            /
        (3)   

Input: root = [1,null,2,3]
Output: [1,3,2]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
Example 4:
(1)
    \
     (2)

Input: root = [1,2]
Output: [2,1]
Example 5:
    (1)
    /
  (2)

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        if (Objects.isNull(root)) {
            return inorder;
        }
        
        Stack<TreeNode> stck = new Stack<>();
        
        while (Objects.nonNull(root) || !stck.isEmpty()) {
            while (Objects.nonNull(root)) {
                stck.push(root);
                root = root.left;
            }
            
            root = stck.pop();
            inorder.add(root.val);
            root = root.right;
        }
        
        return inorder;
    }
}

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
    public List<Integer> inorderTraversal(TreeNode root) {
        this.inorder = new ArrayList<>();
        
        inorderRecursive(root);
        
        return this.inorder;
    }
    
    private void inorderRecursive(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        
        inorderRecursive(root.left);
        this.inorder.add(root.val);
        inorderRecursive(root.right);
    }
    

    private List<Integer> inorder;
}