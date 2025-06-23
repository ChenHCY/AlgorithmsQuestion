/* Leetcode 79. Word Search
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells 
are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
*/
//Time: Time Complexity: O(m * n * 4L) m is the row length, n is the column length, L is 4个方向的搜索
// 每次递归最多尝试 4 个方向，并且最多递归 L 层（offset === word.length 是终止条件）。
// 因为每个起点都可能展开一棵 4 分支、深度为 L 的搜索树。
//Space: O(L) where L is the length of the word to be matched.
// 每次搜索最深是 L 层，因此递归栈最多消耗 O(L) 空间。

// 问题1: 为什么 Java 的解法中用了 boolean[][] visited 来记录访问状态，而有些 JavaScript 解法却没有用？
// 最核心的原因是：所有合法的搜索路径中，不能重复使用同一个格子（即同一个单元格在一次 word 搜索中不能用两次）。而你如果不使用 visited 数组，就必须通过其他方法来确保这一点。这两者是等价的。
/* Java: 
在 Java 中，字符串（char[][] board）是不可变的，如果你不想改变 board 的内容，就得额外用一个数组来标记哪些格子访问过了（visited）。
这是因为：
Java 中数组是引用类型。
为了保证回溯后能还原现场（即“撤销选择”），你不能直接在 board 上做修改。
所以只能额外创建一个 visited[][] 数组。
*/

/* JS: 
通过把 board[i][j] 改成一个不可能出现在 word 中的字符（比如 '#'），来模拟“访问过”的状态，然后再回溯回来。
*/

// 问题2: JS 的闭包机制 可以让 JS 不必传入 board 和 word (最好还是要传）
/*
这是因为在 JavaScript 中，函数是闭包（closure），它可以访问其外部作用域（lexical scope）中的变量，即使这些变量不是作为参数传进去的。

虽然 backtracking 的参数里没有 board 和 word，但它在 exist 函数内部，所以它可以“看到” exist 函数作用域中的 board 和 word，即可以直接访问。

这就是 JavaScript 的闭包特性 —— 一个函数可以使用它定义时的作用域中的变量，即使这些变量不是传进来的。

==》 因为 Java 没有函数作用域级别的闭包。所以 Java 必须传入到 backtracking
*/

/**
 * @param {character[][]} board
 * @param {string} word
 * @return {boolean}
 */
var exist = function(board, word) {
    const m = board.length;
    const n = board[0].length;

    const backtracking = (i, j, offset) => {
        // if current board cell == word character
        if(offset === word.length){
            return true;
        }

        // if outside or not equal current character
        if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] !== word[offset]){
            return false;
        }

        // mark current cell was used
        const temp = board[i][j];
        board[i][j] = "#0";

        const found = backtracking(i + 1, j, offset + 1) || backtracking(i - 1, j, offset + 1) || backtracking(i, j + 1, offset + 1) 
            || backtracking(i, j - 1, offset + 1);

        board[i][j] = temp; // back to pervious status, not used it

        return found;
    }


    // travser all the cell and find it
    for(let i = 0; i < m; i++){
        for(let j = 0; j < n; j++){
            if(backtracking(i, j, 0)){
                return true;
            }
        }
    }

    return false;
};
