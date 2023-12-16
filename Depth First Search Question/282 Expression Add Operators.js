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

/**
 * @param {string} num
 * @param {number} target
 * @return {string[]}
 */
var addOperators = function(num, target) {
    let res = [];

    function dfs(index, express, currValue, last){
        //exit condition: 检查是否遍历到字符串的最后一个数字
        if(index === num.length){
            if(currValue === target){ //检查当前公式的总和是否等于target
                res.push(express); //如果是的，加入到最后的结果
            }
        }

        for(let i = index; i < num.length; i++){
            if (i !== index && num[index] === '0'){
                break; // Skip leading zero number
            }
            
            const currNumStr = num.slice(index, i+1); //从当前位置开始切割字符串
            const currNum = parseInt(currNumStr, 10); //把切割的字符串，转化成一个十进制的数值

            if(index === 0){
                dfs(i + 1, currNumStr, currNum, currNum);
            } else{
                // + 
                dfs(i + 1, express + '+' + currNumStr, currValue + currNum, currNum);
                // - 
                dfs(i + 1, express + "-" + currNumStr, currValue - currNum, -currNum);
                // *: 先乘除后加减，所以当选择乘法时，需要首先减去前一个值，然后加入prev值和当前值的乘积
                dfs(i + 1, express + '*' + currNumStr, currValue - last + last * currNum,  last * currNum);
            }
        }
    };

    dfs(0, "", 0, 0);
    return res;
};
