/*
Invert a binary tree.

Example:

Input:
     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1

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
    TreeNode* invertTree(TreeNode* root) {
        if(root == NULL) {
            return root;
        }
        
        queue<TreeNode*> Q;
        Q.push(root);
        
        while(!Q.empty()) {
            TreeNode* node = Q.front();
            Q.pop();
            TreeNode* dummyNode = node->left;
            node->left = node->right;
            node->right = dummyNode;
            
            if(node->left != NULL) Q.push(node->left);
            if(node->right != NULL) Q.push(node->right);
        }
        
        return root;
    }
};