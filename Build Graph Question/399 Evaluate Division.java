/* Leetcode 399. Evaluate Division

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] 
and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.


Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
*/

class Solution {
    //build a node graph, 来把除数和结果值 链接起来
    //定义邻接节点的信息，代表的num除数 和 需要的权重（最后的结果值）
    class Node{
        String num;
        double val;
        Node(String num, double val){
            this.num = num;
            this.val = val;
        }
    }

    //使用一个hashmap来储存每个字母(被除数)的关系方程式，所有的除数 和 产生的结果值
    HashMap<String, ArrayList<Node>> map = new HashMap<>();

    //Main function: 进行计算差找
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = queries.size();
        double[] res = new double[n]; //最后的结果值

        //把已知的方程式和价格都存入HashMap中
        for(int i = 0; i < equations.size(); i++){
            List<String> equat = equations.get(i); //提取除数和被除数
            String dividendNumber = equat.get(0); //被除数
            String divisionNumber = equat.get(1); //除数
            double currValue = values[i]; //当前这个方程式的结果

            //如果之前没有关于除数 和 被除数的关系式，则在Hashmap中创建一个新的映射空间
            if(!map.containsKey(dividendNumber)){
                map.put(dividendNumber, new ArrayList<>());
            }
            if(!map.containsKey(divisionNumber)){
                map.put(divisionNumber, new ArrayList<>());
            }

            //然后把对应的方程式和结果值 存入 HashMap对应的映射中
            map.get(dividendNumber).add(new Node(divisionNumber, currValue));
            map.get(divisionNumber).add(new Node(dividendNumber, 1 / currValue));
        }

        //然后遍历要求的计算方程式queries, 计算每个方程式的结果
        for(int i = 0; i < n; i++){
            //提取每个要计算方程式的：除数 和 被除数
            String num1 = queries.get(i).get(0); 
            String num2 = queries.get(i).get(1);
            res[i] = dfs(num1, num2, 1, new HashSet<>()); //call dfs function
        }

        return res;
    }

    //使用dfs进行查找路径，来得到每个方程式的结果
    //For example: (a / b) = 2;   (b / c) = 3 =>   (a / c) = (a / b) * (b / c) = 2 * 3 = 6
    private double dfs(String start, String end, double value, HashSet<String> visited){
        //fail case exit, 没有关于start这个数的方程式
        if(!map.containsKey(start)){
            return -1.0;
        }
        //如果之前已经使用过start这个数，代表形成了闭环，无法继续计算
        if(visited.contains(start)){
            return -1.0;
        }

        //success case, exit =>  除数（end）是永远不会发生改变的。
        //所以如果找到了方程式的要求除数，则代表查找成功，输出当前的结果
        if(start.equals(end)){
            return value;
        }

        visited.add(start); //记录当前的start节点（被除数）已经使用过

        //递归for-loop 遍历, 继续遍历被除数 已知的方程式，
        for(Node nextNumber : map.get(start)){
            // 除数（end）是永远不会发生改变的。
            double result = dfs(nextNumber.num, end, value * nextNumber.val, visited);
            //输出最后的结果值
            if(result != -1.0){
                return result;
            }
        }

        //回溯, 消除使用过的记录，如果没有查找到
        visited.remove(start); 
        return -1.0;
    }
}
