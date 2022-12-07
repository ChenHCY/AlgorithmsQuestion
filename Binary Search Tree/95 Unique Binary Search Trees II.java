/* Leetcode 95. Unique Binary Search Trees II
Given an integer n, return all the structurally unique BST's (binary search trees), 
which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]
 
Constraints:
1 <= n <= 8
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
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<>();
        }
        return helper(1, n);
    }

    //Used the recursion method
    public static List<TreeNode> helper(int start, int end){
        List<TreeNode> res = new ArrayList<>();

        //if there is not node value less than root(start)
        if(start > end){
            res.add(null); //should add the "null" value
            return res;
        }

        //for example: n = 10 start = 1
        for(int i = start; i <= end; i++){
         //find the left and right list of every root node
            List<TreeNode> leftNode = helper(start, i - 1); // null
            List<TreeNode> rightNode = helper(i + 1, end);// 2-9

            //add the value from left and right part into res list
            for(TreeNode left : leftNode){
                for(TreeNode right : rightNode){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
