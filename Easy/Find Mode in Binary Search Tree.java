/*
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:
https://assets.leetcode.com/uploads/2021/03/11/mode-tree.jpg

Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
    public int[] findMode(TreeNode root) {
        this.modeCount = 0;
        this.currentCount = 0;
        this.maxCount = 0;
        inOrderRecursive(root);
        
        this.modes = new int[modeCount];
        this.modeCount = 0;
        this.currentCount = 0;
        
        inOrderRecursive(root);
        
        return this.modes;
    }
    
    private int[] modes;
    private int currentVal;
    private int currentCount;
    private int maxCount;
    private int modeCount;
    
    private void inOrderRecursive(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        
        inOrderRecursive(root.left);
        updateMode(root.val);
        inOrderRecursive(root.right);
    }

    private void updateMode(int val) {
        if (val != this.currentVal) {
            this.currentVal = val;
            this.currentCount = 0;
        }
        ++this.currentCount;
        
        if (this.currentCount > this.maxCount) {
            this.maxCount = this.currentCount;
            this.modeCount = 1;
        } else if (this.currentCount == this.maxCount){
            if (Objects.nonNull(this.modes)) {
                this.modes[this.modeCount] = currentVal;
            }
            ++this.modeCount;
        }
    }
    
}