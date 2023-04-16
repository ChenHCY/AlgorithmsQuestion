/*(Biweekly Contest 102)  Leetcode 2641. Cousins in Binary Tree II

Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.

Example 1:
Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

Example 2:
Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 104
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

/* 思路： 此题是更改所有节点的值为其表兄弟节点的值，如果不存在表兄弟节点，则值改为0

所以 ==》 层级总和 - 兄弟节点的总和 = 表节点的值 ==》 用总的层级和 减去 与当前节点具有相同父节点的节点总和 就是这个一部分的表兄弟节点值的总和

所以我们使用BFS进行层序遍历，第一个for-loop求下一层的和和检查下一层是否存在左右节点，第二个for-loop同来更新下一层节点的值 为 其表兄弟节点值的总和

*/
class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //改顶节点的值
        if(root != null){
            root.val = 0;
        }

        while(!queue.isEmpty()){
            int nextLevelSum = 0;
            Queue<TreeNode> currLevel = queue; //当前层级的节点
            queue = new LinkedList<TreeNode>();
            //检查是否存在左节点和右节点，然后把下一个层级的左右节点加入到queue中，
            //并且计算下一个层级的总和值
            for(TreeNode node : currLevel){
                if(node.left != null){
                    nextLevelSum += node.left.val;
                    queue.offer(node.left);
                }

                if(node.right != null){
                    nextLevelSum += node.right.val;
                    queue.offer(node.right);
                }
            }

            //然后更改当前层级的值
            //层级总和 - 兄弟节点的总和 = 表节点的值
            for(TreeNode currNode : currLevel){
                //计算拥有相同父节点的总和sum
                int parentSum = (currNode.left != null ? currNode.left.val : 0) + (currNode.right != null ? currNode.right.val : 0);
                if(currNode.left != null){
                    currNode.left.val = nextLevelSum - parentSum;
                }
                if(currNode.right != null){
                    currNode.right.val = nextLevelSum - parentSum;
                }

            }
        }

        return root;
    }
}
