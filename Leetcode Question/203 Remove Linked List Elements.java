/* Leetcode 203. Remove Linked List Elements
Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

Example 1:
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]

Example 2:
Input: head = [], val = 1
Output: []

Example 3:
Input: head = [7,7,7,7], val = 7
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
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return null;
        ListNode start = head; 
        ListNode prev = null;
        
        while(head != null){
            ListNode next = head.next;
            if(head.val == val){ // Find which node value == val
                if(prev != null){ // if this node have a pre ListNode
                    prev.next = next; // move out this node and contact prev node into the next node
                } else{ // if this node is first ListNode (did not have any pre list node)
                    start = next; // move start pointer into next ListNode
                }
            } else{ // if the current node value is not equal val
                prev = head; // use pre to save these node data
            }
            head = next;// move head into head.next
        }
        
        return start;
    }
}
