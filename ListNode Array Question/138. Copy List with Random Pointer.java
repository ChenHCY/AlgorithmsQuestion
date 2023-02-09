/* Leetcode 138. Copy List with Random Pointer

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value 
set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in 
the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the 
new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in 
the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.

Your code will only be given the head of the original linked list.

 
Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
 

Constraints:

0 <= n <= 1000
-104 <= Node.val <= 104
Node.random is null or is pointing to some node in the linked list.
*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/*
    思路： 这个题目是要求copy一段ListNode head，其中的每个节点都包括两个指针 ==》 next 和 random (随机）
    
    所有还是和133题clone graph差不多的思路，使用一个hashmap，把旧节点和创建的新节点 连接起来
    
    然后两个while-loop:
    
    ==>  第一个while-loop: 给每个节点都复制创造一个新的节点，加入到HashMap中
    
    ==> 第二个while-loop: 找到每个节点的对应的next random，然后复制到新节点中
    
     1. 复制连接每个新节点的next指针 ==》 map.get(curr).next = map.get(curr.next); ==> curr表示旧节点，通过hashmap映射找到新节点
     2. 复制连接每个新节点的random指针 ==》 map.get(curr).random = map.get(curr.random); ==> curr表示旧节点，通过hashmap映射找到新节点
    
    最后返回老节点（head) 在hashmap中的对应，也就是所有新的节点 ==》  return map.get(head); 
*/

class Solution {
    //Deep copy ==> copy the all nodes from head
    public Node copyRandomList(Node head) {
        //Used HashMap to map the old node with the new node 
        HashMap<Node, Node> map = new HashMap<>();

        Node curr = head; //Used for taverse all the node from head list
        //First while loop: Create the new node, any copy the value of old node
        //第一个while-loop: 给每个节点都复制创造一个新的节点，加入到HashMap中
        while(curr != null){
            map.put(curr, new Node(curr.val));
            curr = curr.next; //then move curr pointer into next node
        }

        //更新curr节点 再次从head的头部开始遍历
        curr = head; //renew the curr node start at begin of the head again
      //第二个while-loop: 找到每个节点的对应的next random，然后复制到新节点中
        while(curr != null){
            //新节点的next和random指针都应指向复制列表中的新节点
            //1. 复制连接每个新节点的next指针
            map.get(curr).next = map.get(curr.next);
            //2. 复制连接每个新节点的random指针
            map.get(curr).random = map.get(curr.random);
            //then move curr pointer into next node
            curr = curr.next;
        }

        return map.get(head); //最后返回老节点在hashmap中的对应，也就是所有新的节点
    }
}
