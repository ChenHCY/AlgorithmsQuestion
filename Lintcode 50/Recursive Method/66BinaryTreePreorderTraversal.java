/* 66 · Binary Tree Preorder Traversal 使用递归对于二叉树进行前序遍历
Description
Given a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input:
binary tree = {1,2,3}
Output:
[1,2,3]
Explanation:
   1
/  \
2     3
It will be serialized as {1,2,3} preorder traversal

Example 2:
Input:
binary tree = {1,#,2,3}
Output:
[1,2,3]
Explanation:
1
\
2
/
3
It will be serialized as {1,#,2,3} preorder traversal
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
     * @return: Preorder in ArrayList which contains node values.
     */
  
  //this is a recursive method function
    public static void preorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
       
       // beacuse this is Preorder Traversal, so follow the 1. Visit the root node 2. Visit the left node 3. Visit the right node
        list.add(root.val); // 1.  Visit the root node 
        preorderTraversal(root.left, list); //2. Visit the left node
        preorderTraversal(root.right, list); // 3. Visit the right node
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>(); // Save the data into a LinkedList
        preorderTraversal(root, result);
        return result; // need return a Integer List
    }
}
