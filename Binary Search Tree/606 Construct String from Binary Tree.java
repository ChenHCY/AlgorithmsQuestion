/* Leetcode Construct String from Binary Tree

Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder traversal way, and return it.

Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: root = [1,2,3,4]
Output: "1(2(4))(3)"
Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)"

Example 2:
Input: root = [1,2,3,null,4]
Output: "1(2()(4))(3)"
Explanation: Almost the same as the first example, except we cannot omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 

Constraints:
The number of nodes in the tree is in the range [1, 10^4].
-1000 <= Node.val <= 1000
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
    public String tree2str(TreeNode root) {
        StringBuilder res = new StringBuilder();
        dfs(root, res);
        return res.toString();    
    }

    public static void dfs(TreeNode node, StringBuilder str){
        //exit case
        if(node == null){
            return;
        }
        //根节点部分
        str.append(String.valueOf(node.val));
        if(node.left == null && node.right == null){
            return;
        }
        
        //遍历左子树部分
        str.append("(");
        dfs(node.left, str);
        str.append(")");

        //判断右子树
        if(node.right != null){
            str.append("(");
            dfs(node.right, str);
            str.append(")");
        }
    }
}
