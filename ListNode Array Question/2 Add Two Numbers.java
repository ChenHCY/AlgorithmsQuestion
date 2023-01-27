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
        ListNode head = new ListNode(0);
        ListNode res = head;
        int sum = 0; // Sum of two element add
            
        while(!(l1 == null & l2 == null)){
            sum /= 10; //check whether cur Listnode value is large than 10
            
            //if l1 have list node, add the value into sum and move to next
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            } 
            
            //if l2 have list node, add the value into sum and move to next
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            
            // Every node add together can not large than 10
            ListNode temp = new ListNode(sum % 10);
            head.next = temp; //add this listnode into head 
            head = head.next; 
           
        }
     
         //if there still need one more digit carry 总和还需要往前进一位
        if(sum / 10 == 1){
            head.next = new ListNode(1);
        }
        
        return res.next;
    }
}
