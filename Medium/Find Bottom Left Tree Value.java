/*
Given the root of a binary tree, return the leftmost value in the last row of the tree.

Example 1:
      2
    /  \
    1   3

Input: root = [2,1,3]
Output: 1

Example 2:
        2
      /   \
     2     3
    /     / \
   4     5   6
        /
       7
       
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-2^31 <= Node.val <= 2^31 - 1
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
    public int findBottomLeftValue(TreeNode root) {
        int[] result = new int[]{0, -1};
         dfs(root, result, 0);
        
        return result[0];
    }
    
    private void dfs(TreeNode node, int[] result, int depth) {
        if (node == null) {
            return ;
        }
        
        if (result[1] < depth) {
            result[0] = node.val;
            result[1] = depth;
        }
        
        dfs(node.left, result, depth + 1);
        dfs(node.right, result, depth + 1);
    }
}

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q=new LinkedList();
        q.offer(root);
        ArrayList<Integer> arr=new ArrayList<>();
     
        while(!q.isEmpty()){
                
            int size=q.size();
            arr.clear();
            
            for(int i=0;i<size;i++){
                TreeNode temp=q.poll();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                arr.add(temp.val);
            }          
            
        }
        return arr.get(0);
    }
}