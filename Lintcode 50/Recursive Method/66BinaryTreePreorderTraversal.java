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
        list.add(root.val); // beacuse is Preorder Traversal, so add the Tree Node first
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>(); // Save the data into a LinkedList
        preorderTraversal(root, result);
        return result; // need return a Integer List
    }
}
