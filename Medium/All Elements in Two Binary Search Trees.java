/*
Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.

 

Example 1:
https://assets.leetcode.com/uploads/2019/12/18/q2-e1.png

Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]

Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]

Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]

Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]

Example 5:
https://assets.leetcode.com/uploads/2019/12/18/q2-e5-.png

Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
 

Constraints:

Each tree has at most 5000 nodes.
Each node's value is between [-10^5, 10^5].
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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        hash = new TreeMap<>();
        List<Integer> result = new ArrayList<>();
        
        dfs(root1);
        dfs(root2);
        
        for(int key : hash.keySet()) {
            for(int rep = 0; rep < hash.get(key); ++rep) {
                result.add(key);
            }
        }
        
        
        return result;
        
    }
    
    private TreeMap<Integer, Integer> hash;
    
    private void dfs(TreeNode root) {
        if(root == null) {
            return ;
        }
        
        hash.put(root.val, hash.getOrDefault(root.val, 0) + 1);
        dfs(root.left);
        dfs(root.right);
    }
}