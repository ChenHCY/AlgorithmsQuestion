/* Leetcode 25. Reverse Nodes in k-Group
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of 
nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000
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

//Solution 1: 
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //the base case
        if(head == null){
            return null;
        }

        ListNode a = head;
        ListNode b = head;

        for(int i = 0; i < k; i++){
            if(b == null){
                return head;
            }
            b = b.next; //移动b指针，直到range里面有k个节点
        }

        ListNode newHead = reverse(a, b); //反转当前range里面的节点
        
        a.next = reverseKGroup(b, k); // 递归反转后续链表并连接起来
        
        return newHead;
    }
    //Reverse the ListNode of range [a, b）
    //反转 [a, b) 区域的 链表节点
    private ListNode reverse(ListNode a, ListNode b){
        ListNode pre = null;
        while(a != b){
            ListNode next = a.next;
            a.next = pre;
            pre = a;
            a = next;
        }
        return pre;
    }
}

//Solution 2: 
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //base case, 如果当前区域的节点数只剩下一个，或 根本不存在这个节点
        if(head == null || head.next == null){
            return head;
        }

        int count = 0;
        ListNode curr = head;
        //每次找到一个range, 里面包含k个节点
        while(count != k && curr != null){
            count += 1;
            curr = curr.next;
        }
        
        if(count == k){
            //下一个区域的链表节点
            curr = reverseKGroup(curr, k);
            
            //反转当前这个range里面的链表节点
            while(count > 0){ //因为有k个节点，所以反转k次
                ListNode temp = head.next;
                head.next = curr;
                curr = head; //把下一个区域的节点curr 移动一位，继续反转
                head = temp; //把当前区域的head节点，移动一位，继续反转
                count--;
            }
            head = curr;
        }

        return head; //输出反转完成的链表
    }
}
