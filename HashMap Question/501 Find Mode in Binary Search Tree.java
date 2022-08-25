/*Leetcode 501. Find Mode in Binary Search Tree
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
If the tree has more than one mode, return them in any order.
Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [1,null,2,2]
Output: [2]

Example 2:
Input: root = [0]
Output: [0]
 

Constraints:
The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
    Map<Integer, Integer> map; //<Key, Value>
    int max = 0;
    public int[] findMode(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        this.map = new HashMap<>(); //Used HashMap to count the most frequently occurred times 
        inOrder(root); //call the in-order function
        
        //Used arrayList to save all the most frequently occurred element
        List<Integer> list = new ArrayList<>(); 
        
        //Travser all the element from root tree through HashMap
        for(int key : map.keySet()){
            if(map.get(key) == max){ //if curr element occurred element equals max times
                list.add(key); //add curr element into ArrayList
            }
        }
        
        //change arraylist to be int[] list
        int[] res = new int[list.size()]; 
        for(int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }
        
        return res;
    }
    
    //In-order function: Use the In-order to add all the element into hashmap with occurred  times
    public void inOrder(TreeNode node){
        if(node.left != null){
            inOrder(node.left); //add node left side into HashMap
        }
        //Used HashMap to count the occurred times of every number elements
        map.put(node.val, map.getOrDefault(node.val, 0) + 1); 
        max = Math.max(max, map.get(node.val)); // find the most frequently occurred element
        if(node.right != null){
            inOrder(node.right);//add node right side into HashMap
        }
    }
}
