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
        //.next in the right side means the next node of the curr node
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

//Solution 2: 链表中心点 + 反转链表 + merge sort List  ==》 寻找链表中点 + 链表逆序 + 合并链表
/*

思路： Step 1: 通过快慢节点 来找到链表ListNode的中点 （参考「876. 链表的中间结点」）。
       ==> 我们可以使用快慢指针来 O(N)地找到链表的中间节点。
       
      Step 2: 翻转第二部分的list, revser the half part start at slow pointer
       ==》 同时把LinkedList切割成两部分 ==>  将原链表的右半端反转
       
      Step 3: 归并排序来组合成新的LinkedList => 将原链表的两端合并。
*/

//时间复杂度：O(N) 其中 N 是链表中的节点数。 空间复杂度：O(1)
class Solution {
    public void reorderList(ListNode head) {
        //base case
        if(head == null || head.next == null || head.next.next == null){
            return;
        }

        //Step 1: 通过快慢节点 来找到链表ListNode的中点
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        } //now, slow is the middle node of head

        //Step 2: 翻转第二部分的list, revser the half part start at slow pointer
        //同时把LinkedList切割成两部分
        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null; //在中心点切割LinkList
        l2 = reverseList(l2); //翻转第二部分的LinkedList, [Mid, end]

        //Step 3: 归并排序来组合成新的LinkedList
        mergeSort(l1, l2);
    }

    //the small function to use for reverse the linked list
    public static ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //the small function to use merge sort two ListNode
    public static void mergeSort(ListNode l1, ListNode l2){
        ListNode next1;
        ListNode next2;

        while(l1 != null && l2 != null){
            next1 = l1.next;
            next2 = l2.next;

            l1.next = l2;
            l1 = next1;

            l2.next = l1;
            l2 = next2;
        }
    }
}
