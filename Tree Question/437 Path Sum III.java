/* Leetcode 437. Path Sum III

Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

Example 1:

Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.

Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3
 

Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
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

//Solution 1: 深度优先搜索 + 枚举法
//==》我们访问每一个节点，枚举以每个节点为起始点，然后进行深度搜索，统计有多少条部分路径的总和是等于targetSum的
//Time: O(n^2)   Space: O(n)
class Solution {
    //Example里存在null节点，targetSum = 0的情况，所以不能设定null值等于0，也能设定dfs function()返回一个int值
    int res = 0;  //统计符合要求的路径数量
    
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null){
            return 0;
        }
        
        //首先统计以根节点为起点的情况下，有多少部分子节点的值是targetSum
        dfsCountSum(root, targetSum);

        //然后统计以根节点的左右子节点为起点的情况下，有多少部分子节点的值是targetSum
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return res;
    }

    //dfs function
    public void dfsCountSum(TreeNode currRoot, long targetSum){
        //exit condition
        //如果当前节点不存在，直接返回0，表示这条路径上没有任何一部份的值可以等于targetSum
        if(currRoot == null){
            return;
        }

        //得到当前节点的值
        int currNodeValue = currRoot.val;
        
        //count and add the number of result paths
        //如果当前的节点值等于targetSum剩余的值，代表这条路径上一定有一部分的和为targetSum
        //所以 resultPaths + 1
        if(currNodeValue == targetSum){
            res++;
        }

        //然后继续递归遍历当前节点的左右子节点
        dfsCountSum(currRoot.left, targetSum - currNodeValue); // traverse the left sub-tree
        dfsCountSum(currRoot.right, targetSum - currNodeValue); // traverse the right sub-tree
    }
}
 
//Solution 2: DFS + 前缀和 HashMap() ==> 优化时间复杂度
//我们将前缀和定义为：由根结点到当前结点的路径上所有节点的和，
//如果此前有和为currSum(前缀和)-target的部分路径,而当前的和又为currSum,两者的差就肯定为target了

//简单来说，就是如果存在currSum(前缀和)-target的路径，表示此条路径的和一定为target, 要统计到path中
//==》然后Hashmap中储存了这些前缀和-target的差值和出现的次数，这样可以更加节约时间复杂度的统计符合要求的路径数量
//==》由于这是一个树问题，您需要在探索子树后减少 prefixSum 的频率

//Time: O(n)  N为二叉树中节点的个数, 利用前缀和只需遍历一次二叉树即可。  
//Space: O(1)
class Solution {
    int resPaths;
    int target;
    HashMap<Long, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;

        //因为可能存在单个节点的值等于targetSum值，所以要当sum值为0的时候，也算1条路径
        map.put(0l, 1);

        countPathSum(root, 0); //call the dfs function
        return resPaths;
    }

    //dfs function
    public void countPathSum(TreeNode node, long preFixSum){
        //exit condition
        if(node == null){
            return;
        }

        //count the prefixsum of the treeNodes
        preFixSum += node.val;

        //找到sum = prefixSum - target 的子数组的数量，这些就是能和当前节点组成总和为targetSum的路径数量
        resPaths += map.getOrDefault(preFixSum - target, 0);

        //add the curr prefixsum value into hashmap
        //and count the frequency of curr Prefixsum 
        map.put(preFixSum, map.getOrDefault(preFixSum, 0) + 1);

        //continue move to next level of curr node, including node.left and node.right
        countPathSum(node.left, preFixSum);
        countPathSum(node.right, preFixSum);

        // Remove the current prefixsum from the path to backtrack
        // We must remove the current node with prefixsum while going up the recursive call stack. 
        map.put(preFixSum, map.get(preFixSum) - 1);
        if(map.get(preFixSum) == 0){
            map.remove(preFixSum); //backtracking, 回溯到上一层节点时，必须要删除当前的前缀和值
        }
    }
}
