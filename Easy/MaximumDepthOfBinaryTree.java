import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
    3
   / \
  9  20
    /  \
   15   7

Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:

Input: root = [1,null,2]
Output: 2
Example 3:

Input: root = []
Output: 0
Example 4:

Input: root = [0]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-100 <= Node.val <= 100
*/


class MaximumDepthOfBinaryTree {

  public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        if(root.left == null && root.right == null) {
            return 1;
        }
        
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        
        return Math.max(l, r) + 1;
    }

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

    public int maxDepth1(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        
        Queue<TreeNode> Q = new LinkedList<>();
        
        Q.add(root);
        int depth = 0;
        
        while (!Q.isEmpty()) {
            int size = Q.size();
            
            while (size-- > 0) {
                root = Q.poll();
                
                if (Objects.nonNull(root.left)) {
                    Q.add(root.left);
                }
                
                 if (Objects.nonNull(root.right)) {
                    Q.add(root.right);
                }
            }
            
            ++depth;
        }
        
        return depth;
    }
}