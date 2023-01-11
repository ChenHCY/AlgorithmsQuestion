/* Leetcode 297. Serialize and Deserialize Binary Tree
Serialization is the process of converting a data structure or object into a sequence of bits so that it can 
be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later 
in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization 
algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized 
to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily 
need to follow this format, so please be creative and come up with different approaches yourself.

Example 1:
Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Example 2:
Input: root = []
Output: []
 
Constraints:
The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
*/

/*解题思路
序列化：把一个二叉树root转化为一个String的字符串，所以需要使用BFS的变形。把每一层加入Dueue list中，然后再放入StringBuilder中。

反序列化：把一个String 字符串转化为一个二叉树root, 所以也是BFS的变形。
    1. String[] str = data.split(" "); 把String变为一个String Array
    2. for-loop循环String[] Array所有值，然后BFS一层一层的加入到root 二叉树里面对应的点位
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    //BFS Method
    public String serialize(TreeNode root) {
        //Use StringBuilder to serialize root to be String format
        StringBuilder sb = new StringBuilder();

        if(root == null){
            return "";
        }
        //BFS method list
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            //get the first node from Queue list
            TreeNode node = queue.poll();

            //if there is no more node
            if(node == null){
                sb.append("null ");
                continue;
            }

            //add the node value into string
            sb.append(node.val + " ");

            //move to next level
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //base case
        if(data == null || data.length() == 0){
            return null;
        }

        //change String data to be a string[] array
        String[] str = data.split(" ");

        //create a root tree and add the first node value into root
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        //BFS method list
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //for-loop travser all the element from String data
        for(int i = 1; i < str.length; i++){
            //get the first node from Queue list
            TreeNode node = queue.poll();
            //if there can correct find node value

            if(!str[i].equals("null")){
                //add the value into left node of current node
                node.left = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(node.left);
            }

            i++; //move to check right side

            if(!str[i].equals("null")){
                //add the value into left node of current node
                node.right = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(node.right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
 
