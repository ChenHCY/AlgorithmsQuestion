/* Leetcode 234. Palindrome Linked List
Given the head of a singly linked list, return true if it is a palindrome.

Example 1:
Input: head = [1,2,2,1]
Output: true

Example 2:
Input: head = [1,2]
Output: false


Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {  
        ListNode slow = head; //slow pointer
        ListNode fast = head.next; // fast pointer
        
        //this is while-loop is used to find the mid place of LinkedList
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next; //fast-pointer is move fast two times than slow-pointer
        }
        
        slow = reverse(slow); // reverse the the second half(Start at mid -> end) of LinkedList 
        fast = head; // restate fast become head 
        
        while(slow != null && fast != null){
            if(slow.val != fast.val){
                return false; // if have diference value, return false (not Palindrome Linked List)
            }
            
            slow = slow.next; // move to check next node value
            fast = fast.next; //move to check next node value
        }
        
        return true;   // return true => Palindrome Linked List
    }
    
    
    public ListNode reverse(ListNode root){
        ListNode prev = null;
        while(root != null){
            ListNode temp = root.next;
            root.next = prev;
            prev = root;
            root = temp;
        }
        return prev;
    }
}
