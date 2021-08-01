/*
Given a binary tree, you need to compute the length of the diameter of the tree. 
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.


Example 1:
Given a binary tree

          1
         / \
        2   3
       / \     
      4   5    

Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].


Example 2:

Input: root = [1,2]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
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
    public int diameterOfBinaryTree(TreeNode root) {
       this.diameter = 0; 
        // diameterOfBinaryTreeRecursive(root);

        if (Objects.isNull(root)) {
            return this.diameter;
        }

        Stack<TreeNode> stck = new Stack<>();
        Map<TreeNode, Integer> pathToDepth = new HashMap<>();
        stck.push(root);

        while (!stck.isEmpty()) {
            root = stck.peek();

            if (Objects.nonNull(root.left) && !pathToDepth.containsKey(root.left)) {
                stck.push(root.left);
            } else if (Objects.nonNull(root.right) && !pathToDepth.containsKey(root.right)) {
                stck.push(root.right);
            } else {
                root = stck.pop();
                int left = pathToDepth.getOrDefault(root.left, 0);
                int right = pathToDepth.getOrDefault(root.right, 0);
                int max = 1 + Math.max(left, right);
                pathToDepth.put(root, max);
                this.diameter = Math.max(this.diameter, left + right);
            }
        }

        return this.diameter;
    }
    
    private int diameter;
    
    private int diameterOfBinaryTreeRecursive(TreeNode root) {
        if (Objects.isNull(root)){
            return 0;
        }   
        
        int left = diameterOfBinaryTreeRecursive(root.left);
        int right = diameterOfBinaryTreeRecursive(root.right);
        
        this.diameter = Math.max(this.diameter, left + right);
        
        return Math.max(left, right) + 1;
    }
}