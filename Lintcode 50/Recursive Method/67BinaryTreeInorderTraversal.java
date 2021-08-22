/* 67 · Binary Tree Inorder Traversal  使用递归对于二叉树进行中序遍历
Description
Given a binary tree, return the inorder traversal of its nodes‘ values.

Example 1:
Input:
binary tree = {1,2,3}
Output:
[2,1,3]
Explanation:
   1
/  \
2     3
It will be serialized as {1,2,3} inorder traversal

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
It will be serialized as {1,#,2,3} inorder traversal
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
     * @return: Inorder in ArrayList which contains node values.
     */
  
//this is a recursive method function
    public static void preorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
      // beacuse this is Inorder Traversal, so follow the 1.Visit the left node 2. Visit the root node 3. Visit the right node
        preorderTraversal(root.left, list); // 1.Visit the left node
        list.add(root.val);                 // 2. Visit the root node
        preorderTraversal(root.right, list);// 3. Visit the right node
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        LinkedList<Integer> result = new LinkedList<>(); // Save the data into a LinkedList
        preorderTraversal(root, result);
        return result; // need return a Integer List
    }
}
