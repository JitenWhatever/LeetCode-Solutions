/*
Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. 
The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. 
Otherwise, you should return the original given node.


 
The following example may help you understand the problem better:

Leetcode: Insert into a Cyclic Sorted List

In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.

Leetcode: Insert into a Cyclic Sorted List

The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
*/

/*
// class definition for a node

class Node {
    public int val;
    public Node* next;

    public Node(){}

    public Node(int _val) {
        val = _val;
    }

    
    public Node(int _val, Node _next) {
        next = _next;
        val = _val;
    }

};
*/


class Solution {
    public:
        Node* insert(Node* head, int interval) {
            
            Node* node = new Node(interval);

            if(head == NULL) {
                node->next = node;
                return node;
            }

            if(head->next == NULL) {
                head->next = node;
                node->next = head;

                return head;
            }

            Node* curr = head;

            while(true) {
                if(curr->val > curr->next->val) {
                    if(interval >= curr->val || interval <= curr->next->val) {
                        break;
                    }

                    if(interval > curr->val && interval < curr->next->val) {
                        curr = curr->next;
                        continue;
                    }

                    break;
                }

                
                if(interval >= curr->val && interval <= curr->next->val) {
                    break;
                }

                curr = curr->next;
                if(curr == head) {
                    break;
                }
            }

            node->next = curr->next;
            curr->next = node;

            return head;
        }
};