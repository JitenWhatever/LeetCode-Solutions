/*
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.

The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.

(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

Example 1:
https://assets.leetcode.com/uploads/2020/07/19/sample_2_1897.png

Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.

Example 2:
https://assets.leetcode.com/uploads/2020/07/19/sample_1_1897.png


Input: root = [1,null,2,null,3,4,null,5,null,6]
Output: 4

Example 3:
https://assets.leetcode.com/uploads/2020/07/19/sample_3_1897.png


Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 7
 

Constraints:

The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [1, 104].
*/

class Solution {
    protected int diameter = 0;

    /**
     * return the height of the node
     */
    protected int height(Node node) {
        if (node.children.size() == 0)
            return 0;

        // select the top two largest heights
        int maxHeight1 = 0, maxHeight2 = 0;
        for (Node child : node.children) {
            int parentHeight = height(child) + 1;
            if (parentHeight > maxHeight1) {
                maxHeight2 = maxHeight1;
                maxHeight1 = parentHeight;
            } else if (parentHeight > maxHeight2) {
                maxHeight2 = parentHeight;
            }
            // calculate the distance between the two farthest leaves nodes.
            int distance = maxHeight1 + maxHeight2;
            this.diameter = Math.max(this.diameter, distance);
        }

        return maxHeight1;
    }

    public int diameter(Node root) {
        this.diameter = 0;
        height(root);
        return diameter;
    }
}

class Solution {
    protected int diameter = 0;

    /**
     * return the maximum depth of leaves nodes descending from the given node
     */
    protected int maxDepth(Node node, int currDepth) {
        if (node.children.size() == 0)
            return currDepth;

        // select the top two largest depths
        int maxDepth1 = currDepth, maxDepth2 = 0;
        for (Node child : node.children) {
            int depth = maxDepth(child, currDepth + 1);
            if (depth > maxDepth1) {
                maxDepth2 = maxDepth1;
                maxDepth1 = depth;
            } else if (depth > maxDepth2) {
                maxDepth2 = depth;
            }
            // calculate the distance between the two farthest leaves nodes.
            int distance = maxDepth1 + maxDepth2 - 2 * currDepth;
            this.diameter = Math.max(this.diameter, distance);
        }

        return maxDepth1;
    }

    public int diameter(Node root) {
        this.diameter = 0;
        maxDepth(root, 0);
        return diameter;
    }
}