/* Leetcode 148. Sort List

Given the head of a linked list, return the list after sorting it in ascending order.
Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []
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

//Solution : Used Merge Sort and "Divide && Conquer" Method
 
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //Used Merge Sort and divide and conquer
        ListNode mid = getMid(head); //Divide the ListNode be two small list
        ListNode left = sortList(head); //get left part list
        ListNode right = sortList(mid); // get right part list
        return mergeSort(left, right); // Conquer: Merge Sort every two lits
    }
    
    //Merge Sort Function: Conquer: Merge Sort every two lits
    public ListNode mergeSort(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        
        if(l2 == null){
            return l1;
        }
        
        if(l1.val < l2.val){
            l1.next = mergeSort(l1.next, l2);
            return l1;
        }
        
        l2.next = mergeSort(l1, l2.next);
        return l2;
    }
    
    //Find mid of ListNode head ==> Divide the ListNode be two small list
    public ListNode getMid(ListNode head){
        ListNode mid = null;
        
        //Find the mid piovt position and divide it
        //When head move arrived at the last node, the midPreve arrived at mid position node
        while(head != null && head.next != null){
            mid = (mid == null) ? head : mid.next;
            head = head.next.next; 
        }
        
        ListNode right = mid.next; //open a ListNode get right part
        mid.next = null; //end recursion, it used to avoid appearing the "stackoverflowerror"
        return right;
    }
}
