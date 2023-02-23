/* Leetcode 110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:

Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:

Input: root = []
Output: true
 

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/* 思路：

首先高度平衡二叉树的条件：

  a.任何节点的左子树和右子树之间的差异不超过一
  b.左子树是平衡的
  c.右子树是平衡的

采用递归的思路，用一个递归的small function 计算每个节点左右子树的高度

然后得到左右子树的高度差，用if-statement判断是否小于1 小于1代表平衡（true）

同时因为每个节点的左右子树也应该是平衡的 ==》if的判断条件 也必须递归移动判断下一个层级的节点  if(diffH <= 1 && isBalanced(root.left) && isBalanced(root.right)){

*/
class Solution {
    public boolean isBalanced(TreeNode root) {

        /* The conditions for a height-balanced binary tree:
            1. difference between the left and the right subtree for any node is not more than one
            2. the left subtree is balanced
            3. the right subtree is balanced
        */

        //base case
        if(root == null){
            return true;
        }

        //count the height of curr Node left and the right subtree
        int LeftH = height(root.left);
        int rightH = height(root.right);

        // Difference between the left and the right subtree for any node is not more than one
        int diffH = Math.abs(LeftH - rightH); 

        //  the left subtree is balanced &&  the right subtree is balanced
        if(diffH <= 1 && isBalanced(root.left) && isBalanced(root.right)){
            return true;
        }

        return false;
    }

    //recursion function ==> used to count the height of curr Node left and the right subtree
    public static int height(TreeNode root){
        if(root == null){
            return 0;
        }
        //count the height of curr Node left and the right subtree
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
