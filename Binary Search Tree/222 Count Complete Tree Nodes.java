/*Leetcode 222. Count Complete Tree Nodes

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 6

Example 2:

Input: root = []
Output: 0

Example 3:

Input: root = [1]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [0, 5 * 10^4].
0 <= Node.val <= 5 * 10^4
The tree is guaranteed to be complete.
*/

// 计算一个 Complete Tree 完全二叉树的节点nodes 数量
//  Complete Tree 完全二叉树: 每一层都是紧凑靠左排列的，也就是可能是左右节点都有，或者只有左节点

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

//My Solution: 使用BFS对于二叉树进行分层遍历，计算每一个层级的节点数量，然后添加到一起，得到二叉树总的节点数量 
//Time：O（n)   Space: O(n)
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }

        Queue<TreeNode> pq = new LinkedList<>();
        pq.offer(root);
        int res = 0;

        while(!pq.isEmpty()){
            int size = pq.size();
            int levelNode = 0;
            
            for(int i = 0; i < size; i++){
                TreeNode node = pq.poll(); //提取一个节点node
                levelNode += 1; //计算这个层级的节点数量

                if(node.left != null){
                    pq.offer(node.left);
                }  

                if(node.right != null){
                    pq.offer(node.right);
                }   
            }
            res += levelNode; //把每个层级的节点数量加入到总和
        }

        return res;
    }
}


//The Best Time Solution: 使用 Complete Tree 完全二叉树的特性

//首先统计这个二叉树的左右子树高度
//如果左右子树的高度层级相同，表示这是一个 Perfect Binary Tree 满二叉树 所有节点位置都是满的 =》 节点总数就是 2^h - 1
//如果左右子树的高度层级不相同，表示有节点位置是空的，使用普通的二叉树统计节点数量的方法 =》 递归： countNodes(root.left) + countNodes(root.right) + 1

//Time：O（logN*logN)   =》 这两个递归只有一个会真的递归下去，另一个一定会触发 hl == hr 而立即返回，不会递归下去。
// =》因为一棵 Complete Tree 完全二叉树 的两棵子树，至少有一棵是 Perfect Binary Tree 满二叉树 （ 所有节点位置都是满的） => 所以这两个递归只有一个会真的递归下去

// Space: O(n)，申明了两个新的二叉树
lass Solution {
    public int countNodes(TreeNode root) {
        //base case
        if(root == null){
            return 0;
        }

        //申明两个新的TreeNode, 来计算左右子树的高度
        //避免root的起始点发生改变，影响后面如果左右子树不一样高度的节点数量计算
        TreeNode leftTree = root;
        TreeNode rightTree = root;

        //分别统计左右子树的高度
        int leftHigh = 0;
        while(leftTree != null){
            leftTree = leftTree.left; //移动到当前下一个层级的左子树
            leftHigh += 1; //计算高度
        }

        int rightHigh = 0;
        while(rightTree != null){
            rightTree = rightTree.right; //移动到当前下一个层级的右子树
            rightHigh += 1; //计算高度
        }

        //如果左右子树的高度层级相同，表示这是一个 Perfect Binary Tree 完美二叉树 所以节点位置都是满的 
        //=》 节点总数就是 2^h - 1
        if(leftHigh == rightHigh){
            return (int)Math.pow(2, leftHigh) - 1;
        } 

        //如果左右子树的高度层级不相同，表示有节点位置是空的，使用普通的二叉树统计节点数量的方法
        //递归：countNodes(root.left) + countNodes(root.right) + 1
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
