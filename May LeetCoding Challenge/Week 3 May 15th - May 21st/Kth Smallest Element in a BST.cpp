/*
iven a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?
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
        vector<int> result;
        void helper(TreeNode *root) { 
            
            if(root == NULL) return ;
            helper(root->left);
            result.push_back(root->val);
            helper(root->right);
            
        } 
public:
    int kthSmallest(TreeNode* root, int k) {
        // helper(root); // Time : O(N), Space : O(N) 
        // return result[k - 1];
        
        stack<TreeNode*> st; // Time : O(K), Space : O(K)
        
        while(true) {
            while(root != NULL) { 
                st.push(root);
                root = root->left;
            }
            
            TreeNode* node = st.top();
            st.pop();
            
            if(--k == 0) {
                return node->val;
            }
            
            root = node->right;
        }
    }
};