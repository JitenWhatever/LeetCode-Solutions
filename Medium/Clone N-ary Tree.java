/*
Given a root of an N-ary tree, return a deep copy (clone) of the tree.

Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.

1
2
3
4
class Node {
    public int val;
    public List<Node> children;
}
Nary-Tree input serialization is represented in their level order traversal, 
each group of children is separated by the null value (See examples).

Follow up: Can your solution work for the graph problem?
https://helloacm.com/wp-content/uploads/2020/06/n-ary-tree.jpg

n-ary-tree Deep Clone N-ary Tree using Hash Map + Recursive Depth First Search Algorithm algorithms c / c++ DFS recursive 
n-ary-tree


Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]
Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]

Constraints:
The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [0, 10^4].

Hints:
Traverse the tree, keep a hashtable with you and create a clone node for each node in the tree.
Start traversing the original tree again and connect each child pointer in the cloned tree the same way as the original tree with the help of the hashtable.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node*> children;
 
    public Node() {}
 
    public Node(int _val) {
        val = _val;
    }
 
    public Node(int _val, List<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/
 
class Solution {
    public Node cloneTree(Node root) {
        if (root == null) return null;
        Node newRoot = new Node(root.val);
        for (Node n : root.children) {
            newRoot.children.add(cloneTree(n));
        }
        return newRoot;
    }
}