/*Leetcode 107. Binary Tree Level Order Traversal II

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' 
values. (i.e., from left to right, level by level from leaf to root).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []
 

Constraints:
The number of nodes in the tree is in the range [0, 2000].
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
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(root == null){
            return res;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //add the head root into the queue list;
    
        while(!queue.isEmpty()){
            int size = queue.size(); //check every level size
            List<Integer> currLevel = new ArrayList<>(); // the current level list
            
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll(); //get the first element from queue list
                currLevel.add(node.val); //save the current node val into currLevel 
                
                //then check whether current node have left or right part
                if(node.left != null){
                    queue.offer(node.left); //if node have left part, save left part into queue list
                }
                if(node.right != null){
                    queue.offer(node.right);//if node have right part, save right part into queue list
                }
            }
            
             res.add(0, currLevel); //because this question request return the bottom-up level order traversal  
            //so every level should add to the beginning of the res list ==> get the bottom level to up level order traversal  
        }
        return res;
    }
}
