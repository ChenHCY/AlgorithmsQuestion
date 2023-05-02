/*Leetcode 112. Path Sum

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that 
adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Example 1:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.

Example 2:

Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.

Example 3:

Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.
 

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
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return pathSum(root, 0, targetSum);
    }
    
    //dfs function
    public boolean pathSum(TreeNode root, int cur, int targetSum){
      //the exit condition
        if(root == null){
            return false;
        }
        
      //如果存在左右子节点，则把当前节点的值加入到总和，然后检查是否于targetSum相等
        if(root.left == null && root.right == null){
            if(targetSum == cur + root.val){ //如果已经等于targetSum的值，则raturn输出true
                return true;
            } else if (cur + root.val > targetSum){ //如果已经超过targetSum的值，则表示一定不可能等于targetSum，输出false
                return false;
            }
        }
        //继续递归变量当前节点的左右子节点部分
        return pathSum(root.left, cur+root.val, targetSum) || pathSum(root.right, cur+root.val, targetSum);
        
    }
}
