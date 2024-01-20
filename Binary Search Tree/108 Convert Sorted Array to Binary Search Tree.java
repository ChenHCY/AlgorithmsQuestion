/* 108. Convert Sorted Array to Binary Search Tree
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }        
        
        return bst(nums, 0, nums.length - 1); // CALL Binary Search Tree Method
    }
    
    public TreeNode bst(int[] nums, int left, int right){ //Use the Binary Search Tree Method to cheack and find every step node 
        if(left > right){
            return null;
        }
        //Because the numbers list(Sorted Array) is in ascending order, so we can think the mid number in the list is the Root Tree Node.
        int mid = left + (right - left) / 2; 
        TreeNode node = new TreeNode(nums[mid]); //Start Build the TreeNode
        node.left = bst(nums, left, mid - 1); // same way to deal with Root Tree Node left
        node.right = bst(nums, mid + 1, right); //same way to deal with Root Tree Node right
        
        return node; // Last step: return and output TreeNode
    }
}
