import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.


Example 1:
https://assets.leetcode.com/uploads/2019/02/15/117_sample.png

Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), 
your function should populate each next pointer to point to its next right node, just like in Figure B. 
The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:

Input: root = []
Output: []


Constraints:
    The number of nodes in the tree is in the range [0, 6000].
    -100 <= Node.val <= 100

Follow-up:
    You may only use constant extra space.
    The recursive approach is fine. 
    You may assume implicit stack space does not count as extra space for this problem.
*/

public class PopulatingNextRightPointersInEachNodeII {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        
        if (root == null) {
            return root;
        }
        
        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> Q = new LinkedList<Node>(); 
        Q.add(root);
        
        // Outer while loop which iterates over 
        // each level
        while (Q.size() > 0) {
            
            // Note the size of the queue
            int size = Q.size();
            
            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {
                
                // Pop a node from the front of the queue
                Node node = Q.poll();
                
                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only 
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }
                
                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        
        // Since the tree has now been modified, return the root node
        return root;
    }
    
    Node prev, leftmost;
    
    public void processChild(Node childNode) {
        
        if (childNode != null) {
            
            // If the "prev" pointer is alread set i.e. if we
            // already found atleast one node on the next level,
            // setup its next pointer
            if (this.prev != null) {
                this.prev.next = childNode;
                    
            } else {
                
                // Else it means this child node is the first node
                // we have encountered on the next level, so, we
                // set the leftmost pointer
                this.leftmost = childNode;
            }    
                
            this.prev = childNode; 
        }
    }
        
    public Node connect1(Node root) {
        
        if (root == null) {
            return root;
        }
        
        // The root node is the only node on the first level
        // and hence its the leftmost node for that level
        this.leftmost = root;
        
        // Variable to keep track of leading node on the "current" level
        Node curr = leftmost;
        
        // We have no idea about the structure of the tree,
        // so, we keep going until we do find the last level.
        // the nodes on the last level won't have any children
        while (this.leftmost != null) {
            
            // "prev" tracks the latest node on the "next" level
            // while "curr" tracks the latest node on the current
            // level.
            this.prev = null;
            curr = this.leftmost;
            
            // We reset this so that we can re-assign it to the leftmost
            // node of the next level. Also, if there isn't one, this
            // would help break us out of the outermost loop.
            this.leftmost = null;
            
            // Iterate on the nodes in the current level using
            // the next pointers already established.
            while (curr != null) {
                
                // Process both the children and update the prev
                // and leftmost pointers as necessary.
                this.processChild(curr.left);
                this.processChild(curr.right);
                
                // Move onto the next node.
                curr = curr.next;
            }
        }
                
        return root ;
    }
}