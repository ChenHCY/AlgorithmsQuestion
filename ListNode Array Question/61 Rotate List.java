/* Leetcode 61. Rotate List
Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]
 
Constraints:
The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
*/

//Time: O(n)  Space: O(1)
//Thought: 1. count the length of the ListNode head
//2. connect the tail end with the head end of the ListNode head
//3. Find the first node of the new ListNode array after finished rotates
//4. cut the connect in the tail end and head end  ==> get the final result ListNode array
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
    public ListNode rotateRight(ListNode head, int k) {
        //base case
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode index = head;
        int len = 1;
        
        //count the length of head
        while(index.next != null){
            index = index.next;
            len++;
        }
        
        index.next = head; //connect tail to head
        
        //k % len means need do how many rotates
        //len - k % len means to find the first node after finished rotates
        for(int i = 1; i < len - k % len; i++){
            head = head.next;
        }
        
        ListNode res = head.next; //the first node  of the List after finished rotates
         //            head     res              
        /*  1 ->   2 -> 3   ->  4 -> 5 -> 1 ..   ==> 4 -> 5 -> 1 -> 2 -> 3  */
        head.next = null; // cut the connect tail with head end (for example: cut 3 -> 4)
        
        return res;
    }
}
