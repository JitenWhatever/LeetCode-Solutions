/*
Return the root node of a binary search tree that matches the given preorder traversal.
(Recall that a binary search tree is a binary tree where for every node, 
any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  
Also recall that a preorder traversal displays the value of the node first, 
then traverses node.left, then traverses node.right.)
It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.

Example 1:

            (8)
    (5)             (10)
(1)     (7)                 (12)

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Constraints:
    1 <= preorder.length <= 100
    1 <= preorder[i] <= 10^8
    The values of preorder are distinct.
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
    TreeNode* helper(vector<int> &preorder, int& currentIndex, int limit) {
        if(currentIndex == preorder.size() || preorder[currentIndex] > limit) {
            return NULL;
        }
        
        TreeNode *root = new TreeNode(preorder[currentIndex]);
        ++currentIndex;
        root->left = helper(preorder, currentIndex, root->val);
        root->right = helper(preorder, currentIndex, limit);
        
        return root;
    }
public:
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        
        if(preorder.size() == 0) {
            return NULL;
        }
        int currentIndex = 0;
        TreeNode *root = helper(preorder, currentIndex, INT_MAX);
        
        return root;
    }
};