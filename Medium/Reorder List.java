/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
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
    public void reorderList(ListNode head) {
        if(head == null) {
            return ;
        }
        
        ListNode slow = head, fast = head.next;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        
        ListNode list1 = head;
        ListNode list2 = slow.next;
        slow.next = null;
        
        list2 = reverse(list2);
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while(list1 != null || list2 != null) {
            if(list1 != null) {
                curr.next = list1;
                curr = curr.next;
                list1 = list1.next;
            }
            
            if(list2 != null) {
                curr.next = list2;
                curr = curr.next;
                list2 = list2.next;
            }
        }
        
        head = dummy.next;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null, next = null, curr = head;
        
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}