/*
A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

 

Example 1:
https://assets.leetcode.com/uploads/2018/12/28/unival_bst_1.png

Input: [1,1,1,1,1,null,1]
Output: true

Example 2:
https://assets.leetcode.com/uploads/2018/12/28/unival_bst_2.png

Input: [2,2,2,5,2]
Output: false
 

Note:

The number of nodes in the given tree will be in the range [1, 100].
Each node's value will be an integer in the range [0, 99].
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

O(N), Space(uniqueValue)
class Solution {
    public boolean isUnivalTree(TreeNode root) {   
        Set<Integer> set = new HashSet<>();
        
        dfs(root, set);
        
        return set.size() == 1;
    }
    
    private void dfs(TreeNode root, Set<Integer> set) {
        if(Objects.isNull(root)) {
            return ;
        }
        
        set.add(root.val);
        
        if(set.size() > 1) {
            return ;
        }
        
        dfs(root.left, set);
        dfs(root.right, set);
    }
}