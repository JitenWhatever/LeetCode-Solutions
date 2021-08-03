/*
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

Example 1:
https://assets.leetcode.com/uploads/2021/02/05/bst1.jpg

Input: root = [4,2,6,1,3]
Output: 1

Example 2:
https://assets.leetcode.com/uploads/2021/02/05/bst2.jpg

Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 104].
0 <= Node.val <= 105
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
    public int getMinimumDifference(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        
        Stack<TreeNode> stck = new Stack<>();
        int minDiff = Integer.MAX_VALUE;
        Integer closest = null;
        while(Objects.nonNull(root) || !stck.isEmpty()) {
            while (Objects.nonNull(root)) {
                stck.push(root);
                root = root.left;
            }
            
            TreeNode node = stck.pop();
            
            if (Objects.nonNull(closest)) {
                minDiff = Math.min(minDiff, node.val - closest);
            }
            
            closest = node.val;
            
            root = node.right;
        }
        
        return minDiff;
        
    }
}


public class Solution {
    int min = Integer.MAX_VALUE;
    Integer prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        
        getMinimumDifference(root.left);
        
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;
        
        getMinimumDifference(root.right);
        
        return min;
    }
    
}



public class Solution {
    TreeSet<Integer> set = new TreeSet<>();
    int min = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        
        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min = Math.min(min, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }
        
        set.add(root.val);
        
        getMinimumDifference(root.left);
        getMinimumDifference(root.right);
        
        return min;
    }
}