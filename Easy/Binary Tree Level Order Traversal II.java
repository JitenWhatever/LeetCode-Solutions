/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        
        Queue<TreeNode> Q = new LinkedList<>();
        
        if(root == null) {
            return result;
        }
        
        Q.add(root);
        
        while(!Q.isEmpty()) {
            
            int len = Q.size();
            
            List<Integer> dummy = new ArrayList<>();
            
            
            while(len-- > 0 ) {
                
                root = Q.poll();
                dummy.add(root.val);
                
                if(root.left != null) {
                    Q.add(root.left);
                }
                if(root.right != null) {
                    Q.add(root.right);
                }
            }
            result.add(dummy);
        }
        
         Collections.reverse(result);
        return result;
    }
}
