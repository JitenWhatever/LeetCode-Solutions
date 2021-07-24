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
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new  ArrayList<>();
        
        if (Objects.isNull(root)) {
            return levelOrder;
        }
        
        Queue<TreeNode> Q = new LinkedList<>();
        
        Q.add(root);
        
        while (!Q.isEmpty()) {
            int size = Q.size();
            List<Integer> level = new ArrayList<>();
            
            while (size-- > 0) {
                root = Q.poll();
                level.add(root.val);
                
                if (Objects.nonNull(root.left)) {
                    Q.add(root.left);
                }
                
                if (Objects.nonNull(root.right)) {
                    Q.add(root.right);
                }
            }
            
            levelOrder.add(level);
        }
        Collections.reverse(levelOrder);
        return levelOrder;
    }
}