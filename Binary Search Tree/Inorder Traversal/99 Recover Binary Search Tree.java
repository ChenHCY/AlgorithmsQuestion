/*Leetcode 99. Recover Binary Search Tree

You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-2^31 <= Node.val <= 2^31 - 1
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

class Solution {
    TreeNode firstWrong = null; //第一个错误的节点
    TreeNode secondWrong = null; //第二个错误的节点
    TreeNode prevNode = null;

    public void recoverTree(TreeNode root) {
        //base case: 如果根节点不存在，直接输出
        if(root == null){
            return;
        }

        //通过helper function 找到位置错误的两个节点
        helperInOrder(root);

        //交换 swap 两个错误节点的值
        int temp = firstWrong.val;
        firstWrong.val = secondWrong.val;
        secondWrong.val = temp;
    }

    //helper function: to do the in-order sorting of Binary Search Tree
    //进行中序遍历 =》 找到位置错误的两个节点
    public void helperInOrder(TreeNode node){
        //base case: 当没有节点的时候
        if(node == null){
            return;
        }

        //in-order 中序遍历：左节点 - 根节点 - 右节点
        //并且得到的所有节点值的排序 是 严格单调递增的
        helperInOrder(node.left); //首先遍历左子树部分

        //当我们要加入节点的时候，进行判断来找到不符合严格递增的两个节点数
        if(prevNode != null && prevNode.val > node.val){
            //如果还没有找到第一个错误节点
            if(firstWrong == null){
                firstWrong = prevNode; //标记第一个错误节点的位置（当前prevNode的节点）
            }
            secondWrong = node; //标记第二个错误节点的位置（当前node的节点）
        }
        prevNode = node; //移动prevNode节点的位置，prev一直保持在每个当前node的前一个节点，
        //用于中序排序的比较 =》 inOrder: 节点值严格递增

        helperInOrder(node.right); //然后遍历右子树部分
    }
}
