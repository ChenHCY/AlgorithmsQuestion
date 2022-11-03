/* Leetcode 83. Remove Duplicates from Sorted List
Given the head of a sorted linked list, delete all duplicates such 
that each element appears only once. Return the linked list sorted as well.

Example 1:
Input: head = [1,1,2]
Output: [1,2]

Example 2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]
 
Constraints:
The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
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
//Time: O(n)   Space: O(1)
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        
        ListNode dummy = new ListNode(-1); //dummy node
        dummy.next = head; //connect dummy with ListNode head
        ListNode curr = dummy;
        
        /* dummy(curr) -> 1 -> 1 -> 2  ==> curr start at dummy node*/
        
        //travser all the element ListNode from head
        while(curr.next != null){
            int value = curr.next.val;
            while(curr.next.next != null && curr.next.next.val == value){
                curr.next = curr.next.next; //jump next one, and connect with the one next the next one
            }
            curr = curr.next; //check one by one and save last one from duplicates node
        }
        
        return dummy.next; //return the result after remove all the duplicates node
    }
}
