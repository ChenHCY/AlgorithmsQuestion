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
    //检查相邻两个节点是不是重复的值，如果是的，则跳过，并且链接当前节点和下下个节点
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode cur = head;

        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next; //跳过下一个节点，链接当前节点和下下个节点
            } else{
                cur = cur.next; //如果不是重复的，直接移动cur指针到下一个节点
            }
        }

        return head;
    }
}
