/*
You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026

Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
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
    public int sumNumbers(TreeNode root) {
        this.pathsSum = 0;
        
        if (Objects.isNull(root)) {
            return this.pathsSum;
        }
        
        int currentPathSum = 0;
        
        Stack<Pair<TreeNode, Integer>> stck = new Stack<>();
        
        stck.push(new Pair(root, 0));
        
        while (!stck.isEmpty()) {
            Pair<TreeNode, Integer> currentNode = stck.pop();
            root = currentNode.getKey();
            currentPathSum = currentNode.getValue()*10 + root.val;
            
            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                this.pathsSum +=  currentPathSum;
            }
            
            if (Objects.nonNull(root.left)) {
                stck.push(new Pair(root.left, currentPathSum));
            }
            
            if (Objects.nonNull(root.right)) {
                stck.push(new Pair(root.right, currentPathSum));
            }
            
        }
        
        return this.pathsSum;
    }
    
    private int pathsSum;
    
    private  void pathsSumRecursive(TreeNode root, int currentPathSum) {
        if (Objects.isNull(root)) {
            return ;
        }
        
        currentPathSum = currentPathSum * 10 + root.val;
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            this.pathsSum += currentPathSum;
            return ;
        }
        
        pathsSum(root.left, currentPathSum);             
        pathsSum(root.right, currentPathSum);
    }
}