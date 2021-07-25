/*
Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:
-10 -> -3 -> 0 -> 5 -> 9
            ||
            
            0
           / \
         -3   9
         /   /
       -10   5
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [0]
Output: [0]
Example 4:

Input: head = [1,3]
Output: [3,1]
 

Constraints:

The number of nodes in head is in the range [0, 2 * 104].
-105 <= Node.val <= 105
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
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {   
        return buildBST(head);
    }
    
    private TreeNode buildBST(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        
        ListNode midNode = getRoot(head);
        
        TreeNode root = new TreeNode(midNode.val);
        
        if (head == midNode) {
            return root;
        }
        
        root.left = buildBST(head);
        root.right = buildBST(midNode.next);
        
        return root;
    }
    
    private ListNode getRoot(ListNode head) {
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        
        while (Objects.nonNull(fastPtr) && Objects.nonNull(fastPtr.next)) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        
        if (Objects.nonNull(prevPtr)) {
            prevPtr.next = null;
        }
        
        return slowPtr;
    }
}

// inorder
class Solution {
  public TreeNode sortedListToBST(ListNode head) {
    int size = this.findSize(head);
    this.head = head;
    return buildBST(0, size - 1);
  }
    
  private ListNode head;
    
  private TreeNode buildBST(int low, int high) {
      if (low > high) {
          return null;
      }
      
      int mid = low + (high - low) / 2;
      
      TreeNode left = buildBST(low, mid - 1);
      
      TreeNode root = new TreeNode(this.head.val);
     
      root.left = left;
      
      this.head = this.head.next;
      
      TreeNode right = buildBST(mid + 1, high);
      
      root.right = right;
      
      return root;
  }

  private int findSize(ListNode head) {
    ListNode ptr = head;
    int size = 0;
      
    while (Objects.nonNull(ptr)) {
      ptr = ptr.next;  
      ++size;
    }
      
    return size;
  }

}