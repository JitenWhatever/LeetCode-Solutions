/*
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.

Example 1:
https://assets.leetcode.com/uploads/2021/03/12/closest1-1-tree.jpg

Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
Example 2:

Input: root = [1], target = 4.428571
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109
*/

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
    public int closestValue(TreeNode root, double target) {
        int firstClosest = root.val;
        TreeNode node = target < firstClosest ? root.left: root.right;
        
        if (Objects.isNull(node)) {
            return root.val;
        }
        
        int secondClosest = closestValue(node, target);
        
        return Math.abs(firstClosest - target) < Math.abs(secondClosest - target) ? firstClosest : secondClosest;
    }
};


class Solution {
  public int closestValue(TreeNode root, double target) {
    int val, closest = root.val;
    while (root != null) {
      val = root.val;
      closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
      root =  target < root.val ? root.left : root.right;
    }
    return closest;
  }
}