/* Lintcode 102  Linked List Cycle

Description
Given a linked list, determine if it has a cycle in it.

Example 1:
Input:
linked list = 21->10->4->5，then tail connects to node index 1(value 10).
Output:
true
Explanation:
The linked list has rings.

Example 2:
Input:
linked list = 21->10->4->5->null
Output:
false
Explanation:
The linked list has no rings.

Challenge
Can you solve it without using extra space?
*/

/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

//Solution 1: Used Hashset Method ==> HashSet does not allow duplicate value.
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
        HashSet<ListNode> res = new HashSet<>();

        while(head != null){
            if(!res.add(head)){
                return true;
            }
            head = head.next;
        }

        return false;
    }
}


//Solution 2: Used Two Pointer Method: Fast and slow pointer
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
        if(head == null){
            return false; //The linked list dose not have rings.
        }

        //slow and the fast pointer should start at a different node
        ListNode slow = head;
        ListNode fast = head.next; 

        while(slow != fast){//when slow == fast, means the linked list have ring and nedd out of the while-loop (return true)
            //when fast can arrived null, means the linked list did not have rings 
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next; //slow pointer move one node in every time
            fast = fast.next.next; //fast pointer move two nodes in every time
        }

        return true;// The linked list has rings.
    }
}
