/* Leetcode 117. Populating Next Right Pointers in Each Node II

Given a binary tree
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:
Input: root = []
Output: []
 
Constraints:
The number of nodes in the tree is in the range [0, 6000].
-100 <= Node.val <= 100
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

//Time: O(n)   Space: O(n)
class Solution {
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root); //add the root first
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                Node node = queue.poll();
                
                if(i < size - 1){
                    node.next = queue.peek();
                }
                
                if(node.left != null){
                    queue.offer(node.left);
                }
                
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        
        return root;
    }
}
