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
//Time: O(n) Space:O(1)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /* .next in the left side means connect with others node
           .next in the right side means the next node of current
        */
        //basic dummy node information
        ListNode dummy = new ListNode(-1); //declare a new ListNode 
        dummy.next = head; //connect dummy node with head
        
        ListNode prev = dummy;  //
        ListNode fast = dummy.next; //used to count the length of ListNode head
        int len = 1;
        int i = 0;
        
        //Count the total length of ListNode
        while(fast.next != null){
            fast = fast.next;
            len += 1;
        }
        
        //if need remove first ListNode in the head
        if(len == n){
            return head.next;
        }
        
        //travser to find remove the Nth Node From End of List
        while(prev.next != null){
            //len - n is used to find the one node before the node needs to be removed
            if(i == len - n){ 
                prev.next = prev.next.next; //remove the Nth Node 
                return dummy.next;
            }
            prev = prev.next; //if not Nth Node, move into next one
            i++; //count the length
        }
    
        return dummy.next;
    }
}
