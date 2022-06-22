/*Leetcode 83. Remove Duplicates from Sorted List
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Example 1:
Input: head = [1,1,2]
Output: [1,2]

Example 2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        
        ListNode start = head; // first pointer
        ListNode fast = head.next; // second pointer
        ListNode result = start;
        
        while(fast != null){
            if(fast.val != start.val){ // when the value of two pointers are difference
                start.next = fast; // move first pointer into second node
                start = start.next; // move first node into second node
            }
            //when the value of two pointers are same
            fast = fast.next; // move second Node into next ListNode
        }
        
        start.next = fast; 
        return result;
        //Use two pointer Method remove the same value ListNode
    }
}
