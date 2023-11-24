/*Leetcode 147. Insertion Sort List

Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

The steps of the insertion sort algorithm:

1. Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
2. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
3. It repeats until no input elements remain.


The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element 
in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
 

Constraints:
The number of nodes in the list is in the range [1, 5000].
-5000 <= Node.val <= 5000
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

//Time: O(n^2)  Space: O(1)
class Solution {
    // used insertion sort 插入排序，sort a list node in increasing order
    public ListNode insertionSortList(ListNode head) {
        //base case
        if(head == null || head.next == null){
            return head;
        }
        //创建一个虚拟的ListNode, 链接上head
        ListNode dummy = new ListNode(-1);
        dummy.next = head; // .next在左边表示链接
        ListNode cur = head; // 用来从左往右遍历
        ListNode prev = null;
        ListNode temp = null;
        
        while(cur != null && cur.next != null){
            // 符合要求的递增排序
            if(cur.val <= cur.next.val){
                cur = cur.next; 
            } else { // 如果当前值大于下一个值，不符合要求
                prev = dummy; //pre指针从最小开始遍历，找到可以加入节点值的位置
                temp = cur.next; // 记录下一个的节点值，移动其到正确的位置
                cur.next = cur.next.next; // 链接当前节点和"下下一个节点"，跳过下一个节点
                while(prev.next.val <= temp.val){
                    prev = prev.next; //pre指针从最小开始遍历，找到可以加入节点值的位置
                }
                //把temp节点值 插入到prev 和 prev.next 中间
                temp.next = prev.next;
                prev.next = temp;
            }
        }

        return dummy.next;
    }
}
