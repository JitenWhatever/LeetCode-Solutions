/*
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
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
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode currentNode = dummy;

        while(currentNode != null && currentNode.next != null) {
            if(currentNode.next.val == val) {
                currentNode.next = currentNode.next.next;
            }
            else {
                currentNode = currentNode.next;
            }
        }

        return dummy.next;
    }
}

