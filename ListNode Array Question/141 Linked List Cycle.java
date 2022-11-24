/* Leetcode 141. Linked List Cycle
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can 
be reached again by continuously following the next pointer. Internally, pos is 
used to denote the index of the node that tail's next pointer is connected to. 

Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
*/

/*

Thought: Used two pointer listnode

fast-pointer: every time move two nodes
slow-pointer: every time move one node

while-loop to travser it ==> condition: fast !=null && fast.next !=null
==> the fast-pointer will not move arrive out of line
==> And if slow == fast, there is a cycle in the LinkedList ==> return true;
==> otherwise return false

*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        //base case
        if(head == null){
            return false;
        }
        
      //fast-pointer: every time move two nodes
      //slow-pointer: every time move one node
        ListNode fast = head.next;
        ListNode slow = head;
        
      //while-loop to travser it ==> condition: fast !=null && fast.next !=null
      //the fast-pointer will not move arrive out of line
        while(fast !=null && fast.next !=null) {
            if(fast == slow){
                return true;
            }//if slow == fast, there is a cycle in the LinkedList ==> return true;
            
            fast = fast.next.next;
            slow = slow.next;
        }
        // otherwise return fals
        return false;
    }
}
