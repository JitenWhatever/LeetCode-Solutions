/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Constraints:

0 <= n <= 8
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
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) {
            return new ArrayList<TreeNode>();
        }
    
        return allTrees(1, n);
    }
    
    private List<TreeNode> allTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        
        if(start > end) {
            result.add(null);
            
            return result;
        }
        
        for(int root = start; root <= end; ++root) {
            List<TreeNode> leftTree = allTrees(start , root - 1);
            List<TreeNode> rightTree = allTrees(root + 1, end);
            
            for(TreeNode left : leftTree) {
                for(TreeNode right : rightTree) {
                    TreeNode newNode = new TreeNode(root, left, right);
                    
                    result.add(newNode);
                }
            }
            
        }
        
        return result;
    }
}
