/* Leetcode 92. Reverse Linked List II
Given the head of a singly linked list and two integers left and right where left <= right, 
reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]
 
Constraints:
The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n
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
//.next in left side means connect with which node
//.next in right side means next node of current node

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null){
            return head;
        }
        //basic dummy node information
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        
        //find the prev node before left position node
        for(int i = 1; i < left; i++){
            prev = prev.next;
        }
        
        ListNode curr = prev.next; //the node in the left position
        
        //.next in left side means connect with which node
        //.next in right side means next node of current node
        /* Example: 1(prev) -> 2(curr) -> 3(nextNode) -> 4 -> 5
        first time: 1(prev) -> 3 -> 2(curr) -> 4(nextNode) -> 5
        second time: 1(prev) -> 4(nextNode) -> 3 -> 2(curr) -> 5
        */
        for(int i = left; i < right; i++){
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }
        
        return dummy.next; //return the LinkedList Node after changed
    }
}
