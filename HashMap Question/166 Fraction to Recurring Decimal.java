/* 166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.


Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 4, denominator = 333
Output: "0.(012)"
 

Constraints:
-2^31 <= numerator, denominator <= 2^31 - 1
denominator != 0
*/

class Solution {
    // 使用HashMap记录小数的循环的部分和index位置，如果再次遇见相同的余数，则表示出现循环的部分 ==> 在对应的index位置加入括号
    public String fractionToDecimal(int numerator, int denominator) {
        //如果分子为0，结果肯定为0
        if(numerator == 0){
            return "0";
        }

        StringBuilder res = new StringBuilder();
        // XOR 符号 "^": 相同的值的话 输出为0(false)，不同的值的话 输出为1(true)
        String sign = (numerator > 0) ^ (denominator > 0) ? "-" : "";
        res.append(sign); //把符合加入到结果字符串

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        res.append(num / den);  // 首先计算整数部分和判断能否完成整除
        num %= den; //计算得到余数部分

        if(num == 0){ //如果没有余数，表示可以整除 => 输出结果
            return res.toString(); 
        }

        //如果不能整除，开始计算小数部分
        res.append(".");

        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, res.length()); //把余数加入HashMap

        //开始计算余数，如果余数0，表示得到整除，停止循环
        while(num != 0){
            num *= 10;
            res.append(num / den); //计算小数部分的除法结果, 然后加入到结果字符串
            num %= den; //继续这次除法之后的余数
            if(map.containsKey(num)){ //如果再次遇见相同余数，表示找到了循环部分 ==> 加入括号
                int addIndex = map.get(num); //加入左括号的位置
                res.insert(addIndex, "("); //在指定的位置 加入左括号
                res.append(")"); //字符串的最后面加入右括号
                break; 
            } else{ //如果是不相同的，计数加入到HashMap
                map.put(num, res.length());
            }
        }

        return res.toString(); //输出最后的除法结果字符串
    }
}
