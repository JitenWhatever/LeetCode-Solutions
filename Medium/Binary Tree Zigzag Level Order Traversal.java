/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

Example 1:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagLevelOrder = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return zigzagLevelOrder;
        }
        
        Queue<TreeNode> Q = new LinkedList<>();
        
        Q.add(root);
        int depth = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            
            List<Integer> levelOrder = new ArrayList<>();
            
            while (size-- > 0) {
                root = Q.poll();
                 if (depth % 2 == 0) {
                    levelOrder.add(root.val); 
                 } else {
                     levelOrder.add(0, root.val);
                 }
                
                if (Objects.nonNull(root.left)) {
                    Q.add(root.left);
                }
                
                if (Objects.nonNull(root.right)) {
                    Q.add(root.right);
                }
            }
            ++depth;
            zigzagLevelOrder.add(levelOrder);
        }
        
        return zigzagLevelOrder;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
    if (level >= results.size()) {
      LinkedList<Integer> newLevel = new LinkedList<Integer>();
      newLevel.add(node.val);
      results.add(newLevel);
    } else {
      if (level % 2 == 0)
        results.get(level).add(node.val);
      else
        results.get(level).add(0, node.val);
    }

    if (node.left != null) DFS(node.left, level + 1, results);
    if (node.right != null) DFS(node.right, level + 1, results);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<List<Integer>>();
    }
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    DFS(root, 0, results);
    return results;
  }
}