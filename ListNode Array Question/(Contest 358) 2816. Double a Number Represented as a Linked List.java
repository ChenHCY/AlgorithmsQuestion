/*Leetcode 2816. Double a Number Represented as a Linked List

You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.

Return the head of the linked list after doubling it.

Example 1:
Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.

Example 2:
Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
 
Constraints:
The number of nodes in the list is in the range [1, 10^4]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
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
    public ListNode doubleIt(ListNode head) {
        //reverse the linkedList node first
        head = reverseList(head); //因为数学计算 要从最低位开始添加，所以首先翻转这个链表
        
        ListNode root = head;
        int carry = 0; //进位的值
        
        //数学计算，把链表的值都乘以2，然后得到新的链表值
        while(root != null){
            int doubleValue = root.val * 2 + carry;
            root.val = doubleValue % 10;
            carry = doubleValue / 10;
           
           //如果遍历到最后一个节点，且存在有进位的情况，给进位的值创建一个新的节点，并且和链表链接
            if(root.next == null && carry > 0){
                root.next = new ListNode(carry);
                carry = 0; //进位值归零
                break;
            }
            
            root = root.next;
        }
        
        return reverseList(head); //最后再次翻转链表，进行还原
    }
    
    //function: 翻转链表 reverse the List Node
    private ListNode reverseList(ListNode root){
        ListNode pre = null;
        ListNode curr = root;
        
        while(curr != null){
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }
}
