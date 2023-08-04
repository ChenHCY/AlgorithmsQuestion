/* 105. Construct Binary Tree from Preorder and Inorder Traversal

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:

Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.

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

/* 思路： 此题是通过一个BST二叉树的前序遍历preorder和中序遍历inorder 来还原这个二叉树

因为前序遍历的特性：root, 左子树， 右子树 / 中序遍历的特性：左子树，root, 右子树

所以 1. 找到root根节点，然后 在中序遍历中确认其位置index

2. 中序遍历的特性：左子树，root, 右子树 =》 对应的 【0 <-> rootIndex - 1】就是左子树区域， 【rootIndex + 1 <-> preoreder.length - 1】就是右子树区域

3. 继续递归 =》根据划分的区域继续找到新的root(左右子树的根节点）
    ==》 左子树节点： currRoot(当前节点在preorder中的index) + 1 
    ==》右子树节点： currRoot(当前节点在preorder中的index) + 左子树的个数(rootIndex - inStart + 1)]

4. 然后继续根据中序遍历的特性，找到这两个节点的左右子树部分，缩小区域递归 =》直到使用完所以的节点值，形成了唯一的二叉树
*/

//Time: O(n)  Space: O(n)
class Solution {
    // 前序遍历 preorder = [3, 9, 8, 20, 15, 7] root 左子树 右子树
    // 中序遍历 inorder = [8, 9, 3, 15, 20, 7] 左子树 root 右子树
    // root = 3, index = 2 => 把每个node val和 inorder里面的index 建立映射关系，储存进hashmap中
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //把每个node val 和 inorder里面的index 建立映射关系，储存进hashmap中
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i); // map里面存的是 每个node值 和 其在inorder[]中序遍历的index位置
        }

        //调用递归函数，根节点root 为在 preorder[]前序遍历 每次划分区间后的 左边第一个节点值
        //划分的区间 就是 每个顶点的总的二叉树，=》然后我们可以根据这个值在inoreder里面的index， 来确立其左右子树的个数，继续递归build
        return buildTree(preorder, inorder, 0, 0, preorder.length - 1, map);
    }

    //递归function：根据inorder和prorder的特性 划分每个节点的左右子树区间，然后buil a tree
    public TreeNode buildTree(int[] preorder, int[] inorder, int currRoot, int inStart, int inEnd, HashMap<Integer, Integer> map){

        //exit condition: 越界，通过inStart 和 inEnd 来确定当前节点左右子树是否还存在
        if(currRoot >= preorder.length || inStart > inEnd){
            return null;
        }

        //1. 找到和建立当前区域的根节点root，也就是区域中前序遍历的第一个数 =》 currRoot
        TreeNode root = new TreeNode(preorder[currRoot]); // 建立当前区域的根节点root

        //2. 然后在得到当前根节点 root 的左右子树个数，hashmap里面存的是 每个node值 和 其在 inorder[]中序遍历的index位置
        int rootIndex = map.get(preorder[currRoot]); //根据这个节点值，对应的inorder里面的index, 得到当前根节点的左右子树个数

        //3. 左子树的区域： inorder [isStart <-> rootIndex]， 左子树的root节点的index位置  => currRoot + 1
        root.left = buildTree(preorder, inorder, currRoot + 1, inStart, rootIndex - 1, map);

        //4. 右子树的区域： inorder [(rootIndex + 1) <-> isEnd]， 右子树的root节点的index位置 => currRoot + 左子树的个数(rootIndex - inStart + 1)
        root.right = buildTree(preorder, inorder, currRoot + rootIndex - inStart + 1, rootIndex + 1, inEnd, map);

        return root;
    } 
}
