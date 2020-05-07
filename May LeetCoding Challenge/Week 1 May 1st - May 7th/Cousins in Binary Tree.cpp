/*
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.

Example 1:
Input: root = [1,2,3,4], x = 4, y = 3  
        1
    2       3
4
Output: false

Example 2:
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
            1
    2               3
        4                   5
Output: true

Example 3:
Input: root = [1,2,3,null,4], x = 2, y = 3
        1
2               3
    4
Output: false

Note:
    The number of nodes in the tree will be between 2 and 100.
    Each node has a unique integer value from 1 to 100.
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
    pair<TreeNode*, int> solve(TreeNode *root, int num){
        queue<TreeNode*> Q;
        int depth = 0;
        if(root) Q.push(root);
        TreeNode *dummy;
        bool found = false;
        pair<TreeNode*, int> p;
        while(!Q.empty()){
             queue<TreeNode*> P;
             ++depth;
            while(!Q.empty()){
                dummy = Q.front();
                Q.pop();
               
                if(dummy->left){
                    if(dummy->left->val == num){
                        p = make_pair(dummy, depth);
                        found = true;
                        break;
                    }
                    P.push(dummy->left);
                }
                
                 if(dummy->right){
                    if(dummy->right->val == num){
                         p = make_pair(dummy, depth);
                        found = true;
                        break;
                    }
                    P.push(dummy->right);
                }
            }
            
            if(found) break;
            Q = P;
        }
        
        return p;
    }
public:
    bool isCousins(TreeNode* root, int x, int y) {
        
        pair<TreeNode*, int> xpair = solve(root, x);
        pair<TreeNode*, int> ypair = solve(root, y);
        
        if(xpair.second == ypair.second){
            return !(xpair.first->val == ypair.first->val);
        }
        return false;
    }
};