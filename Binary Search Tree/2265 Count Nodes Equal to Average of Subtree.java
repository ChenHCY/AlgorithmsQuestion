/* Leetcode 2265. Count Nodes Equal to Average of Subtree

Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.

 Example 1:
 Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.


Example 2:
Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 

Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
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
 // 计算有多少个node节点的值 等于 该node节点 子树总和的平均值
class Solution {
    int totalNodes = 0; //记录这个二叉树总共的节点数量
    int res = 0; // 有多少个满足要求的节点数
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return res;
    }

    // dfs function: 深度遍历 计算每个节点 子树的Sum总和
    private int dfs(TreeNode node){
        //exit condition
        if(node == null){
            return 0;
        }

        // 总的节点数 - 当前节点位置 = 该节点子树节点的数量
        int currNode = totalNodes; // 记录当前节点的位置
        totalNodes++;

        //继续遍历计算该节点子树的总和
        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);
        
        //计算该节点子树节点的总和值
        int sum = node.val + leftSum + rightSum;

        //如果当前node节点的值 等于 该node节点 子树总和的平均值
         // 总的节点数 - 当前节点位置 = 该节点子树节点的数量
        if(node.val == (sum) / (totalNodes - currNode)){
            res++; //计数
        }

        return sum; //dfs function是统计子树sum的
    }
}
