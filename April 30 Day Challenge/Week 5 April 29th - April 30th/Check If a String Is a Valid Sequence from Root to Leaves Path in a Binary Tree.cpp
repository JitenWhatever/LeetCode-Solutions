/*
Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree. 

We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.

Example 1:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation: 
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure). 
Other valid sequences are: 
0 -> 1 -> 1 -> 0 
0 -> 0 -> 0

Example 2:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
Output: false 
Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.

Example 3:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.

Constraints:

    1 <= arr.length <= 5000
    0 <= arr[i] <= 9
    Each node's value is between [0 - 9].
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
    /*vector<bool> res;
    string str = "";
    string s = "";
    bool equal(string s1, string s2){
        int n = s1.length();
        int m = s2.length();
        if(n != m) return false;
        for(int i=0; i<n; i++){
            if(s1[i] != s2[i]){
                return false;
            }
        }
        
        return true;
    }
    void dfs(TreeNode *root, string str){
       if(!root->left && !root->right){
          if(equal(str, s)){
              res.push_back(true);
          }
           else{
               res.push_back(false);
           }
           return ;
       }
        
        if(root->left) dfs(root->left, str + to_string(root->left->val));
        if(root->right) dfs(root->right, str + to_string(root->right->val));    
        
    }
    */
    bool solve(TreeNode *root, vector<int> &arr, int index){
        if(!root) return false;
        if(root->val != arr[index]) return false;
        
        if(index + 1 == arr.size()){
            return (root->left == NULL) && (root->right == NULL);
        }
        index++;
        return solve(root->left, arr, index) || solve(root->right, arr, index);
    }
public:
    bool isValidSequence(TreeNode* root, vector<int>& arr) {
        
       return solve(root, arr, 0);
        /*if(root == NULL  && arr.empty()) return true;
        for(int x : arr){
            s += to_string(x);
        }
        
        dfs(root, str + to_string(root->val));

        bool ans = false;
        for(bool x : res){
            ans |= x;
        }
        return ans;;
       */
    }
    
    
};