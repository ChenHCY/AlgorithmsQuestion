/* Leetcode 143. Reorder List

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:

Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:

Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
 

Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
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

//Solution 1: Time: O(n)  Space: O(n)

/*思路： 因为链表的缺点就是不能随机存储，

所以我们可以通过线性列表来对于ListNode进行重组

首先把链表中的所有 Node 存储到线性表 List 中，然后用双指针 分别从头尾开始依次 取元素即可。
*/
class Solution {
    public void reorderList(ListNode head) {
        //把listnode 存入线性列表中, 通过线性列表进行重组
        //base case
        if(head == null){
            return;
        }

        //.next in the left side means pointer connect
        //.next in the right side means move into next node
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }

        //开始组合成新的ListNode
        int i = 0;
        int j = list.size() - 1;
        while(i < j){
            // L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
            list.get(i).next = list.get(j); //
            i++;
            //偶数个节点的情况，会提前相遇
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}

//Solution 2: 反转链表 + merge sort
