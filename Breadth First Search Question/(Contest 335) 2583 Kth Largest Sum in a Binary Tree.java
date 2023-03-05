/*Leetcode (Contest 335) 2583 Kth Largest Sum in a Binary Tree

You are given the root of a binary tree and a positive integer k.

The level sum in the tree is the sum of the values of the nodes that are on the same level.

Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.

Note that two nodes are on the same level if they have the same distance from the root.

Example 1:
Input: root = [5,8,9,2,1,3,7,4,6], k = 2
Output: 13
Explanation: The level sums are the following:
- Level 1: 5.
- Level 2: 8 + 9 = 17.
- Level 3: 2 + 1 + 3 + 7 = 13.
- Level 4: 4 + 6 = 10.
The 2nd largest level sum is 13.


Example 2:

Input: root = [1,2,null,3], k = 1
Output: 3
Explanation: The largest level sum is 3.
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= 106
1 <= k <= n
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

/* 思路： 此题是求出一个二叉树中，第Kth大的层级和

所以使用BFS的思路，把每一层的节点和都计算出来，储存在一个ArrayList中

最后使用 Collections.sort(levelSumList, Collections.reverseOrder()); 进行排序

找到第kth大的数字 ==》  return levelSumList.get(k-1);
 
*/
class Solution {
    public long kthLargestLevelSum(TreeNode root, int k) {
        //BFS Method ==> count the sum of every node value
        List<Long> levelSumList = new ArrayList<>();
        int level = 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            long levelSum = 0; //count the sum of every level node value
            
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                levelSum += Long.valueOf(cur.val);
                
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            
            levelSumList.add(levelSum); //add the sum of every node value into list
            level++; //count the level length of binary tree
        }
        
        if(k > level){ // If there are fewer than k levels in the tree, return -1.
            return -1;
        }
        
        //sort the list of every level sum
        Collections.sort(levelSumList, Collections.reverseOrder());
        return levelSumList.get(k-1);
    }
}
