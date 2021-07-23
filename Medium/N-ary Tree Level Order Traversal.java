/*
Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

 

Example 1:
https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png

Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]

Example 2:
https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 

Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return levelOrder;
        }
        
        Queue<Node> Q = new LinkedList<>();
        Q.add(root);
        
        while (!Q.isEmpty()) {
            int size = Q.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                root = Q.poll();
                level.add(root.val);
                
                if (Objects.nonNull(root.children)) {
                    root.children.forEach(node -> Q.add(node));
                }
            }
            
            levelOrder.add(level);
        }
        
        return levelOrder;
    }
}