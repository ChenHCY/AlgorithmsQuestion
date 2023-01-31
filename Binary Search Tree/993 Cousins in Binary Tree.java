/* Leetcode 993. Cousins in Binary Tree

Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, 
return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.
如果二叉树的两个节点具有相同的深度且具有不同的父节点，则它们是表兄弟。

Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.

Example 1:

Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

Example 3:

Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

Constraints:

The number of nodes in the tree is in the range [2, 100].
1 <= Node.val <= 100
Each node has a unique value.
x != y
x and y are exist in the tree.
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

使用DFS + 递归的手法，遍历Binary Tree的每一个节点

当遇到和x, y想等值的节点时，储存那个节点的父节点和层级(level）

如果 xLevel == yLevel && xParent != yParent，层级一样 父节点不一样时，==》return true 是为表亲

*/

class Solution {
    int xLevel, yLevel; // the level of node x and y
    int xParent, yParent; // the parent node of the node x and y

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, 0, 0); //call dfs function
        // Two nodes of a binary tree are cousins if they have the same depth with different parents
      //如果二叉树的两个节点具有相同的深度且具有不同的父节点，则它们是表兄弟。
        if(xLevel == yLevel && xParent != yParent){
            return true;
        }
        return false;
    }

    //the dfs cuntion to travser the Binary every level nodes
    public void dfs(TreeNode root, int x, int y, int level, int parent){
        //the exit condition ==> find the node x
        if(root.val == x){ 
            xLevel = level;
            xParent = parent;
            return;
        }

        //the exid condition ==> find the node y
        if(root.val == y){
            yLevel = level;
            yParent = parent;
            return;
        }

        //move to next level node of Binary Tree
        if(root.left != null){
            dfs(root.left, x, y, level + 1, root.val);
        }

        if(root.right != null){
            dfs(root.right, x, y, level + 1, root.val);
        }
    }
}
