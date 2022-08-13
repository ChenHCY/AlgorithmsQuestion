/* 21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.
Example 1:
Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: l1 = [], l2 = []
Output: []

Example 3:
Input: l1 = [], l2 = [0]
Output: [0]
 
Constraints:
The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both l1 and l2 are sorted in non-decreasing order.
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

//Solution 1: Used While-loop traverse and compare the every listnode value from l1 and l2, then can get a new ListNode list
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(); //the new ListNode List
        ListNode result = list;
        
        while(l1 != null || l2 != null) {
            if(l1 != null && l2 != null) {
                if(l1.val < l2.val) {
                    list.next = l1;
                    l1 = l1.next;
                } else{
                    list.next = l2;
                    l2 = l2.next;
                } 
            } else if (l1 == null){
                list.next = l2;
                l2 = l2.next;
            } else if (l2 == null){
                list.next = l1;
                l1 = l1.next;
            }
            list = list.next;
        }
        return result.next;
    }
}

//Solution 2: used the recursive method ==> do not need use any loop

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //this method is used recursive properties
        if(list1 == null){
            return list2;
        }
        
        if(list2 == null){
            return list1;
        }
     
        //compare the listnode value and continue recursive
        if(list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } 
        
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}
