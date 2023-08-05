/*Leetcode (Biweekly Contest 110) 2807 Insert Greatest Common Divisors in Linked List

Given the head of a linked list head, in which each node contains an integer value.

Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.

Return the linked list after insertion.

The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

Example 1:
Input: head = [18,6,10,3]
Output: [18,6,6,2,10,1,3]
Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes (nodes in blue are the inserted nodes).
- We insert the greatest common divisor of 18 and 6 = 6 between the 1st and the 2nd nodes.
- We insert the greatest common divisor of 6 and 10 = 2 between the 2nd and the 3rd nodes.
- We insert the greatest common divisor of 10 and 3 = 1 between the 3rd and the 4th nodes.
There are no more adjacent nodes, so we return the linked list.

Example 2:
Input: head = [7]
Output: [7]
Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes.
There are no pairs of adjacent nodes, so we return the initial linked list.
 

Constraints:
The number of nodes in the list is in the range [1, 5000].
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
class Solution {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode currNode = head;

        //开始遍历链表, 不能越界，所以 =》 currNode.next != null
        while(currNode.next != null){
            //使用子function 计算相邻节点的最大公约数
            int gcd = findGcd(currNode.val, currNode.next.val);
          
            //建立新节点
            ListNode newNode = new ListNode(gcd);
          
            //加入到链表中, .next在左边表示链接, .next在右边表示下一个节点
            newNode.next = currNode.next; 
            currNode.next = newNode;
            currNode = currNode.next.next; //移动到下一个区域 继续求最大公约数 gcd
        }

        return head;
    }

    // Calculate the greatest common divisor of the two nodes
    // 两个数不停的互相取模%，剩余那个不为0的数就是 这两个数的最大公约数 gcd
    private int findGcd(int a, int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
