/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.

A leaf is a node with no children.

Example 1:
https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]

Example 2:
https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg

Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return paths;
        }
        
        StringBuilder currentPath = new StringBuilder();
        findPaths(root, paths, currentPath);
        
        return paths;
    }
    
    private void findPaths(TreeNode root, List<String> paths, StringBuilder currentPath) {
        if (Objects.isNull(root)) {
            return ;
        }
        currentPath.append(root.val);
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            paths.add(currentPath.toString());
            return;
        }
        
        currentPath.append("->");
        findPaths(root.left, paths, new StringBuilder(currentPath.toString()));
        findPaths(root.right, paths, new StringBuilder(currentPath.toString()));
        
    }
}

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return paths;
        }
        
        StringBuilder currentPath = new StringBuilder();
        findPaths(root, paths, currentPath);
        
        return paths;
    }
    
    private void findPaths(TreeNode root, List<String> paths, StringBuilder currentPath) {
        if (Objects.isNull(root)) {
            return ;
        }
        currentPath.append(root.val);
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            paths.add(currentPath.toString());
            return;
        }
        
        currentPath.append("->");
        int len = currentPath.length();
        findPaths(root.left, paths, currentPath);
        currentPath.setLength(len);
        findPaths(root.right, paths, currentPath);
        currentPath.setLength(len);
    }

    // BFS
    public List<String> binaryTreePathsBFS(TreeNode root) {
        List<String> list = new ArrayList<String>();
        Queue<TreeNode> qNode = new LinkedList<TreeNode>();
        Queue<String> qStr = new LinkedList<String>();
        
        if (root==null) return list;

        qNode.add(root);
        qStr.add("");
        while(!qNode.isEmpty()) {
            TreeNode curNode=qNode.poll();
            String curStr=qStr.poll();
            
            if (curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
            if (curNode.left!=null) {
                qNode.add(curNode.left);
                qStr.add(curStr+curNode.val+"->");
            }
            if (curNode.right!=null) {
                qNode.add(curNode.right);
                qStr.add(curStr+curNode.val+"->");
            }
        }
        return list;
    }

    // DFS
    public List<String> binaryTreePathsDFS(TreeNode root) {
        List<String> list=new ArrayList<String>();
        Stack<TreeNode> sNode=new Stack<TreeNode>();
        Stack<String> sStr=new Stack<String>();
        
        if(root==null) return list;
        sNode.push(root);
        sStr.push("");
        while(!sNode.isEmpty()) {
            TreeNode curNode=sNode.pop();
            String curStr=sStr.pop();
            
            if(curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
            if(curNode.left!=null) {
                sNode.push(curNode.left);
                sStr.push(curStr+curNode.val+"->");
            }
            if(curNode.right!=null) {
                sNode.push(curNode.right);
                sStr.push(curStr+curNode.val+"->");
            }
        }
        return list;
    }
}

