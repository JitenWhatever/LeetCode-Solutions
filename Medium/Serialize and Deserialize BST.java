/*
Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

 

Example 1:

Input: root = [2,1,3]
Output: [2,1,3]
Example 2:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The input tree is guaranteed to be a binary search tree.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "$";
        }
        
        String serial = "";
        serial += root.val;
        serial +=  "," + serialize(root.left);
         serial += "," + serialize(root.right);
        
        return serial;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        int[] index = new int[1];
         // System.out.println(data + data.split(",").length);
        return solve(data.split(","), index);
    }
    
    private TreeNode solve(String[] nums, int[] index) {
        int len  = index[0]++;
        if(nums.length == len) {
            return null;
        }
        if(nums[len].equals("$")) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(nums[len]));
        root.left = solve(nums, index);
        root.right = solve(nums, index);
            
            
        return  root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;