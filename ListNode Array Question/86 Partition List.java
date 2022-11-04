/* Leetcode 86. Partition List
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]
 
Constraints:
The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
*/

//Time: O(n)  Space: O(1)
//Create two ListNode, and travser to find which is small and which is large than x value
//Then Connect the small value part with large value part ==> get final result 

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
    public ListNode partition(ListNode head, int x) {
        //create two ListNode, and travser to find which is small and which is large than x value
        ListNode dummyLow = new ListNode(-1); //the smaller part than x value
        ListNode curr1 = dummyLow; //it could seem as a pointer move in the dummyLow List
        ListNode dummyLarge = new ListNode(-1);//the larfe part than x value
        ListNode curr2 = dummyLarge;  //it could seem as a pointer move in the dummyLarge List
        
        while(head != null){
            ListNode temp = new ListNode(head.val); //get the every current value
            if(head.val < x){ //if find the list node value smaller than x, connect with dummyLow
                curr1.next = temp; // add the value into the dummyLow list
                curr1 = curr1.next; //move curr1 - pointer into next element
            } else{ //if find the list node value large than x, connect with dummyLarge
                curr2.next = temp; // add the value into the dummyLarge list
                curr2 = curr2.next; //move curr2 - pointer into next element
            }
            head = head.next; // move to next ListNode in the head
        }
        
         //connect the two ListNode
        curr1.next = dummyLarge.next;
        return dummyLow.next; //output the result LinkedLisr
    }
}
