/* 68 · Binary Tree Postorder Traversal 使用递归对于二叉树进行后序遍历
Description
Given a binary tree, return the postorder traversal of its nodes’ values.

Example 1:
Input:
binary tree = {1,2,3}
Output:
[2,3,1]
Explanation:
   1
/  \
2     3
It will be serialized to {1,2,3} followed by post-order traversal

Example 2:
Input:
binary tree = {1,#,2,3}
Output:
[1,3,2]
Explanation:
1
\
2
/
3
It will be serialized to {1,#,2,3} followed by post-order traversal
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
  
  //this is a recursive method function
     public static void preorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
       // // beacuse this is Postorder Traversal, so follow the 1. Visit the left node 2. Visit the right node 3. Visit the root node 
        preorderTraversal(root.left, list); //  1. Visit the left node
        preorderTraversal(root.right, list); // 2. Visit the right node
        list.add(root.val); //  3. Visit the root node 
    }
    

    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        LinkedList<Integer> result = new LinkedList<>(); // Save the data into a LinkedList
        preorderTraversal(root, result);
        return result; // need return a Integer List
    }
}
