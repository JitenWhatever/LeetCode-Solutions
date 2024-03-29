import java.util.LinkedList;
import java.util.Queue;

/*
Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or 
the target node and the answer must be a reference to a node in the cloned tree.

Follow up: Solve the problem if repeated values on the tree are allowed.

 

Example 1:
https://assets.leetcode.com/uploads/2020/02/21/e1.png

Input: tree = [7,4,3,null,null,6,19], target = 3
Output: 3
Explanation: In all examples the original and cloned trees are shown. 
The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
Example 2:
https://assets.leetcode.com/uploads/2020/02/21/e2.png

Input: tree = [7], target =  7
Output: 7
Example 3:
https://assets.leetcode.com/uploads/2020/02/21/e3.png

Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
Output: 4
Example 4:
https://assets.leetcode.com/uploads/2020/02/21/e4.png

Input: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
Output: 5
Example 5:
https://assets.leetcode.com/uploads/2020/02/21/e5.png

Input: tree = [1,2,null,3], target = 2
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
The values of the nodes of the tree are unique.
target node is a node from the original tree and is not null.

*/



public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null || cloned == null) {
            return null;
        }
        
        Queue<TreeNode> O = new LinkedList<>();
        Queue<TreeNode> C = new LinkedList<>();
        
        O.add(original);
        C.add(cloned);
        
        while(!O.isEmpty()) {
            TreeNode or =  O.poll();
            TreeNode cl = C.poll();
            
            if(or.val == target.val) {
                return cl;
            }
            
            if(or.left != null && cl.left != null) {
                O.add(or.left);
                C.add(cl.left);
            }
            
            if(or.right != null && cl.right != null) {
                 O.add(or.right);
                C.add(cl.right);
            }
        }
        
        return null;
    }
}