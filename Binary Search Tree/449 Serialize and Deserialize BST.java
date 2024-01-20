/* 449. Serialize and Deserialize BST

Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or 
transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization 
algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized 
to the original tree structure.

The encoded string should be as compact as possible.

Example 1:
Input: root = [2,1,3]
Output: [2,1,3]

Example 2:
Input: root = []
Output: []
 

Constraints:
The number of nodes in the tree is in the range [0, 10^4].
0 <= Node.val <= 10^4
The input tree is guaranteed to be a binary search tree.
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

// The encoded string should be as compact as possible.
// 编码的字符串应尽可能紧凑。
public class Codec {

    // 序列化：把一个二叉树root转化为一个String的字符串，
    // Encodes a tree to a single string.
    // BFS Method 宽度优先搜索
    public String serialize(TreeNode root) {
        if (root == null){
            return "#";
        }

        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //宽度优先搜索whil-loop: 遍历每一个节点，加入到stringbuilder
        while(!queue.isEmpty()){
            //get every node from queue
            TreeNode node = queue.poll();

            //如果是null, 加入null
            if(node == null){
                sb.append("#,");
                continue; //跳过后续步骤，继续添加下一个
            }

            // add the node value into string
            sb.append(node.val + ",");
            // 把当前节点的左右子树 加入到队列中
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // 反序列化：把一个String 字符串转化为一个二叉树root, 所以也是BFS的变形。
    public TreeNode deserialize(String data) {
        //base case
        if(data == "#"){
            return null;
        }

        //change String data to be a string[] array
        //消除空格，然后把string改成array形式
        String[] str = data.split(","); 

        //create a root tree and add the first node value into root
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));

        //BFS method list，queue是用来遍历组装的
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //把root根节点加入到queue队列中，进行组装tree

        for(int i = 1; i < str.length; i++){
            TreeNode node = queue.poll(); //首先得到的就是根节点root, 然后一层一层组装

            //检查是否存在左子树
            if(!str[i].equals("#")){
                node.left = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(node.left);
            }

            i++;

            //检查是否存在右子树
            if(!str[i].equals("#")){
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
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
