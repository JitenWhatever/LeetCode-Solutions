/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
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
    int sumOfLeftLeaves(TreeNode* root) {
        // if(root ==  NULL || root->left == NULL && root->right == NULL) {
        //     return 0;
        // }
        // dfs(root);
        
        return dfs(root);
    }
    
    int dfs(TreeNode* root) {
        
        int sum = 0;
        if(root == NULL) {
            return 0;
        }
        if(root->left != NULL && root->left->left == NULL && root->left->right == NULL) {
            sum += root->left->val;
        }
        
        sum +=  dfs(root->left);
        
        sum += dfs(root->right);
        
        return sum;
   }
};