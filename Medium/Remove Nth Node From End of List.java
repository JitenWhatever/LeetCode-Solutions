/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
1-->2-->3-->4-->5 => 1-->2-->3-->5

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

Follow up: Could you do this in one pass?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

//  one pass
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        int index = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (index++ <= n) {
            node = node.next;
        }
        
        ListNode start = dummy;
       
        while (node != null) {
            node = node.next;
            start = start.next;
        }
        start.next = start.next.next;
        return dummy.next;
    }
}

//Two pass
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int length  = 0;
    ListNode first = head;
    while (first != null) {
        length++;
        first = first.next;
    }
    length -= n;
    first = dummy;
    while (length > 0) {
        length--;
        first = first.next;
    }
    first.next = first.next.next;
    return dummy.next;
}