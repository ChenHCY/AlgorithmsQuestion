/* Leetcode 889. Construct Binary Tree from Preorder and Postorder Traversal

Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of 
distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.

Example 1:
Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

Example 2:

Input: preorder = [1], postorder = [1]
Output: [1]
 

Constraints:

1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
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
    // preorder 前序遍历：root 左子树 右子树
    // postorder 后序遍历： 右子树  左子树 root
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();

        //把每个值的节点 和 后序遍历postorder的index 存入hashmap
        for(int i = 0; i < postorder.length; i++){
            map.put(postorder[i], i);
        }
        
        return buildTree(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    // recusion function: find the every root to build the Binary Search Tree
    // 定义：根据 preorder[preStart..preEnd] 和 postorder[postStart..postEnd]
    // preorder区域内的第一个数 是 root 节点，postorder区域内的最后一个数 是 root 节点
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] postorder, int poStart, int poEnd, HashMap<Integer, Integer> map){
        //exit condition => 越界
        if(preStart > preEnd){
            return null;
        }

        //如果区域内只剩下一个数值节点：直接创建新的节点，加入到二叉树
        if(preStart == preEnd){
            return new TreeNode(preorder[preStart]);
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootValue = preorder[preStart];

        // root 左子树的节点 的值 是前序遍历第二个元素
        // 通过前序和后序遍历构造二叉树的关键在于通过左子树的根节点
        int leftRootValue = preorder[preStart + 1];

        int leftRootIndex = map.get(leftRootValue); // leftRootVal 在后序遍历数组中的索引
        int leftSize = leftRootIndex - poStart + 1; // 根据leftroot的节点index 得到左子树的数量

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootValue);
      
        //然后构造 左右 子树
        //根据左子树的根节点index 和  左子树的个数  推导左右子树的索引边界
        root.left = buildTree(preorder, preStart + 1, preStart + leftSize, postorder, poStart, leftRootIndex, map);
        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd, postorder, leftRootIndex + 1, poEnd - 1, map);

        return root;
    }
}
