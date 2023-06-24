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

Time Limit Exceeded  => 62 / 65 test cases passed

class Solution {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //dfs的移动方向
    int m; // the board.length
    int n; // the board[0].length

    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;

        List<String> res = new ArrayList<>(); //output result
        int[] boardFreq = new int[26]; //统计board里面每个字母的数量，=》用来进行剪枝预处理

        //1. 使用buckSort 统计board里面每个字母出现的次数
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                boardFreq[board[i][j] - 'a']++; //从0-25，对应a-z每个字母的frequnece
            }
        }

        //2. 查找words每个string，是否能在board里面找到组成
        //里面有个预处理：如果words中一个String的任意一个字母个数，超过在board上的数量，则表示无法通过board组成这个String字符串
        for(String word : words){
            boolean canFormWord = true; // Variable to track if the word can be formed using the board
            int[] wordFreq = new int[26]; //words[]里面每个字符串的 字母组成
             boolean[][] visited = new boolean[m][n]; // 用来记住这个字母是否已经使用过
    
            //统计words[]里面每个字符串的 所有字母的出现次数
            for(char c : word.toCharArray()){
                wordFreq[c - 'a']++;
            }
            //检查String word里面的字母出现的数量 和 board里面字母出现的数量 是否能构成
            for(int i = 0; i < 26; i++){
                //如果words中一个String的任意一个字母个数，超过在board上的数量，则表示无法通过board组成这个String字符串
                //=> 跳出循环，不再继续执行后续
                if(boardFreq[i] < wordFreq[i]){
                    canFormWord = false;
                    break;
                }
            }

            if(!canFormWord){
                continue;
            }
            
            //3. 最后开始在board进行查找 剩余“有可能” 构成的 String word
            boolean find = false;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(dfs(board, word, 0, i, j, visited)){ //成功找到
                        find = true;
                        res.add(word); //把这个string word 加入到最后结果中
                        break;
                    }
                }
                if(find){
                    break;
                }
            }
        }

        return res;
    }

    //the dfs function：检查能否在board上面找到words[]里面的string字符串
    private boolean dfs(char[][] board, String wordStr, int index, int x, int y, boolean[][] visited){
         //success exit, and condition
        if(index == wordStr.length()){
            return true;
        }

        //exit condition, 越界
        if(x < 0 || y < 0 || x >= m || y >= n || visited[x][y]){
            return false;
        }

        if(board[x][y] == wordStr.charAt(index)){
            visited[x][y] = true;
            //继续遍历 =》 4个反向
            for(int[] dir : dirs){
                if(dfs(board, wordStr, index + 1, x + dir[0], y + dir[1], visited)){
                    visited[x][y] = false; // Unmark current cell before backtracking
                    return true;
                }
            }
            visited[x][y] = false;
        }

        return false;
    }
}
