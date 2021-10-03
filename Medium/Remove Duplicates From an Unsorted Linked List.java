/*
Given the head of a linked list, find all the values that appear more than once in the list 
and delete the nodes that have any of those values.

Return the linked list after the deletions.

 

Example 1:
https://assets.leetcode.com/uploads/2021/04/21/tmp-linked-list.jpg

Input: head = [1,2,3,2]
Output: [1,3]
Explanation: 2 appears twice in the linked list, so all 2's should be deleted. After deleting all 2's, 
we are left with [1,3].
Example 2:
https://assets.leetcode.com/uploads/2021/04/21/tmp-linked-list-1.jpg

Input: head = [2,1,1,2]
Output: []
Explanation: 2 and 1 both appear twice. All the elements should be deleted.
Example 3:
https://assets.leetcode.com/uploads/2021/04/21/tmp-linked-list-2.jpg

Input: head = [3,2,2,1,3,2,4]
Output: [1,4]
Explanation: 3 appears twice and 2 appears three times. After deleting all 3's and 2's, we are left with [1,4].
 

Constraints:

The number of nodes in the list is in the range [1, 10^5]
1 <= Node.val <= 10^5
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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> seen = new HashMap<>();
        
        ListNode dummy = new ListNode(0, head);
        
        ListNode curr = dummy.next;
        
        while (curr != null) {
            seen.put(curr.val, seen.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }
        
        curr = dummy;
       
        
        while (curr.next != null) {
            if (seen.get(curr.next.val) > 1) {
                curr.next = curr.next.next;
            } else {
                 curr = curr.next;
            }
           
        }
        
        return dummy.next;
    }
}

class Solution {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> seen = new HashMap<>();
        
        ListNode dummy = new ListNode(0, head);
        
        ListNode curr = dummy.next;
        
        while (curr != null) {
            seen.put(curr.val, seen.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }
        
        curr = dummy.next;
        ListNode prev = dummy;
        
        while (curr != null) {
            if (seen.get(curr.val) > 1) {
                prev.next = curr.next;
                curr.next = null;
                curr = prev;
            }
            
            prev = curr;
            curr = curr.next;
        }
        
        return dummy.next;
    }
}