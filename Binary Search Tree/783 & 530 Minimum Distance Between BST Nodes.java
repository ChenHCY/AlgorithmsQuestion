/* Leetcode  783 Minimum Distance Between BST Nodes  && 530. Minimum Absolute Difference in BST
Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

Example 1:
Input: root = [4,2,6,1,3]
Output: 1

Example 2:
Input: root = [1,0,48,null,null,12,49]
Output: 1
 
Constraints:

The number of nodes in the tree is in the range [2, 100].
0 <= Node.val <= 105
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

/*
题意：找到二叉搜索树 (BST) 中任意两个节点之间的最小差异

思路：

首先 因为根据二叉树的特性，左边节点 < 根节点 < 右边节点
     并且中序遍历的顺序是 左边节点 -> 根节点 -> 右边节点
     
==》所有使用中序遍历可以得到一个按递增顺序排列的List

然后再通过for-loop进行遍历查找，就能得到任意两个节点之间的最小差异

*/

//Time: O(n)  Space:O(n）
class Solution {
    public int minDiffInBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrderTraversal(root, res); // 得到一个按递增顺序排列的List
        int minDis = Integer.MAX_VALUE;

        //通过for-loop进行遍历查找，就能得到任意两个节点之间的最小差异
        for(int i = 1; i < res.size(); i++){
            minDis = Math.min(minDis, res.get(i) - res.get(i-1));
        }

        return minDis;
    }

    //the dfs function to do the pre Order Traversal
    //因为根据二叉树的特性，左边节点 < 根节点 < 右边节点
    //并且中序遍历的顺序是 左边节点 -> 根节点 -> 右边节点
    //所有使用中序遍历可以得到一个按递增顺序排列的List
    public static void preOrderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }

        // beacuse this is Inorder Traversal, 
        //so follow the 1.Visit the left node 2. Visit the root node 3. Visit the right node
        preOrderTraversal(root.left, list); // 1.Visit the left node
        list.add(root.val);                 // 2. Visit the root node
        preOrderTraversal(root.right, list);// 3. Visit the right node
    }
}
