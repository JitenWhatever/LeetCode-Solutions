/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
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
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        vector<vector<int>> result;
        stack<vector<int>> temp;
        if(root == NULL) {
            return result;
        }
        
        queue<TreeNode*> Q;
        Q.push(root);
        while(!Q.empty()) {
            queue<TreeNode*> P;
            vector<int> dummy;
            
            while(!Q.empty()) {
                TreeNode* node = Q.front();
                Q.pop();
                dummy.push_back(node->val);
                if(node->left) P.push(node->left);
                if(node->right) P.push(node->right);
            }
            
            Q = P;
            temp.push(dummy);
        }
        
        while(!temp.empty()) {
            result.push_back(temp.top());
            temp.pop();
        }
        return result;
    }
};
