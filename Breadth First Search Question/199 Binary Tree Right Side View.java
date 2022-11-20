/* Leetcode 199. Binary Tree Right Side View

Given the root of a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:
Input: root = [1,null,3]
Output: [1,3]

Example 3:
Input: root = []
Output: []
 
Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
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

/* 

Thought: According to the image, the returned output is the rightmost node of each row
so it is the last one of each row

==> Used BFS to find every row nodes, and for-loop if-statement add the last one node into res list
PS: BFS, instead of saving all the numbers in each level, save the last one.
*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        if(root == null){
            return res;
        }
        
        Queue<TreeNode> pq = new LinkedList<>();
        pq.offer(root);
        int lastElment = 0;
        
        //Travser all the element to find the last one of each row
        while(!pq.isEmpty()){
            int size = pq.size();
            
            for(int i = 1; i <= size; i++){
                TreeNode node = pq.poll();
                
                if(i == size){
                    res.add(node.val);
                }
                
                if(node.left != null){
                    pq.offer(node.left);
                }
                
                if(node.right != null){
                    pq.offer(node.right);
                }
            }
        }
        
        return res;
    }
}
