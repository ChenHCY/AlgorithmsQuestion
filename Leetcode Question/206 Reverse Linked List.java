/* 206. Reverse Linked List
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
 
Example2:
Input: head = [1,2]
Output: [2,1]

Example3:
Input: head = []
Output: []
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
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next;
        
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
