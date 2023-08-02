/*Leetcode 106. Construct Binary Tree from Inorder and Postorder Traversal

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the 
postorder traversal of the same tree, construct and return the binary tree.

Example 1:

Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]
 

Constraints:

· 1 <= inorder.length <= 3000
· postorder.length == inorder.length
· -3000 <= inorder[i], postorder[i] <= 3000
· inorder and postorder consist of unique values.
· Each value of postorder also appears in inorder.
· inorder is guaranteed to be the inorder traversal of the tree.
· postorder is guaranteed to be the postorder traversal of the tree.
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
    // 中序遍历 inorder = [9,3,15,20,7] 左子树 root 右子树
    // 后序遍历 pastorder = [9,15,7,20,3] 左子树 右子树 root
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //hashmap里面储存的是数值在中序排序中的index位置 => 方便根据数值查找位置 和 统计左右节点的个数
        HashMap<Integer, Integer> map = new HashMap<>(); 
        //使用for-loop进行储存 
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i); //不同数值 在 中序inorder中的index位置
        }

        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    //递归function：根据inorder和postorder的特性 划分每个节点的左右子树区间，然后buil the binary search tree
    //中序遍历数组为 inorder[inStart..inEnd]，
    //后序遍历数组为 postorder[postStart..postEnd]，
    // => 构造这个二叉树并返回该二叉树的根节点
    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map){
        //exit condition: 越界，通过inStart 和 inEnd 来确定当前节点左右子树是否还存在
        if(inStart > inEnd){
            return null;
        }

        //1. 找到和建立当前区域的根节点root，也就是区域中前序遍历的第一个数 =》 currRoot
        int rootVal = postorder[postEnd]; // 每次范围内的 root的节点值 就是 范围内 后序遍历 的最后一个元素

        ///2. 然后在得到当前根节点 root 的左右子树个数，hashmap里面存的是 每个node值 和 其在 inorder[]中序遍历 的index位置
        int rootIndex = map.get(rootVal); // rootVal 在中序遍历数组中的index位置

        int leftSize = rootIndex - inStart;  //根据这个节点值，对应的inorder里面的index, 得到当前根节点的左右子树个数

        TreeNode root = new TreeNode(rootVal); // 建立当前区域的根节点root

        /* rootIndex: rootVal 在中序遍历数组中的index位置   || rootVal: root的节点值 */

        //3. 左子树的区域： 中序: [inStart, rootIndex - 1]   后序: [postStart, postStart + 左子树节点数量(leftSize) - 1]  "index从0开始"
        root.left = buildTree(inorder, inStart, rootIndex - 1, postorder, postStart, postStart + leftSize - 1, map);

        //4. 右子树的区域： 中序: [rootIndex + 1, inEnd]   后序: [postStart + 左子树节点数量(leftSize), postEnd - 1]
        root.right = buildTree(inorder, rootIndex + 1, inEnd, postorder, postStart + leftSize, postEnd - 1, map);

        return root;
    }
}
