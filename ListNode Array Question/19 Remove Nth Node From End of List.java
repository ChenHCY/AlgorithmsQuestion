/* 19. Remove Nth Node From End of List
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        ListNode next = head.next;
        ListNode temp = head;
        int length = 1;
        
        while(temp.next != null){
            temp = temp.next;
            length++;
        } // get the length of list node array
        
        if (length == n){
            return head.next;
        } // if first list node need remove
        
        int i = 1;
        while(curr.next != null){
            if(i == length - n){
                curr.next = curr.next.next;
                return head;
            }
            curr = curr.next;
            i++;
        } // find the node we want remove before
        return head;
    }
}
