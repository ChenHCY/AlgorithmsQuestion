/* 894. All Possible Full Binary Trees

Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Example 1:
Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

Example 2:
Input: n = 3
Output: [[0,0,0]]
 

Constraints:
1 <= n <= 20
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
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        createTree(n, res);
        return res;
    }

    //used recursion method to create the left and right binary tree
    public static void createTree(int n, List<TreeNode> tree){
        //exit condition: 如果只剩下一个节点，加入节点值为0，返回输出
        if(n == 1){
            tree.add(new TreeNode(0));
            return;
        } else if(n % 2 == 0){ //如果是偶数，无法形成满二叉树
            return;
        }

        for(int i = 1; i < n - 1; i+=2){
            List<TreeNode> leftTree = new ArrayList<>();
            List<TreeNode> rightTree = new ArrayList<>();
            createTree(i, leftTree); //左子树 + 右子树的节点数量 = n(总节点数量) - 1
            createTree(n - 1 - i, rightTree);
            for(TreeNode l : leftTree){
                for(TreeNode r : rightTree){
                    TreeNode root = new TreeNode(0); // 根节点
                    root.left = l;
                    root.right = r;
                    tree.add(root);
                }
            }
        }
    }
}
