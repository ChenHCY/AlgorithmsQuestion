/*Leetcode 82. Remove Duplicates from Sorted List II
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list. Return the linked list sorted as well.

Example 1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:
Input: head = [1,1,1,2,3]
Output: [2,3]
 
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
//Time: O(n)    Space: O(1)
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        //Used two-pointer method 
        ListNode dummy = new ListNode(-1);
        dummy.next = head; //connect dummt with ListNode head
        ListNode curr = dummy;
        
        //travser all the element ListNode from head
        //need to save two ListNode before the end
        while(curr.next != null && curr.next.next != null){
            if(curr.next.val == curr.next.next.val){//if find the duplictates node
                //save the duplicates node value
                int value = curr.next.val;
                while(curr.next != null && curr.next.val == value){
                    curr.next = curr.next.next; // jump next one, and move to connect the one next the next one
                }
            } else{ //if is not duplicates
                curr = curr.next; // move curr pointer into next element node
            }
        }
        
        return dummy.next; //retunr the result after remove all the duplicates node
    }
}
