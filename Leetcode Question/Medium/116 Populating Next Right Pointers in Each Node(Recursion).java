/*Leetcode 116. Populating Next Right Pointers in Each Node (Recursion)
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:
Input: root = []
Output: []
 
Constraints:
The number of nodes in the tree is in the range [0, 212 - 1].
-1000 <= Node.val <= 1000
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        //check whether have next level of Binary tree
        if(root == null || root.left == null){
            return root;
        }
        
      //connect left node with right node
        root.left.next = root.right;
        
      //Start at third level: there is have two parts ==> left side and right side
      //need to connect it the "right" node of left side with "left" node of right side
      /* Example:   2         3
                   /  \      /  \
                  4 -> 5 -> 6 -> 7
      */
      //this if-statement used for connect Node 5 with Node 6
        if(root.next != null){
            root.right.next = root.next.left;
        }
        
      //Move to next level and call Recursion ==> Until end of Binary tree
        connect(root.left);
        connect(root.right);
        return root;
    }
}
