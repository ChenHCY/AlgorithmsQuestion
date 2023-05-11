/* Leetcoed 124 Binary Tree Maximum Path Sum

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 

A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:
The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
*/

/* 思路：此题思路是在一个Binary Search Tree上找到能产生的最大路径之和

因为找到这个Binary Search Tree的最大路径和，且不需要通过跟，也没有要求起点位置

所以我们可以自上而下的遍历每个节点，然后计算每个节点的最大贡献值

==》 因为路径是不能中断或者返回的，所以每个非空节点返还给父层级节点的贡献值，是当前节点值 + 左右子节点的最大值 ==> `node.val + Math.max(leftSum, rightSum);`
 ==> 空节点的最大贡献值等于 0
 ==> 非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和
 
具体而言，就是在以每个节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。 

然后有一个全局变量，来储存遍历买个节点能产生的最大路径总和值 ==》 包括当前节点的值与当前节点的左右子节点部分的最大贡献值
*/
//Time: O(n) 其中 N 是二叉树中的节点个数。对每个节点访问不超过 2 次
//Space: O(n) 要取决于递归调用层数，最大层数等于二叉树的高度
//==> 所以最坏情况下，二叉树的高度等于二叉树中的节点个数
class Solution {
    //因为找到这个Binary Search Tree的最大路径和，且不需要通过跟，也没有要求起点位置
    //该函数计算二叉树中的每一个节点的最大贡献值，
    //具体而言，就是在以每个节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        countSum(root);
        return maxSum;
    }

    //count the Max sum path 
    //自上而下的遍历，然后检查每个节点的左右子节点返还值
    public int countSum(TreeNode node){
        //exit condition, 当没有子节点时，返还0给父层级节点
        if(node == null){
            return 0;
        }

        //计算当前节点的左右节点区间的总和值，总和值不小于0
        int leftSum = Math.max(countSum(node.left), 0);
        int rightSum = Math.max(countSum(node.right), 0);

        //计算当前节点的路径总和值
        //包括当前节点的值与当前节点的左右子节点部分的最大贡献值
        int currSum = node.val + leftSum + rightSum;
        maxSum = Math.max(maxSum, currSum); //然后和maxSum比较 并且更新最大的路径和值

        //把当前节点的最大贡献值返还给父节点
        //因为路径是不能中断或者返回的，所以非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和
        int returnParentNode = node.val + Math.max(leftSum, rightSum);
        return returnParentNode;
    }
}
