/* Leetcode 24. Swap Nodes in Pairs
Given a linked list, swap every two adjacent nodes and return its head. 
You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]
 

Constraints:
The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
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
/* L1 1 -> 2 -> 3 -> 4*/
//    L2
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //Time: O(n)   Space O(1)
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode l1 = temp;
        ListNode l2 = head; //l2 will not change, always equals ListNode head
        
        while(l2 != null && l2.next != null){
            ListNode nextStart = l2.next.next; //save next change start postion
            l1.next = l2.next; //L1 -> 2 -> ...
            l2.next.next = l2; // L1 -> 2 -> 1 -> ...
            l2.next = nextStart; // L1 -> 2 -> 1 -> 3 -> 4 ==> connect next two adjacent nodes part
            //beacuse l2 did not happend change, so l2 always in the node '1'
            //now finished changing the first part, need to move l1 and l2 into the next part(every two adjacent nodes)
            l1 = l2; // 2 -> 1 -> l1 3 -> 4
            l2 = l2.next;//          l2      ==> contiue to swap next two adjacent nodes
        }
        
        return temp.next; // temp.next = head;
    }
}
