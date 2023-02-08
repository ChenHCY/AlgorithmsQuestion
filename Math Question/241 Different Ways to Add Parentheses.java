/* Leetcode 241. Different Ways to Add Parentheses

Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group 
numbers and operators. You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.

Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0 
(2-(1-1)) = 2

Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
 

Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
*/

/* 思路： Divide Conquer algorithm 分治 + recursion 递归

这是一道非常经典的分支题目，题意是考虑括号的位置，找出和计算所有放入一个括号之后得到的数值

类似于一个二叉树的形式，每当遇到一个符号的时候，就进行分解 Divide  同时递归

==》 if(c == '+' || c == '*' || c == '-') ==》 

==》 List<Integer> leftP = diffWaysToCompute(expression.substring(0, i));//开头到符号左边
List<Integer> rightP= diffWaysToCompute(expression.substring(i + 1));//符号右边的全部

这样可以把一个长式子，不停的划分为短的，

然后再一个一个计算， 计算完左右两部分后，从左右两部分取出number, 进行Conquer(治疗)

for(int leftNumber : leftP){ for(int rightNumber : rightP){}}

==> 每次取出一个数，再根据划分时的符号 进行计算 ==》最后得到一个结果，存入list中。

当遍历完成 就能得到所有加入一个括号之后的总值
*/
//Time: O(nlogn)  Space: O(n)
class Solution {
    //Divide Conquer algorithm + recursion
    public List<Integer> diffWaysToCompute(String expression) {
        //base case
        if(expression == null || expression.length() == 0){
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        //travser all the element from expression
        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            //当遇到任意符号的时候停止 ==> 开始进行divide（分开）
            if(c == '+' || c == '*' || c == '-'){
                List<Integer> leftP = diffWaysToCompute(expression.substring(0, i)); //开头到符号左边
                List<Integer> rightP= diffWaysToCompute(expression.substring(i + 1));//符号右边的全部

                 //计算完左右两部分后，从左右两部分取出number, 进行Conquer(治疗)
                for(int leftNumber : leftP){
                    for(int rightNumber : rightP){
                        //计算两边的值 和 中间的符号 ==》得到一个总的结果 然后存入res的list中
                        if(c == '+'){ //符号为 '+'
                            res.add(leftNumber + rightNumber);
                        }else if(c == '-'){ //符号 '-'
                            res.add(leftNumber - rightNumber);
                        }else if(c == '*'){ //符号 '*'
                            res.add(leftNumber * rightNumber);
                        }
                    }
                }
            }
        }

        //如果字符串中没有任何的符号存在，直接存入字符串的integer值
        if(res.size() == 0){
            res.add(Integer.valueOf(expression));
        }

        return res;
    }
}
