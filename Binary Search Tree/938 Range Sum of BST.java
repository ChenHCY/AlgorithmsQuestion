/* 938. Range Sum of BST

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

Example 1:
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.

Example 2:
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 

Constraints:
The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 10^5
1 <= low <= high <= 10^5
All Node.val are unique.
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

//Solution 1: used BFS
//Time: O(n)  Space: O(n)
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }  

        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode curr = queue.poll(); //提取当前节点
            //判断是否在范围里面[low, high]
            if(curr.val >= low && curr.val <= high){
                sum += curr.val;
            }

            //判断有没有左右子树节点
            if(curr.val >= low && curr.left != null){
                queue.offer(curr.left);
            }

            if(curr.val <= high && curr.right != null){
                queue.offer(curr.right);
            }
        }

        return sum;
    }
}

//Solution 2: Used DFS
//Time: O(n)  Space: O(1)
class Solution {
    private int sum;
    public int rangeSumBST(TreeNode root, int low, int high) {
        sum = 0;
        dfs(root, low, high);
        return sum;
    }

    public void dfs(TreeNode node, int low, int high){
        //1. 是否存在这个节点
        if(node == null){
            return;
        }

        //2.节点值是否在范围[low, high]里面
        if(node.val >= low && node.val <= high){
            sum += node.val;
        }

        //3.判断是否有左右子树，且有可能在范围里面
        if(node.val >= low && node.left != null){
            dfs(node.left, low, high);
        }

        if(node.val <= high && node.right != null){
            dfs(node.right, low, high);
        }
    }
}
