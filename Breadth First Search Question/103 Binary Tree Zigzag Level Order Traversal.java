/*Leetcode 103. Binary Tree Zigzag Level Order Traversal
Given the root of a binary tree, return the zigzag level order 
traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 2000].
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
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        
        Queue<TreeNode> pq = new LinkedList<>();
        pq.offer(root);
        
        while(!pq.isEmpty()){
            int size = pq.size();
            List<Integer> list = new ArrayList<>();
            
            for(int i = 0; i < size; i++){
                TreeNode cur = pq.poll();
                
                //can use the res.size() to be level
                if(res.size() % 2 == 0){ //odd level
                    list.add(cur.val); //add the left to the right
                } else{ //even level ==> add the right to the left
                    list.add(0, cur.val);
                }
                
                //check whether still have node in the next level
                if(cur.left != null){
                    pq.offer(cur.left);
                }
                
                if(cur.right != null){
                    pq.offer(cur.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
