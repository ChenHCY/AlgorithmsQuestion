/*Leetcode 111. Minimum Depth of Binary Tree

Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

Constraints:
The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
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

/* Thought: Used the BFS Method

Through the BFS basic format, and Find which node left side and right both are null

And counts the height

*/

class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        } 
        
        int miniHeight = 1;
        
        Queue<TreeNode> pq = new LinkedList<>();
        pq.offer(root);
        
        while(!pq.isEmpty()){
            int size = pq.size();
            
            for(int i = 0; i < size; i++){
                TreeNode cur = pq.poll();
                
                //find the minimum depth of binary tree
                if(cur.left == null && cur.right == null){
                    return miniHeight;
                }
                
                if(cur.left != null){
                    pq.offer(cur.left);
                }
                
                if(cur.right != null){
                    pq.offer(cur.right);
                }
            }
            
             miniHeight++;
        }
        
        return miniHeight;
    }
}
