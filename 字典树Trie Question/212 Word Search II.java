/*Leetcode 212. Word Search II
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
*/

/* 此题是查找 words[]里面所有的字符串，有哪些是能在board上面查找到的

所以我们对于 words[]里面所有的String字符串 来建立字典树Tire，

然后DFS深度优先搜索 遍历board里面的每一个格子，检查是否存在于字典树中，然后向四个方向扩散能否到达字典树的底端 （形成需要的字符串）

==》最后StirngBuilder 记录每一个能到达底端的字符串String, 存入结果list

*/

class Solution {
    class TrieNode {
        boolean isWord = false; //表示这个结点是否为一个单词的结尾
        TrieNode[] next = new TrieNode[26]; // next[]表示这个结点的下一个26个字母节点对应是数字
    }

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //dfs的移动方向
    int m, n;
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
    
        TrieNode root = buildTrie(words); // 构建字典树
        HashSet<String> resSet = new HashSet<>(); //去重
        boolean[][] visited = new boolean[m][n]; // 用来记住这个字母是否已经使用过
        StringBuilder result = new StringBuilder(); // 记录沿途遍历到的元素

        //main-for loop, 从board的每个格子开始遍历
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dfs(board, resSet, result, i, j, root, visited);
            }
        }

        return new ArrayList<>(resSet);
    }

    //建立字典树Trie
    private TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for (String word : words) {
            char[] arr = word.toCharArray();
            TrieNode curr = root;
            for (char c : arr) {
                // 依次添加每个字母 => 找到每个字母的index位置
                // 如果当前字母的index位置 之前没有使用过, 把当前字母的index位置, 创建一个新的字典树节点
                if (curr.next[c - 'a'] == null) { //如果当前字母的index位置 之前没有使用过
                    curr.next[c - 'a'] = new TrieNode(); //把当前字母的index位置, 创建一个新的字典树节点
                }
                curr = curr.next[c - 'a']; //移动当前节点指针到下一层
            }
            curr.isWord = true; //结束，标记正确的形成了一个字符串
        }
        return root;
    }

    //the dfs function：检查能否在board上面找到words[]里面的string字符串
    private void dfs(char[][] board, Set<String> hashSet, StringBuilder sb, int x, int y, TrieNode node, boolean[][] visited){
        //exit condition, 越界，或者已经被使用过，或者这个节点代表的字母在 字典树Trie中 不存在
        if(x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || node.next[board[x][y] - 'a'] == null){
            return;
        }

        sb.append(board[x][y]); // 记录当前字符

        // Succes condition, 如果已经形成了一个字符串，则加入到结果list
        if (node.next[board[x][y] - 'a'].isWord) {
            hashSet.add(sb.toString());
        }

        visited[x][y] = true; //记录当前格子已经访问

        //继续遍历 =》 4个方向
        for (int[] dir : dirs) {
            dfs(board, hashSet, sb, x + dir[0], y + dir[1], node.next[board[x][y] - 'a'], visited);
        }

        visited[x][y] = false; //回溯还原
        sb.deleteCharAt(sb.length() - 1); // //回溯还原
    }
}
