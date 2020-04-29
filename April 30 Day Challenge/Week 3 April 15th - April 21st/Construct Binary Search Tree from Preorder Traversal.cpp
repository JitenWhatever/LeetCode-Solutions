/*
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    
    TreeNode * solve(int preLeft, int inLeft, int inRight, vector<int> preorder, vector<int> inorder){
        if(preLeft > preorder.size() -1 || inLeft > inRight) {
            return NULL;
        }
        TreeNode *root = new TreeNode(preorder[preLeft]);
        int inIndex = 0;
        for(int i=inLeft; i<=inRight; i++){
            if(root->val == inorder[i]){
                inIndex = i;
            }
        }
        
        root->left = solve(preLeft + 1, inLeft, inIndex - 1, preorder, inorder);
        root->right = solve(preLeft + inIndex - inLeft + 1, inIndex + 1, inRight, preorder, inorder);
        
        return root;
    }

   

    TreeNode* bstFromPreorder(vector<int>& preorder) {
       vector<int> inorder;
        for(int x : preorder){
            inorder.push_back(x);
        }
        sort(inorder.begin(), inorder.end());
        int n = preorder.size();
        
        return solve(0, 0, n-1, preorder, inorder);
        
    }
};

===========================================================================
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
    TreeNode *solve(vector<int> &preorder, int &currentIndex, int limit){
        if(currentIndex == preorder.size() || preorder[currentIndex] > limit){
            return NULL;
        }
        
        int val = preorder[currentIndex];
        TreeNode *root = new TreeNode(val);
        currentIndex++;
        root->left = solve(preorder, currentIndex, val);
        root->right = solve(preorder, currentIndex, limit);
        
        
        return root;
        
    }
    
public:
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        int currentIndex = 0;
        
        return solve(preorder, currentIndex, INT_MAX);
    }
};