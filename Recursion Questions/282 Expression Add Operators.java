/* Leetcode 282. Expression Add Operators 

Given a string num that contains only digits and an integer target, return all possibilities to insert the 
binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.

Note that operands in the returned expressions should not contain leading zeros.

Example 1:
Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.

Example 2:
Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.

Example 3:
Input: num = "3456237490", target = 9191
Output: []
Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 

Constraints:
1 <= num.length <= 10
num consists of only digits.
-2^31 <= target <= 2^31 - 1
*/

class Solution {
    List<String> res;
    int n; // String num.length();
    int t; // the value of target;
    String s; // the String num;

    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        n = num.length();
        s = num;
        t = target;
        recursionHelper(0, "", 0, 0);
        return res;
    }

    //Recursion function: 递归找到每个数字之间的空隙，尝试加入+ - *; 最后找到总和等于target的所有公式
    public void recursionHelper(int index, String express, long currSum, long prev){
        //exit condition: 当遍历到字符串的最后一个数字，且这个公式的总和等于target
        if(index == n){
            if(currSum == t){
                res.add(express);
            }
        }

        for(int i = index; i < n; i++){
            //前导0的情况
            if(i != index && s.charAt(index) == '0'){
                break;
            }

            String currStr = s.substring(index, i + 1); //截取一段字符串区域
            Long currValue = Long.parseLong(currStr); //得到当前区域的数值

            //判断加入哪种符号
            if(index == 0){ //字符串开头, 无法加入任何符合
                recursionHelper(i + 1, currStr, currValue, currValue);
            } else{ //开始判断加入符号
                //+
                recursionHelper(i + 1, express + "+" + currValue, currSum + currValue, currValue);
                //-
                recursionHelper(i + 1, express + "-" + currValue, currSum - currValue, -currValue);
                //*
                recursionHelper(i + 1, express + "*" + currValue, currSum - prev + prev * currValue, prev * currValue);
            }
        }
    }
}
