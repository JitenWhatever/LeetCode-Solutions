/*
Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  
For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

Example 1:
https://assets.leetcode.com/uploads/2019/04/04/sum-of-root-to-leaf-binary-numbers.png


Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 

Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.
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
    vector<string> paths;
public:
    int sumRootToLeaf(TreeNode* root) {
        
        dfs(root, "");
        
        int result = 0;
        for(string str : paths) {
            for(int index = 0; index < str.length(); ++index) {
                result += pow(2, index)*(str[index] - '0');
            }
        }
        return result;
    }
    
    
    void dfs(TreeNode* root, string str) {
        if(root == NULL) {
            return;
        }
        str = to_string(root->val) + str;
        if(root->left == NULL && root->right == NULL) {
            paths.push_back(str);
            return;
        }
        
        dfs(root->left, str);
        dfs(root->right, str);
    }
};