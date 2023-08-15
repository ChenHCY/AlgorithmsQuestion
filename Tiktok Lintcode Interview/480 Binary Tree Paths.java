/*Lintcode 480 · Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.

Example 1:

Input：{1,2,3,#,5}
Output：["1->2->5","1->3"]
Explanation：
   1
 /   \
2     3
 \
  5

Example 2:

Input：{1,2}
Output：["1->2"]
Explanation：
   1
 /   
2     
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     *          we will sort your return value in output
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> res = new ArrayList<>();
        buildPath(root, res, "");
        return res;
    }

    private void buildPath(TreeNode root, List<String> res, String path){
        //if root is null, return and break
        if(root == null){
            return;
        }

        if(root.left == null && root.right == null){
            res.add(path + root.val);
            return; //跳过后续步骤
        }

        //递归遍历移动到下一个层级子树
        buildPath(root.left, res, path + root.val + "->");
         buildPath(root.right, res, path + root.val + "->");
    }
}
