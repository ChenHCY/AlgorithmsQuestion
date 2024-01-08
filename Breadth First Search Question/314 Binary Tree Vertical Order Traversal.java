/* 314. Binary Tree Vertical Order Traversal

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]

Example 2:
Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:
Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 
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
//Time: O(n + m) n表示二叉树的节点数量，m表示二叉树的边数
//Space: O(3n) 开了一个hashmap 一个treemap 一个Queue进行储存和运算
class Solution {
    //the vertical order traversal: 从左往右
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        //使用TreeMap储存每个垂直层级里面的node, 怎样可以根据key值(node的垂直层级)进行排序
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        //使用hashmap储存每个节点 和 对应的 index位置
        HashMap<TreeNode, Integer> nodeMap = new HashMap<>();
        nodeMap.put(root, 0);

        //使用BFS来层次遍历二叉树
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 主遍历
        while(!queue.isEmpty()){
            TreeNode currNode = queue.poll(); //拿到当前节点
            int index = nodeMap.get(currNode); //当前节点的位置

            if(!map.containsKey(index)){
                map.put(index, new ArrayList<>());
            }
            map.get(index).add(currNode.val);

            //遍历加入左子树节点
            if(currNode.left != null){
                queue.offer(currNode.left);
                nodeMap.put(currNode.left, index - 1); //左子树层级index-1
            }

            //遍历加入右子树节点
            if(currNode.right != null){
                queue.offer(currNode.right);
                nodeMap.put(currNode.right, index + 1); //右子树层级index+1
            }
        }

        return new ArrayList<>(map.values());
    }
}
