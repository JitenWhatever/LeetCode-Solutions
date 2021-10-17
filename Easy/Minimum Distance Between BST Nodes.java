/*
Given the root of a Binary Search Tree (BST), 
return the minimum difference between the values of any two different nodes in the tree.

Example 1:
https://assets.leetcode.com/uploads/2021/02/05/bst1.jpg
Input: root = [4,2,6,1,3]
Output: 1

Example 2:
https://assets.leetcode.com/uploads/2021/02/05/bst2.jpg

Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 100].
0 <= Node.val <= 10^5
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
    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int diff = Integer.MAX_VALUE;
        Integer closest = null;
        
        Stack<TreeNode> stck = new Stack<>();
        
        while(root != null || !stck.isEmpty()) {
            while(root != null) {
                stck.add(root);
                root = root.left;
            }
            
            root = stck.pop();
            
            if (closest != null) {
                diff = Math.min(diff, -closest + root.val);
            }
            
           
            closest = root.val;
            
            root = root.right;
        }
        
        
        return diff;
    }
}

class Solution {
    Integer prev, ans;
    public int minDiffInBST(TreeNode root) {
        prev = null;
        ans = Integer.MAX_VALUE;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev != null)
            ans = Math.min(ans, node.val - prev);
        prev = node.val;
        dfs(node.right);
    }
}