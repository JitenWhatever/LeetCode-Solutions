/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
*/

class Solution {

private:
     struct TrieNode {
        TrieNode* left;
        TrieNode* right;
    };
    
    TrieNode* root = new TrieNode();
    
    void insert(int num, TrieNode* root) {
        TrieNode* curr = root;
        for(int bit = 31; bit>= 0; --bit) {
            int mask = (num>>bit)&1;
            if(mask == 0) {
                if(!curr->left) {
                    curr->left = new TrieNode();
                }
                
                curr = curr->left;
            }
            else {
                  if(!curr->right) {
                    curr->right = new TrieNode();
                }
                
                curr = curr->right;
            }
        }
    }
    
public:
    int findMaximumXOR(vector<int>& nums) {
        
        if(nums.size() == 0) {
            return 0;
        }
        
        for(int num : nums) {
            insert(num, root);
        }
        
        int maxXor = INT_MIN;
        int currXor = 0;
        int currNum = 0;
        TrieNode* curr = root;
        
        for(int num : nums) {
            currXor = 0;
            currNum = num;
            
            curr = root;
            
            for(int bit = 31; bit >= 0; --bit) {
                int mask = (currNum>>bit)&1;
                
                if(mask == 0) {
                    if(curr->right) {
                        currXor += (1<<bit);
                        curr = curr->right;
                    }
                    else {
                        curr = curr->left;
                    }
                }
                else {
                    if(curr->left) {
                        currXor += (1<<bit);
                        curr = curr->left;
                    }
                    else {
                        curr = curr->right;
                    }
                }
            }
            
            maxXor = max(maxXor, currXor);
        }
        
        return maxXor;
    }
};