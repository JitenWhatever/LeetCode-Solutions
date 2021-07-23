/*
Given an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

 

Follow up:

Recursive solution is trivial, could you do it iteratively?


Example 1:
https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png


Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]

Example 2:
https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png


Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 

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
    public List<Integer> postorder(Node root) {
        List<Integer> postOrder = new ArrayList<>();
        
        if (Objects.isNull(root)) {
            return postOrder;
        }
        
        // postOrderRecursive(root, postOrder); 
        Stack<Node> stck = new Stack<>();
        
        stck.push(root);
        
        while(!stck.isEmpty()) {
            root = stck.peek();
            if (Objects.nonNull(root.children)) {
                for (int index = root.children.size() - 1; index >= 0; --index) {
                    stck.push(root.children.get(index));
                }
                root.children = null;
            } else {
                postOrder.add(stck.pop().val);
            }
        }
        
        return postOrder;
    }
    
    private void postOrderRecursive(Node root, List<Integer> postOrder) {
        if (Objects.isNull(root)) {
            return;
        }
        
        if (Objects.nonNull(root.children)) {
            root.children.forEach(node -> {
                postOrderRecursive(node, postOrder);
            });
        }
    
        postOrder.add(root.val);
    }
}

class Solution {
  public List<Integer> postorder(Node root) {
    LinkedList<Node> stack = new LinkedList<>();
    LinkedList<Integer> output = new LinkedList<>();
    if (root == null) {
      return output;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      Node node = stack.pollLast();
      output.addFirst(node.val);
      for (Node item : node.children) {
        if (item != null) {
          stack.add(item);    
        } 
      }
    }
    return output;
  }
}