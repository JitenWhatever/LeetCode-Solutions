/*
You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and 
the kth node from the end (the list is 1-indexed).

Example 1:
https://assets.leetcode.com/uploads/2020/09/21/linked1.jpg

Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
Example 2:

Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]
 

Constraints:

The number of nodes in the list is n.
1 <= k <= n <= 10^5
0 <= Node.val <= 100
*/


public class SwappingNodesInALinkedList {
    

    public ListNode swapNodes(ListNode head, int k) {
        int listLength = 0;
        ListNode currentNode = head;
        // find the length of linked list
        while (currentNode != null) {
            listLength++;
            currentNode = currentNode.next;
        }
        // set the front node at kth node
        ListNode frontNode = head;
        for (int i = 1; i < k; i++) {
            frontNode = frontNode.next;
        }
        //set the end node at (listLength - k)th node
        ListNode endNode = head;
        for (int i = 1; i <= listLength - k; i++) {
            endNode = endNode.next;
        }
        // swap the values of front node and end node
        int temp = frontNode.val;
        frontNode.val = endNode.val;
        endNode.val = temp;
        return head;
    }

  
    public ListNode swapNodes1(ListNode head, int k) {
        int listLength = 0;
        ListNode frontNode = null;
        ListNode endNode = null;
        ListNode currentNode = head;
        // find the length of list and set the front node
        while (currentNode != null) {
            listLength++;
            if (listLength == k) {
                frontNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        // set the end node at (listLength - k)th node
        endNode = head;
        for (int i = 1; i <= listLength - k; i++) {
            endNode = endNode.next;
        }
        // swap front node and end node values
        int temp = frontNode.val;
        frontNode.val = endNode.val;
        endNode.val = temp;
        return head;
    }

   
    public ListNode swapNodes2(ListNode head, int k) {
        int listLength = 0;
        ListNode frontNode = null;
        ListNode endNode = null;
        ListNode currentNode = head;
        // set the front node and end node in single pass
        while (currentNode != null) {
            listLength++;
            if (endNode != null)
                endNode = endNode.next;
            // check if we have reached kth node
            if (listLength == k) {
                frontNode = currentNode;
                endNode = head;
            }
            currentNode = currentNode.next;
        }
        // swap the values of front node and end node
        int temp = frontNode.val;
        frontNode.val = endNode.val;
        endNode.val = temp;
        return head;
    }
}