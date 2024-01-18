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
Solution 1: Used BFS
Time: O(n)  Space: O(n)
Thought: According to the image, the returned output is the rightmost node of each row
so it is the last one of each row
==> Used BFS to find every row nodes, and for-loop if-statement add the last one node into res list
PS: BFS, instead of saving all the numbers in each level, save the last one.
*/
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res; // 如果root为空，输出empty res list
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //BFS主遍历
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll(); //提取当前节点

                //如果当前节点是这个层级的最右边的，加入到结果list
                if(i == size - 1){
                    res.add(node.val);
                }

                //检查是否有左右子树
                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }

        return res;
    }
}


// Solution 2: Used DFS
// Time: O(n)  Space: O(n)
class Solution {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    //dfs function: 按照 根节点 -> 右子树 -> 左子树的方式加入
    //如果depth深度 == res.size, 表示这是这层加入的第一个节点，也就是最右边的节点
    public void dfs(TreeNode node, int depth){
       //如果不存在当前节点，直接返回
        if(node == null){
            return;
        }
        //如果depth深度 == res.size, 表示这是这层加入的第一个节点，也就是最右边的节点
        if(depth == res.size()){
            res.add(node.val);
        }
        //继续递归遍历，左右子树
        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
    }
}
