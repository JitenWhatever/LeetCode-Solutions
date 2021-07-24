/*
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
Constraints:

The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (Objects.isNull(p)) {
            return Objects.isNull(q);
        }
        
         if (Objects.isNull(q)) {
            return Objects.isNull(p);
        }
        

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (Objects.isNull(p)) {
            return Objects.isNull(q);
        }
        if (Objects.isNull(q)) {
            return Objects.isNull(p);
        }
        
        boolean isSameTree = true;
        
        Stack<TreeNode> P = new Stack<>();
        Stack<TreeNode> Q = new Stack<>();
        P.push(p);
        Q.push(q);
        
        while (!P.isEmpty()) {
            p = P.pop();
            q = Q.pop();
            
            if (!isEqual(p, q)) return false;
            
            if (!isEqual(p.left, q.left)) return false;
            if (Objects.nonNull(p.left)) {
                P.push(p.left);
                Q.push(q.left);
            }
            if (!isEqual(p.right, q.right)) return false;
            if (Objects.nonNull(p.right)) {
                P.push(p.right);
                Q.push(q.right);   
            }
            
        }
        
        return isSameTree;
    }
    
    private boolean isEqual(TreeNode p, TreeNode q) {
        if (Objects.isNull(p)) {
            return Objects.isNull(q);
        }
        if (Objects.isNull(q)) {
            return Objects.isNull(p);
        }
        return p.val == q.val;
    }
}