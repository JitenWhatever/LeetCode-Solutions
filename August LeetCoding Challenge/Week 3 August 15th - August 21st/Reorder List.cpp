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
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    void reorderList(ListNode* head) {
        
        if(head == NULL) {
            return;
        }
        
        ListNode* slow = head;
        ListNode* fast = slow->next;
        
        while(fast != NULL && fast->next != NULL) {
            slow = slow->next;
            fast = fast->next->next;
        }
        
        ListNode* list1 = head;
        ListNode* list2 = slow->next;
        slow->next = NULL;
        
        list2 = reverse(list2);
        
        ListNode* dummy = new ListNode(0);
        
       ListNode* curr = dummy;
        
        while(list1 != NULL || list2 != NULL) {
            if(list1 != NULL) {
                curr->next = list1;
                curr = curr->next;
                list1 = list1->next;
            }
            
            if(list2 != NULL) {
                curr->next = list2;
                curr = curr->next;
                list2 = list2->next;
            }
        }
        
        head =  dummy->next;
    }
    
    ListNode* reverse(ListNode* head) {
        ListNode* curr = head;
        ListNode* next = NULL;
        ListNode* prev = NULL;
        
        while(curr != NULL) {
            next = curr->next;
            curr->next = prev;
            
            prev = curr;
            curr = next;
        }
        
        return head = prev;
    }
};