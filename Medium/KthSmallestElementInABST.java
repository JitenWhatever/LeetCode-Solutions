import java.util.Stack;

/*
iven a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4
 

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) 
and you need to find the kth smallest frequently, how would you optimize?
*/
 
class KthSmallestElementInABST {
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
    public int kthSmallest(TreeNode root, int k) {
     
        Stack<TreeNode> st = new Stack<TreeNode>();
        
        while(true) {
            while(root != null) {
                st.add(root);
                root = root.left;
            }
            
            root = st.pop();
            
            if(--k == 0) {
                return root.val;
            }
            
            root = root.right;
        }
    }
}