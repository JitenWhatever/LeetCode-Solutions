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
    
    class TrieNode {
        TrieNode left;
        TrieNode right;
        
        TrieNode(){
            this.left = null;
            this.right = null;
        }
    }
    
    private TrieNode root = new TrieNode();
    
    private void insert(int num, TrieNode root) {
        TrieNode curr = root;
        
        for(int bit = 31; bit >= 0; --bit) {
            int mask = (num>>bit)&1;
            
            if(mask == 0) {
                if(curr.left == null) {
                    curr.left = new TrieNode();
                }
                
                curr = curr.left;
            }
            else {
                if(curr.right == null) {
                    curr.right = new TrieNode();
                }
                
                curr = curr.right;
            }
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        for(int num : nums) {
            insert(num, root);
        }
        
        TrieNode curr =  root;
        int maxXor = Integer.MIN_VALUE;
        int currXor = 0;

        for(int num : nums) {            
            for(int bit = 31; bit >= 0; --bit) {
                int mask = (num>>bit)&1;
                
                if(mask == 0) {
                    if(curr.right != null) {
                        currXor += (1<<bit);
                        curr = curr.right;
                    }   
                    else {
                        curr = curr.left;
                    }
                }
                else {
                     if(curr.left != null) {
                        currXor += (1<<bit);
                         curr = curr.left;
                    }   
                    else {
                        curr = curr.right;
                    }
                }
            }
            
            maxXor = Math.max(maxXor, currXor);
            currXor  = 0;
            curr = root;
        }
        
        return maxXor;
    }
}