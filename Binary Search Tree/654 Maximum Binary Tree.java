/*Leetcode 654. Maximum Binary Tree

You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

  1. Create a root node whose value is the maximum value in nums.
  2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
  3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.

Return the maximum binary tree built from nums.

Example 1:
Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.

Example 2:
Input: nums = [3,2,1]
Output: [3,null,2,null,1]
 

Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 1000
All integers in nums are unique.
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

//Time: O(n^2)  Space: O(1)
class Solution {
    //根据题意就是每次找到区域内的最大值为根节点root，然后最大值左边数字是左子树，右边是右子树，继续递归
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        return buildTree(nums, 0, n - 1);
    }

    //Small function to build the Binary Search Tree
    private TreeNode buildTree(int[] nums, int left, int right){
        //exit condition => 越界
        if(left > right){
            return null;
        }

        //find the maximum number in the range [left, right]
        int maxIndex = left;
        for(int i = left; i <= right; i++){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i; //找到最大值在nums中的index
            }
        }

        //建立root节点
        TreeNode root = new TreeNode(nums[maxIndex]);

        //继续递归建立root节点子树部分
        root.left = buildTree(nums, left, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, right);

        return root;
    }
}
