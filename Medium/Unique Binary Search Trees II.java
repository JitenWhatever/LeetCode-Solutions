/*
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. 
Return the answer in any order.

Example 1:

Input: 3

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 8
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
