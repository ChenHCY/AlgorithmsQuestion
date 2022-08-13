/* Leetcode 23. Merge k Sorted Lists
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []
 

Constraints:
k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104
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

//Solution 1: Used Divide Conquer Method,  Divide the lists[] list until the size is 2, then Conquer merge sort
//
class Solution {
    //the main function
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        // Call Divide Conquer Method
        return sort(lists, 0, lists.length - 1);    
    }
    
    //Divide Conquer function
    public ListNode sort(ListNode[] lists, int left, int right){
        if(left >= right){
            return lists[left];
        }
        
        // Divide the list of size n until the size is 2. then merge sort
        int mid = (right - left) / 2 + left; //Divide: find mid position
        ListNode l1 = sort(lists, left, mid); 
        ListNode l2 = sort(lists, mid + 1, right);
        return mergeSort(l1, l2); //Conquer: continue to merge sort two lists
    }
    
    //Merge Sort function: used to merget sort of two list
    public ListNode mergeSort(ListNode l1, ListNode l2){
        if(l1 == null) 
            return l2;
        if(l2 == null)
            return l1;
        
        if(l1.val < l2.val){
            l1.next = mergeSort(l1.next, l2);
            return l1;
        }
        
        l2.next = mergeSort(l1, l2.next);
        return l2;
    }
}

//Solution 2: Used Priority Queue 
/* PriorityQueue(int initialCapacity, Comparator<E> comparator): 
Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.*/
//Small -> Large: (o1, o2) -> o1.val - o2.val
//Large -> Small: (o1, o2) -> o2.val - o1.val

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        //PriorityQueue sort every list elemnt value be small to large
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        //add all the listnode from lists[] list into PriorityQueue, use PriorityQueue sort it
        for(ListNode list : lists){
            if(list != null){
                queue.add(list);
            }
        }
        
        //get and add queue.poll() element（first digit）into new ListNode, then adding the second digit back to the queue
        while(!queue.isEmpty()){
            curr.next = queue.poll(); // get and add queue.poll() element（first digit）into new ListNode
            curr = curr.next; //move curr into next element
            if(curr.next != null){
                queue.add(curr.next); // then adding the second digit back to the queue
            }
        }
        
        return dummy.next;//get final result
    }
}
