/*
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order, and each of their nodes contains a single digit. 
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
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

// Simple iterate over the list till both lists gets exchusted do element wise sum at end if remainder left add it as new node
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode currentNode = dummy;
        int carry = 0;
        
        while(l1 != null || l2 != null) {
            int a = (l1 == null) ? 0 : l1.val;
            int b = (l2 == null) ? 0 : l2.val;
            
            int sum = a + b + carry;
            carry = sum / 10;
            
            currentNode.next = new ListNode(sum%10);
            currentNode = currentNode.next;
            
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;            
        }
        
        if(carry > 0) {
            currentNode.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}