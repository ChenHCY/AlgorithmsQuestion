/* Leetcode 203. Remove Linked List Elements
Given the head of a linked list and an integer val, remove all 
the nodes of the linked list that has Node.val == val, and return the new head.

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

//Time: O(n)   Space: O(1)
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return null;
        
        ListNode dummy = new ListNode(-1); //declare a new ListNode
        dummy.next = head;  //connect dummy with head
        ListNode curr = dummy; //Used curr to travser all the element in the head
        
        //travser all the element from head
        while(curr.next != null){
            if(curr.next.val == val){ // Find which node value == val
               curr.next = curr.next.next;
            } else{ // if the current node value is not equal val
                curr = curr.next; // use pre to save these node data
            }
        }
        
        return dummy.next;
    }
}
