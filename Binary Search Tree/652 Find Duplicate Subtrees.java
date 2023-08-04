/*Leetcode 652. Find Duplicate Subtrees

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 

Constraints:

The number of the nodes in the tree will be in the range [1, 5000]
-200 <= Node.val <= 200
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
    //记录所有子树以及出现的次数 =》用来查找是否重复
    HashMap<String, Integer> map = new HashMap<>();

    //记录重复的子树 根节点 root
    List<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        findSubtree(root);
        return res;
    }

    //recursion function: to find the duplicate subtree
    private String findSubtree(TreeNode root){
        //exit condition, 不存在节点
        if(root == null){
            return "*";
        }

        //移动到子树组部分继续递归
        String left = findSubtree(root.left);
        String right = findSubtree(root.right);

        //把 子树部分 和 根节点  组成一个 String 字符串
        String subTree = left + "," + right + "," + root.val;

        //检查这个子树 出现的次数 ==》 是否重复
        int subTreeFrequence = map.getOrDefault(subTree, 0); //提取当前子树之前出现过的次数，如果没有就是0

        //如果出现次数大于等于1，表示这个子树是重复的
        //但因为重复的子树只需要记录一次，则只当第一次重复时，加入到结果集 res list
        if(subTreeFrequence == 1){ //把重复的子树加入到 res list
            res.add(root);
        }

        //记录当前子树，出现次数+1
        map.put(subTree, subTreeFrequence + 1);

        return subTree; //输出子树组成的字符串
    }
}
