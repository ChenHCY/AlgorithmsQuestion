/* Leetcode 2718. Sum of Matrix After Queries

You are given an integer n and a 0-indexed 2D array queries where queries[i] = [typei, indexi, vali].

Initially, there is a 0-indexed n x n matrix filled with 0's. For each query, you must apply one of the following changes:

    · if typei == 0, set the values in the row with indexi to vali, overwriting any previous values.
    · if typei == 1, set the values in the column with indexi to vali, overwriting any previous values.
    
Return the sum of integers in the matrix after all queries are applied.

Example 1:

Input: n = 3, queries = [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]
Output: 23
Explanation: The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 23. 

Example 2:

Input: n = 3, queries = [[0,0,4],[0,1,2],[1,0,1],[0,2,3],[1,2,1]]
Output: 17
Explanation: The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 17.

Constraints:

1 <= n <= 104
1 <= queries.length <= 5 * 104
queries[i].length == 3
0 <= typei <= 1
0 <= indexi < n
0 <= vali <= 105
*/

/* 此题思路是根据 queries[i] = [type, index, val] 的类型type, index value（位置 行数 OR 列数），来改变matrix中每个格子的值

 Beacuse it was overwriting any previous values ==> 覆盖任何以前的值 ==？ 所以我们应该从后面反向计算，如果遇到已经处理过的值，则不再改变
 
 type = 0, set the row values, type = 1 set the column value
*/

class Solution {
    public long matrixSumQueries(int n, int[][] queries) {
        //n is length of queries[][]
        long res = 0;
        int m = queries.length;
        HashSet<Integer> rowSet = new HashSet<>(); //记录哪些row被处理过
        HashSet<Integer> columnSet = new HashSet<>(); //记录哪些column被处理过
        
      //倒序遍历
        for(int i = m - 1; i >= 0; i--){
            //两种类型： 1. type = 0, set the rows value
            //检查当前行是否已经被处理过
            if(queries[i][0] == 0 && !rowSet.contains(queries[i][1])){
                //计算总和：剩余未被处理过的格子数量 * 要求覆盖的新值 queries[i][2]
                res += (n - columnSet.size()) * queries[i][2]; // n is length of queries[][]
                //把这个处理过的列 加入到rowSet中, 标记已经处理完成
                rowSet.add(queries[i][1]);
            }
            
            //两种类型： 1. type = 1, set the column value
            //检查当前列是否已经被处理过
            if(queries[i][0] == 1 && !columnSet.contains(queries[i][1])){
                //计算总和：剩余未被处理过的格子数量 * 要求覆盖的新值 queries[i][2]
                res += (n - rowSet.size()) * queries[i][2]; //n is length of queries[][]
                //把这个处理过的列 加入到columnSet中， 标记已经处理完成
                columnSet.add(queries[i][1]);
            }
        }

        return res;
    }
}
