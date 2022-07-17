/*Leetcode 2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode head = new ListNode(-1);
        ListNode res = head;
        int digiht = 0;
            
        while(!(n1 == null & n2 == null)){
            int v1 = 0;
            int v2 = 0;
            
            if(n1 != null){
                v1 = n1.val;
                n1 = n1.next;
            } 
            
            if(n2 != null){
                v2 = n2.val;
                n2 = n2.next;
            }
            
            int curNode = v1 + v2 + digiht;
            //add this listnode into res
            res.next = new ListNode(curNode % 10); // every node add together can not large than 10
            res = res.next;
            //check whether cur Listnode value is large than 10
            digiht = curNode / 10;
        }
        
        if(digiht > 0){
            res.next = new ListNode(digiht);
        }
        
        return head.next;
    }
}
