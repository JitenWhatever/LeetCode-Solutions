/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
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
    public ListNode mergeKLists(ListNode[] lists) { 
        List<Integer> result = new ArrayList<>();
        
        flattenList(lists, result);
        
        ListNode dummy = new ListNode(0);
        
        ListNode head = dummy;
        
        Collections.sort(result);
        
        for (int num : result) {
            ListNode node = new ListNode(num);
            head.next = node;
            head = head.next;
        }
        
        head.next = null;
        
        return dummy.next;
    }
    
    private void flattenList(ListNode[] lists, List<Integer> result) {
        for (ListNode list : lists) {
            while (Objects.nonNull(list)) {
                result.add(list.val);
                list = list.next;
            }
        }
    }
}
//  Sorting
// Time complexity : O(NlogN) 
// Space complexity : O(N)


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
    public ListNode mergeKLists(ListNode[] lists) { 
        int minIndex = 0;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (true) {
            boolean isBreak = true;
            int minValue = Integer.MAX_VALUE;
            
            for (int index = 0; index < lists.length; ++index) {
                if (Objects.nonNull(lists[index])) {
                    if (lists[index].val < minValue) {
                        minIndex = index;
                        minValue = lists[index].val;
                    }
                    isBreak = false;
                }
            }
            if (isBreak) {
                break;
            }
            
            ListNode node = new ListNode(lists[minIndex].val);
            head.next = node;
            head = head.next;
            lists[minIndex] = lists[minIndex].next;
        }
        
        head.next = null;
        
        return dummy.next;
    }

}

// compare one by one
// Time complexity : O(kN)
// Space complexity : O(N)


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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        for (ListNode list : lists) {
            if (Objects.nonNull(list)) {
                pq.add(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (!pq.isEmpty()) {
            head.next = pq.poll();
            head = head.next;
            ListNode next = head.next;
            
            if (Objects.nonNull(next)) {
                pq.add(next);
            }
        }
        
        return dummy.next;
    }
}

// Optimize Approach 2 by Priority Queue
// Time complexity : O(Nlogk) 
// Space complexity : O(1)



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
    public ListNode mergeKLists(ListNode[] lists) {
        
        if (Objects.isNull(lists) || lists.length == 0) {
            return null;
        }
        
        if (lists.length == 0) {
            return lists[0];
        }
        
        ListNode head = mergeTwoLists(lists[0], lists[1]);
        
        for (int index = 2; index < lists.length; ++index) {
            head = mergeTwoLists(head, lists[index]);
        }
        
        return head;
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            if (l1.val < l2.val) {
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }
        
        if (Objects.isNull(l1)) {
            head.next = l2;
        }
        
        if (Objects.isNull(l2)) {
            head.next = l1;
        }
        
        return dummy.next;
    }
}
// Merge with Divide And Conquer
// Time complexity : O(NlogK) 
// Space complexity : O(1)

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
    public ListNode mergeKLists(ListNode[] lists) {
        
        if (Objects.isNull(lists) || lists.length == 0) {
            return null;
        }
    
        
        int interval = 1;
        
        while (interval < lists.length) {
             for (int index = 0; index + interval < lists.length; index += interval * 2) {
                lists[index] = mergeTwoLists(lists[index], lists[index + interval]);
            }
            
            interval *= 2;
        }
       
        return lists[0];
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            if (l1.val < l2.val) {
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }
        
        if (Objects.isNull(l1)) {
            head.next = l2;
        }
        
        if (Objects.isNull(l2)) {
            head.next = l1;
        }
        
        return dummy.next;
    }
}