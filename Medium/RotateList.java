/*
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 10^9
*/


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 
class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        int len = 0;
        ListNode curr = head;
        while(curr != null) {
            ++len;
            curr = curr.next;
        }

        if(len == 0) {
            return head;
        }
        
        k = k % len;

        if(head == null || k == 0) {
            return head;
        }

        curr = head;
        for(int itr = 0; itr < len - k - 1; ++itr) {
            curr = curr.next;
        }

        ListNode newHead = curr.next;
        curr.next = null;
        curr = newHead;

        while(curr != null && curr.next != null) {
            curr = curr.next;
        }

        curr.next = head;

        return newHead;
    }

    public ListNode rotateRight1(ListNode head, int k) {
        
        if (head == null || head.next == null) {
            return head;
        }
        
        int len = 0;
        
        ListNode fast = head;
        
        while (fast != null) {
            fast = fast.next;
            ++len;
        }
        
        k = k % len;
        
        ListNode slow = head;
        fast = head;
        
        while (k-- > 0) {
            fast = fast.next;
        }
        
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        
        fast.next = head;
        head = slow.next;
        slow.next = null;
       
        return head; 
    }
}