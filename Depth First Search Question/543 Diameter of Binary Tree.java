/* Leecode 543. Diameter of Binary Tree
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
Input: root = [1,2]
Output: 1
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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res); //call the dfs function
        return res[0];
    }
    
    public int dfs(TreeNode root, int[] res){
        if(root == null){  //if can not find child node
            return 0; //it means there is no Diameter can add
        }
        
        //recursive to find there is how many nodes and the diameter of the every root's left part
        int left = dfs(root.left, res);
        //recursive to find there is how many nodes and the diameter of the every root's right part
        int right = dfs(root.right, res);
        
        //Compare every level diameter and find the max length
        res[0] = Math.max(res[0], left + right);
        
        return Math.max(left, right) + 1; //add the root, get the the diameter of the tree.
    }
}
