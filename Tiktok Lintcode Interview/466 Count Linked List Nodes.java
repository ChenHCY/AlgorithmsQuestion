/*Lintcode 466 · Count Linked List Nodes

Count how many nodes in a linked list.

Example 1:
	Input:  1->3->5->null
	Output: 3
	
	Explanation: 
	return the length of the list.

Example 2:
	Input:  null
	Output: 0
	
	Explanation: 
	return the length of list.
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

public class Solution {
    /**
     * @param head: the first node of linked list.
     * @return: An integer
     */
    public int countNodes(ListNode head) {
        // write your code here
        if(head == null){
            return 0;
        }

        int count = 1;

        while(head.next != null){
            count++;
            head = head.next;
        }

        return count;
    }
}
