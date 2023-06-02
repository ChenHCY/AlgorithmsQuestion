/* Leetcode 700. Search in a Binary Search Tree

You are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.

Example 1:

Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]

Example 2:

Input: root = [4,2,7,1,3], val = 5
Output: []
 
Constraints:

The number of nodes in the tree is in the range [1, 5000].
1 <= Node.val <= 10^7
root is a binary search tree.
1 <= val <= 10^7
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

//Time: O(h)  the height of the BST root
//Space: O(1）
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode res = root;

        //base case: if root is null, did not have Binary Search Tree
        if(root == null){
            return null;
        }

        //1. root.val > val; 根据BST的特性，val值一定在当前节点的左子树部分
        //递归调用到当前节点的左子树部分，继续进行查找
        if(root.val > val){
            res = searchBST(root.left, val);
        }

        //2. root.val < val; 根据BST的特性，val值一定在当前节点的右子树部分
        //递归调用到当前节点的左子树部分，继续进行查找
        if(root.val < val){
            res = searchBST(root.right, val);
        }

        //3.root == val, 当前节点的值等于val
        if (root.val == val){
            res = root;
        }

        return res;
    }
}
 
