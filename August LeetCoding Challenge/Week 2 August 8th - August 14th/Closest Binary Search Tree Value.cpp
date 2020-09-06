/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int closestValue(TreeNode* root, double target) {

        int a = root->val;
        TreeNode* next = (target < a) ? root->left:root->right;
        
        if (!next) return a;

        int b = closestValue(next, target);
        
        return abs(a-target) < abs(b-target) ? a:b;
    }
};