/*
Given a binary tree, write a function to get the maximum width of the given tree. 
The width of a tree is the maximum width among all levels. 
The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes 
(the leftmost and right most non-null nodes in the level, where the null 
nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
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
    
    class Node {
        TreeNode node;
        int index;
        
        Node(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        
        if(root == null) {
            return 0;
        }
        
        int maxWidth = Integer.MIN_VALUE;
        
        Deque<Node> Q = new LinkedList();
        
        Q.offer(new Node(root, 1));
        
        while(!Q.isEmpty()) {
            
            maxWidth = Math.max(maxWidth,  Q.peekLast().index - Q.peekFirst().index + 1);
            
            int len = Q.size();
            
            while(len-- > 0) {
                Node temp = Q.pollFirst();
                
                if(temp.node.left != null) {
                    Q.offer(new Node(temp.node.left, 2*temp.index));
                }
                 if(temp.node.right != null) {
                    Q.offer(new Node(temp.node.right, 2*temp.index + 1));
                }
                
            }
        }
        
        return maxWidth;
    }
}
