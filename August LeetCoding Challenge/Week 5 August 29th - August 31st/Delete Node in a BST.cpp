/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.

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
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* deleteNode(TreeNode* root, int key) {
        inOrder(root, key);
        
        return buildBST(0, v.size() - 1);
    }
    
    vector<int> v;
    
    void inOrder(TreeNode *root, int key) {
        if(root == NULL) {
            return ;
        }
        
        inOrder(root->left, key);
        if(root->val != key) {
            v.push_back(root->val);
        }
        inOrder(root->right, key);
    }
    
    TreeNode* buildBST(int low, int high) {
        if(low > high) {
            return NULL;
        }
        
        int mid = low + (high - low)/2;
        
        TreeNode* root = new TreeNode(v[mid]);
        
        root->left = buildBST(low, mid - 1);
        root->right = buildBST(mid + 1, high);
        
        return root;
    }
};