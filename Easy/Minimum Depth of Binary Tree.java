/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

Input: root = [3,9,20,null,null,15,7]
Output: 2
Example 2:

Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
 

Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
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

 // recursion
class Solution {
    public int minDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);      
        
        return (left == 0 || right == 0) ? left + right + 1 : 1 + Math.min(left, right);
    }
}

 // BFS
class Solution {
    public int minDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        
        Queue<Pair<TreeNode, Integer>> Q = new LinkedList<>();
        
        Q.add(new Pair(root, 1));
        int mindepth = 0;
        
        while (!Q.isEmpty()) {
            Pair<TreeNode, Integer> P = Q.poll();
            root = P.getKey();
            mindepth = P.getValue();
            
            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                break;
            }
            
            if (Objects.nonNull(root.left)) {
                Q.add(new Pair(root.left, mindepth + 1));
            }
            
            if (Objects.nonNull(root.right)) {
                Q.add(new Pair(root.right, mindepth + 1));
            }
        }
        
        return mindepth;
    }
}

// DFS
class Solution {
    public int minDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        
        Stack<Pair<TreeNode, Integer>> S = new Stack<>();
        
        S.push(new Pair(root, 1));
        int mindepth = Integer.MAX_VALUE;
        
        while (!S.isEmpty()) {
            Pair<TreeNode, Integer> P = S.pop();
            root = P.getKey();
            int depth = P.getValue();
            
            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                mindepth = Math.min(mindepth, depth);
            }
            
            if (Objects.nonNull(root.left)) {
                S.push(new Pair(root.left, depth + 1));
            }
            
            if (Objects.nonNull(root.right)) {
                S.push(new Pair(root.right, depth + 1));
            }
        }
        
        return mindepth;
    }
}