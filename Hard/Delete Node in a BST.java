/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
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
    public TreeNode deleteNode(TreeNode root, int key) {
     
        return deleteNodeByModification(root, key); // 0 ms
        
        return deleteNodeByTreeReConstruct(root, key); // 1 ms
    }
    
    private  TreeNode deleteNodeByTreeReConstruct(TreeNode root, int key) {
        
        list = new ArrayList<>();
        
        inOrder(root, key);
        
        root = buildTree(0, list.size() - 1);
        
        return root;
    }
    
    
    private List<Integer> list = null;
    
    private void inOrder(TreeNode root, int key) {
        if(root == null) {
            return;
        }
        
        inOrder(root.left, key);
        
        if(root.val != key) {
            list.add(root.val);
        }
        
        inOrder(root.right, key);
    }
    
    private TreeNode buildTree(int low, int high) {
        
        if(low > high) {
            return null;
        }
        
        int mid  = low + (high - low) / 2;
        
        TreeNode root = new TreeNode(list.get(mid));
        
        root.left = buildTree(low, mid - 1);
        root.right = buildTree(mid + 1, high);
        
        return root;
    }
    
    private TreeNode deleteNodeByModification(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        
        if(key < root.val) {
            root.left =  deleteNodeByModification(root.left, key);
        }
        else if(root.val < key) {
            root.right = deleteNodeByModification(root.right, key);
        }
        else {
            if(root.left == null && root.right == null) {
                return null;
            }
            else if(root.left != null) {
                
                TreeNode temp = root.left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                root.val = temp.val;
                root.left = deleteNodeByModification(root.left, root.val);
            }
            else {
                TreeNode temp = root.right;
                
                while(temp.left != null) {
                    temp = temp.left;
                }
                
                root.val = temp.val;
                root.right = deleteNodeByModification(root.right, root.val);
            }
            
        }
        
        
        return root;
    }
    
    
}