/* 653. Two Sum IV - Input is a BST
Given the root of a Binary Search Tree and a target number k, return true if there 
exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: root = [5,3,6,2,4,null,7], k = 9
Output: true

Example 2:
Input: root = [5,3,6,2,4,null,7], k = 28
Output: false

Example 3:
Input: root = [2,1,3], k = 4
Output: true

Example 4:
Input: root = [2,1,3], k = 1
Output: false
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
    public boolean findTarget(TreeNode root, int k) {
        List<Integer>  result = new ArrayList();
        Inorder(root, result);
        
        int left = 0;
        int right = result.size() - 1;
        
        
        while(left < right){
            int sum = result.get(left) + result.get(right); // get the left+right total value
            
          //we use inorder traversal can get a sort list
            if(sum < k){
                left++;
            } else if (sum > k){
                right--;
            } else {
                return true;
            }
        }
        
        return false;
        
    }
    
    //follow the Binary tree, we should use Inorder traversal that can get a sort List
    public void Inorder(TreeNode root, List<Integer> list) {
        if(root != null){
            Inorder(root.left, list);
            list.add(root.val);
            Inorder(root.right, list);
        }
    }
}
