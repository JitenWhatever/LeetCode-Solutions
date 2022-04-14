import java.util.Objects;

/*
Given the root node of a binary search tree (BST) and a value. 
You need to find the node in the BST that the node's value equals the given value. 
Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

Example 1:
        4
       / \
      2   7
     / \
    1   3

Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]

You should return this subtree:

      2     
     / \   
    1   3


Example 2:

        4
       / \
      2   7
     / \
    1   3
Input: root = [4,2,7,1,3], val = 5
Output: []
 

Constraints:

The number of nodes in the tree is in the range [1, 5000].
1 <= Node.val <= 10^7
root is a binary search tree.
1 <= val <= 10^7
*/

class SearchInABinarySearchTree {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        
         if (Objects.isNull(root) || root.val == val) {
            return root;
        }
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
   
   public TreeNode searchBST1(TreeNode root, int val) {
        while (Objects.nonNull(root) && val != root.val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}