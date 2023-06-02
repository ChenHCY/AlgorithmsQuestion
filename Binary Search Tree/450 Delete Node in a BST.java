/* Leetcode 450. Delete Node in a BST
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.

Example 1:

Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:

Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.

Example 3:

Input: root = [], key = 0
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-10^5 <= Node.val <= 10^5
Each node has a unique value.
root is a valid binary search tree.
-10^5 <= key <= 10^5
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
    //此题在一个Binary Tree中删除指定的节点值
    public TreeNode deleteNode(TreeNode root, int key) { 
        //1. if root.val == null, 不存在这个Binary Search Tree
        if(root == null){
            return null;
        }

        //2. 如果key值大于 root, 根据二叉树BST的标准 key值一定位于root的右子树部分
        //递归调用直接移动到root的右子树部分进行差找和删除修改
        //然后把完成删除的子树部分重新赋值给当前节点的右子树
        if(key > root.val){
            root.right = deleteNode(root.right, key);
        }

        //3. 如果key值小于 root, 根据二叉树BST的标准 key值一定位于root的左子树部分
        // 递归调用直接移动到root的左子树部分进行查找和删除修改
        // 然后把完成删除的子树部分重新赋值给当前节点的左子树
        if(key < root.val){
            root.left = deleteNode(root.left, key);
        }

        //4. if the curr node value = key
        if(root.val == key){
            //如果当前节点没有左子节点，直接用右子节点进行替换
            if(root.left == null){
                return root.right;
            }

            //如果当前节点没有右子节点，直接用左子节点进行替换
            if(root.right == null){
                return root.left;
            }

            //如果当前节点同时拥有左右子节点，因为要满足Binary Search Tree的左小右大标准
            //则两种替换方案：
            //1. 使用当前节点的左子树部分的最大节点进行替换
            //2. 使用当前节点的右子树部分的最小子节点进行替换
            TreeNode minRightNode = findMinNode(root.right); // 找到右子树部分的最小节点值

            //删除当前节点的右子树节点部分中 最小值节点
            root.right = deleteNode(root.right, minRightNode.val);

            //使用右子树部分的最小值节点替换curr node 当前节点
            minRightNode.left = root.left;
            minRightNode.right = root.right;
            root = minRightNode;
        }

        return root;
    }

    //找到一个二叉树的最小值的节点
    public TreeNode findMinNode(TreeNode node){
        while(node.left != null){
           node = node.left;
        }
        return node;
    }
}
