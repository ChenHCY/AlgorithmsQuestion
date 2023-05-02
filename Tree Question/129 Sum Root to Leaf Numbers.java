/* Leetcode 129. Sum Root to Leaf Numbers

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:

Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:

Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
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

//Time: O(n)   Space: O(1)
class Solution {
    public int sumNumbers(TreeNode root) {
        int res = dfsCountSum(root, 0);
        return res;
    }

    //the dfs function
    public int dfsCountSum(TreeNode root, int sum){
        //the exit condition
        if(root == null){
            return 0;
        }
        //如果当前root值不等于null, 把总值*10后加入当前root的值
        sum = sum * 10 + root.val;

        //然后继续递归变量当前节点的左右子节点，不停的重复29行的，直到到最底端
        int left = dfsCountSum(root.left, sum);
        int right = dfsCountSum(root.right, sum);
        
        //如果当前节点没有任何子节点，表示这个节点的left+right=0, ==》return输出sum
        //如果当前节点存在子节点，表示这个节点的left+sum != 0 ==》return输出新的总值
        return (left + right) == 0? sum : left + right; 
    }
}
