/*
Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, 
return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Note that in a binary tree, the root node is at the depth 0, 
and children of each depth k node are at the depth k + 1.

Example 1:
 
        1
    2       3
4
Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:

            1
    2               3
        4                   5
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

Example 3:

Input: root = [1,2,3,null,4], x = 2, y = 3
        1
2               3
    4
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

Constraints:

The number of nodes in the tree is in the range [2, 100].
1 <= Node.val <= 100
Each node has a unique value.
x != y
x and y are exist in the tree.Output: false
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
    public boolean isCousins(TreeNode root, int x, int y) {
        
        dfs(root, 0, x, y);
        
        return this.isCousin;
    }
    
    private int limit = -1;
    private boolean isCousin = false;
    
    private boolean dfs(TreeNode node, int currDepth, int x, int y) {
        if (node == null) {
            return false;
        }
        
        if (this.limit != -1 && currDepth > this.limit) {
            return false; // x and y are not at same level
        }
        
        if (node.val == x || node.val == y) {
            if (this.limit == -1) {
                this.limit = currDepth;
            }
            
            return currDepth == this.limit;
        }
        
        
        boolean left = dfs(node.left, currDepth + 1, x, y);
        boolean right = dfs(node.right, currDepth + 1, x, y);
        
        if (left && right && this.limit != currDepth + 1) {
            this.isCousin = true;
        }
        
        return left || right;
    }
}

class Solution {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xDepth = -1, yDepth = -1;
    
    private void findCousins(TreeNode root, int depth, int x, int y, TreeNode parent) {
        if(root == null) return;
        
        if(root.val == x) {
            xParent = parent;
            xDepth = depth;
        } else if(root.val == y) {
            yParent = parent;
            yDepth = depth;
        }
        
        findCousins(root.left, depth+1, x, y, root);
        findCousins(root.right, depth+1, x, y, root);
    }
    
    public boolean isCousins(TreeNode root, int x, int y) {
        findCousins(root, 0, x, y, null);
        
        return xDepth == yDepth && xParent != yParent;
    }
}

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
    public boolean isCousins(TreeNode root, int x, int y) {
        
        return bfs(root, x, y);
    }
    
    private boolean bfs(TreeNode node, int x, int y) {
        Queue<TreeNode> Q = new LinkedList<>();
        
        Q.add(node);
        
        boolean xFound = false, yFound = false;
        Pair<Integer, TreeNode> xp = null, yp = null;
        int depth = 0;
        
        while (!Q.isEmpty()) {
            int len = Q.size();
            ++depth;
            while (len-- > 0) {
                node = Q.poll();
                
                if (node.left != null) {
                    if (node.left.val == x) {
                        xFound = true;
                        xp = new Pair<>(depth, node);
                    } else if (node.left.val == y) {
                        yFound = true;
                        yp = new Pair<>(depth, node);
                    }
                    
                    Q.add(node.left);
                }
                
                if (node.right != null) {
                     if (node.right.val == x) {
                        xFound = true;
                        xp = new Pair<>(depth, node);
                    } else if (node.right.val == y) {
                        yFound = true;
                        yp = new Pair<>(depth, node);
                    }
                    
                    Q.add(node.right);
                } 
            }
            
            if (xFound && yFound) {
                break;
            }
        }
        
        return xp != null && yp != null && xp.getKey() == yp.getKey() && xp.getValue() != yp.getValue();
    }
}