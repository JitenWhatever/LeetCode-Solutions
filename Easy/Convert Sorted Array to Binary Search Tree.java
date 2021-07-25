/*
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

 

Example 1:
         0
       /  \
      3    9
     /     /
    -10    5
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:
         0
       /  \
    -10    5
      \     \
      -3     9

Example 2:
    3     1
   /       \
  1         3
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        
        return buildBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildBST(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        
        int mid = low + (high - low)/2;
        
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, low, mid - 1);
        root.right = buildBST(nums, mid + 1, high);
        
        return root;
    }
}

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        
        return buildBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildBST(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        
        int mid = low + (high - low)/2;
        if ((low + high)%2 == 1) ++mid; // mid + rand.nextInt(2) to always choose right mid
        
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, low, mid - 1);
        root.right = buildBST(nums, mid + 1, high);
        
        return root;
    }
}

