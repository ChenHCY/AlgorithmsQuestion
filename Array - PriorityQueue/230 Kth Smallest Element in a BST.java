/* Leetcode 230. Kth Smallest Element in a BST

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
 

Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
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
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        }); //降序排列，PrioirityQueue从左侧顶端删除

        findValue(pq, root, k); //得到剩余最小的k个数字的

        return pq.peek(); //输出第k小的数字
    }

    public void findValue(PriorityQueue<Integer> pq, TreeNode root, int k){
        if(root == null){ //如果不存在节点，跳出递归
            return;
        }

        pq.add(root.val); //把当前节点的值加入到优先队列中
        
        if(pq.size() > k){ //保持 pq优先队列的size 为 k
            pq.poll(); //删除左侧顶端的最大值，保持优先队列为最小的k个数字
        }

        //移动到下一层，继续添加
        if(root.left != null){
            findValue(pq, root.left, k);
        }

        if(root.right != null){
            findValue(pq, root.right, k);
        }
    }
}
