/* Leetcode 113. Path Sum II
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values 
in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

Example 1:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22

Example 2:

Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:

Input: root = [1,2], targetSum = 0
Output: []
 
Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
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

//Time: O(n)  Space: O(1)
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>(); //记录符合要求的节点路径
        List<Integer> list = new LinkedList<>(); //记录所有符合要求的路径
        //查找所有总和值等于targetSum的路径
        dfsPath(root, targetSum, list, res, 0); //call the dfs function
        return res;
    }

    public void dfsPath(TreeNode root, int targetSum, List<Integer> list, List<List<Integer>> res, int sum){
        //the exit condition
        //当没有接到时，跳过剩余步骤，直接返回
        if(root == null){
            return;
        }

        //每次都记录当前节点的值，并且把值加入到总和里
        sum += root.val;
        list.add(root.val);

        //the add condition
        //if the current node is a leaf and its value is equal to sum, save the current path
        //当遍历到二叉树的最低端，并且总和等于targetSum值的时候，表示这条路径的值是targetSum， 存入res list中
        if(root.left == null && root.right == null && sum == targetSum){
            res.add(new ArrayList<>(list));
        }

        //继续递归遍历当前节点的下一层左右子节点
        dfsPath(root.left, targetSum, list, res, sum); // traverse the left sub-tree
        dfsPath(root.right, targetSum, list, res, sum); // traverse the right sub-tree

        // Remove the current node from the path to backtrack, 
        // We must remove the current node while going up the recursive call stack.
        list.remove(list.size() - 1); //backtracking, 回溯到上一层节点时，必须要删除当前的节点
    }

}
