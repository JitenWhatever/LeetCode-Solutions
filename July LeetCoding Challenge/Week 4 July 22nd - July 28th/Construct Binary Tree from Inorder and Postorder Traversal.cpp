/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
private:
    TreeNode* helper(vector<int> inorder, vector<int> postorder, int inStart, int inEnd, int postIndex) {
        
        if(postIndex < 0 || inStart > inEnd) {
            return NULL;
        }
        
        int inIndex = 0;
        for(int index = inStart; index <= inEnd; ++index) {
            if(postorder[postIndex] == inorder[index]) {
                inIndex = index;
                break;
            }
        }
        TreeNode* root = new TreeNode(postorder[postIndex]);
        root->left = helper(inorder, postorder, inStart, inIndex - 1, postIndex - 1 - inEnd + inIndex);
        root->right = helper(inorder, postorder, inIndex + 1, inEnd, postIndex - 1);
        return root;
    }
public:
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        return helper(inorder, postorder, 0, inorder.size() - 1, postorder.size() - 1);
    }
};
