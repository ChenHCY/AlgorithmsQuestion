/*Leetcode 114. Flatten Binary Tree to Linked List

Given the root of a binary tree, flatten the tree into a "linked list":

    · The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
    · The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Example 1:

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
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

//Solution 1：递归 recursion DFS => 前序遍历
//Time: O(n^2)    Space: O(n)
class Solution {
    //递归：把一个二叉树转化为新的二叉树，左子树都为null, 右子树顺序和前序遍历一样
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }

        LinkedList<TreeNode> preSort = new LinkedList<>();
        //前序遍历和排序，把所有节点储存进arraylist
        dfsPre(root, preSort);

        TreeNode resTree = preSort.removeFirst(); //把节点从list一个一个取出来, 首先是根节点
        resTree.left = null; // the left child pointer is always null.
      
        //遍历链表，将链表中的TreeNode节点前后串联起来
        while(preSort.size() > 0){
            TreeNode temp = preSort.removeFirst();
            temp.left = null;
            resTree.right = temp;
            resTree = resTree.right;
        }
    }

    //前序遍历function, 给二叉树root进行前序排序
    private void dfsPre(TreeNode node, LinkedList<TreeNode> res) {
        if(node == null){
            return;
        }
        //前序遍历：root 左子树 右子树
        res.add(node);
        dfsPre(node.left, res);
        dfsPre(node.right, res);
    }
}


//Solution 2：迭代
//Time: O(n)   Space: O(1)
class Solution {
     // 定义：将以 root 为根的树拉平为链表
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }

        // 迭代，拉平左右子树
        flatten(root.left);
        flatten(root.right);

        // 1. 原先的左右子树已经被拉平成一条链表，保存原先的左右子树
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将原先的右子树 变成 左子树
        root.left = null;
        root.right = left;

        // 3. 将原先的右子树接到当前右子树的末端
        TreeNode temp = root;
        while(temp.right != null){
            temp = temp.right; //找到当前二叉树的最后一个右子树节点
        }
        temp.right = right; //然后把原先的右子树接到当前右子树的末端
    }
}
