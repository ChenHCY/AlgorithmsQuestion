/*Leetcode 1161. Maximum Level Sum of a Binary Tree

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

Example 1:
Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.

Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-10^5 <= Node.val <= 10^5
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

//Solution 1: 使用BFS宽度优先搜索, 对于二叉树进行分层遍历 ==> 计算每一个层级的节点，最多比较得到最大的层级总和sum 和 对应的层级level
//Time: O(n)  Space: O(n)
class Solution {
    public int maxLevelSum(TreeNode root) {
        if(root == null){
            return 0;
        }

        //用来储存每个层级的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //首先把根节点root加入到queue队列中

        int maxSum = Integer.MIN_VALUE; //最大的总和
        int level = 1; //标记层级值 ==> 题目要求从level 1开始
        int maxLevel = 1; //最大总和的层级值

        //遍历每一个层级的节点，然后计算sum ==> 进行比较
        //最后得到最大的层级总和，和对应的层级值
        while(!queue.isEmpty()){
            int size = queue.size(); //当前层级的node数量
            int currSum = 0; //用来计算当前层级的sum

            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                currSum += node.val;

                //check whether still have node in the next level
                if(node.left != null){
                    queue.offer(node.left);
                } 

                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            if(maxSum < currSum){
                maxSum = currSum;
                maxLevel = level;
            }

            level += 1; 
        }

        return maxLevel;
    }
}


//Solution 2: DFS深度优先搜索
