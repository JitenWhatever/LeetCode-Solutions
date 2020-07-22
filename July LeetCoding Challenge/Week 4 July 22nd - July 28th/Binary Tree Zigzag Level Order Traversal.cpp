/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
          vector<vector<int>> result;
        if(!root) {
            return result;
        }
        
        int depth = 0;
        queue<TreeNode*> Q ;
        Q.push(root);
        
      
        
        while(!Q.empty()) {
            
            int len = Q.size();
            vector<int> dummy;
            
            while(len--) {
                root = Q.front();
                Q.pop();
                dummy.push_back(root->val);
                
                if(root->left) Q.push(root->left);
                if(root->right) Q.push(root->right);
            }
            if(depth%2) {
                reverse(dummy.begin(), dummy.end());
                 result.push_back(dummy);
            }
            else {
                 result.push_back(dummy);
            }
           
            depth++;
        }
        
        return result;
    }
};