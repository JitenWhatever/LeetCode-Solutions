/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
             1   
          /  |  \
         2   |   2
        / \  |  /  \
       3   4 | 4    3
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
             1   
          /  |  \
         2   |   2
          \  |    \
           3 |     3

Input: root = [1,2,2,null,3,null,3]
Output: false
 
Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 

Follow up: Could you solve it both recursively and iteratively?
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
    public boolean isSymmetric(TreeNode root) {
        if (Objects.isNull(root)) {
            return true;
        }
        
       return isSameTree(root.left, root.right);
    }
    
    private boolean isSameTree(TreeNode p, TreeNode q) {
         if (Objects.isNull(p)) {
            return Objects.isNull(q);
        }
        
         if (Objects.isNull(q)) {
            return Objects.isNull(p);
        }
        

        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}

public boolean isSymmetric(TreeNode root) {
    Queue<TreeNode> Q = new LinkedList<>();
    Q.add(root);
    Q.add(root);
    while (!Q.isEmpty()) {
        TreeNode node1 = Q.poll();
        TreeNode node2 = Q.poll();
        if (node1 == null && node2 == null) continue;
        if (node1 == null || node2 == null) return false;
        if (node1.val != node2.val) return false;
        Q.add(node1.left);
        Q.add(node2.right);
        Q.add(node1.right);
        Q.add(node2.left);
    }
    return true;
}