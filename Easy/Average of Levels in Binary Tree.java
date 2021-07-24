/*
Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. 
Answers within 10-5 of the actual answer will be accepted.
 

Example 1:
    3
    /  \
    9   20
        /  \
        15   7      
Input: root = [3,9,20,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].

Example 2:
    3
    /  \
    9   20
  /  \
 15   7  

Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averageOfLevelsList = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return averageOfLevelsList;
        }
        
        Queue <TreeNode> Q = new LinkedList<>();
        
        Q.add(root);
        
        while (!Q.isEmpty()) {
            int size = Q.size();
            Double averageOfLevel = Double.valueOf(0);
            for (int itr = 0; itr < size; ++itr) {
                root = Q.poll();
                averageOfLevel += Double.valueOf(root.val); 
                
                if (Objects.nonNull(root.left)) {
                    Q.add(root.left);
                }
                
                if (Objects.nonNull(root.right)) {
                    Q.add(root.right);
                }
            }
            averageOfLevelsList.add(averageOfLevel/size);
        }
        
        return averageOfLevelsList;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List < Double > averageOfLevels(TreeNode root) {
        List < Integer > count = new ArrayList < > ();
        List < Double > res = new ArrayList < > ();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }
    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }
}
