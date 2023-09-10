/*Leetcode (Contest 362) 2850. Minimum Moves to Spread Stones Over Grid

You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell. 
The grid contains exactly 9 stones, and there can be multiple stones in a single cell.

In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.

Return the minimum number of moves required to place one stone in each cell.

Example 1: 
Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
Output: 3
Explanation: One possible sequence of moves to place one stone in each cell is: 
1- Move one stone from cell (2,1) to cell (2,2).
2- Move one stone from cell (2,2) to cell (1,2).
3- Move one stone from cell (1,2) to cell (0,2).
In total, it takes 3 moves to place one stone in each cell of the grid.
It can be shown that 3 is the minimum number of moves required to place one stone in each cell.

Example 2:
Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
Output: 4
Explanation: One possible sequence of moves to place one stone in each cell is:
1- Move one stone from cell (0,1) to cell (0,2).
2- Move one stone from cell (0,1) to cell (1,1).
3- Move one stone from cell (2,2) to cell (1,2).
4- Move one stone from cell (2,2) to cell (2,1).
In total, it takes 4 moves to place one stone in each cell of the grid.
It can be shown that 4 is the minimum number of moves required to place one stone in each cell.
 

Constraints:
grid.length == grid[i].length == 3
0 <= grid[i][j] <= 9
Sum of grid is equal to 9.
*/

//迭代写法
//Time: O(3 ^ (n^2))
//Space: O(n)
class Solution {
    public int minimumMoves(int[][] grid) {
        int res = Integer.MAX_VALUE;
        int count = 0; //记录grid里面没有石头的格子数量

        //开始统计grid里面没有石头的格子数量
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == 0){
                    count++;
                }
            }
        }

        //the exit case, 如果没有了石头数量为0的格子了，不需要继续移动
        if(count == 0){
            return 0;
        }

        //主遍历：遍历整个grid 查找得到石头数量为0的格子
        //然后再次遍历查找石头数量大于1的格子
        // ==> 使用曼哈顿公式，计算需要移动步数
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == 0){
                    for(int x = 0; x < 3; x++){
                        for(int y = 0; y < 3; y++){
                            if(grid[x][y] > 1){
                                int dis = Math.abs(i - x) + Math.abs(j - y);
                                grid[x][y]--;
                                grid[i][j] = 1;
                                res = Math.min(res, dis + minimumMoves(grid));
                                grid[x][y]++;
                                grid[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}


//递归写法
//Time: O(3 ^ (n^2))
//Space: O(n)
class Solution {
    int res = Integer.MAX_VALUE; //最小值 => 需要移动的最小步数
    int[][] gridMatrix; //因为后面我们要对于grid发生更改，根据clouser闭包规则 所以要创建一个新的grid写在外面
    public int minimumMoves(int[][] grid) {
        this.gridMatrix = grid; //给写在外面的grid总值赋值
        int count = 0; //记录grid里面没有石头的格子数量
        //开始统计grid里面没有石头的格子数量
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == 0){
                    count++;
                }
            }
        }

        dfs(0, count); //调用dfs function
        return res;
    }

    //sum 记录移动方案对应的移动次数
    //cnt 记录剩余石头数为0的格子数
    private void dfs(int sum, int cnt){
        //exit case: 剩余石头数位0的格子 没有了
        if(cnt == 0){
            res = Math.min(res, sum); //比较取的最小值
        }
        ////遍历所有grid格子，当找到石头数量为0的格子时, 计算移动石头的距离
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                //遍历所有grid格子，当找到石头数量为0的格子时
                if(gridMatrix[i][j] == 0){
                    //然后开始遍历查找石头数量大于1的格子
                    //使用Manhattan Distance公式，计算从那个格子移动到之前石头数量为0的格子 需要的步数
                    for(int x = 0; x < 3; x++){
                        for(int y = 0; y < 3; y++){
                             //因为每个格子都要有石头，所以要找到了拥有石头数量大于1的格子给没石头的格子移动
                            if(gridMatrix[x][y] > 1){
                                //更改格子的石头的数量
                                gridMatrix[x][y]--;
                                gridMatrix[i][j] = 1;

                                //使用Manhattan Distance公式，计算移动产生的步数
                                int dis = Math.abs(i - x) + Math.abs(j - y);

                                dfs(sum + dis, cnt-1); //递归到下一层，直到不存在石头数量为0的格子
                                //递归结束，回溯back-tracking到之前格子的石头数量状态
                                gridMatrix[x][y]++;
                                gridMatrix[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
}
