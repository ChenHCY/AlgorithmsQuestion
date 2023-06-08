/* Leetcode 402. Remove K Digits

Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 10^5
num consists of only digits.
num does not have any leading zeros except for the zero itself.
*/

class Solution {
    //单调栈
    public String removeKdigits(String num, int k) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for(int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            while(!stack.isEmpty() && stack.peek() > c && k > 0){
                stack.pop();
                k -= 1;
            }
            stack.push(c);    
        }

        //如果string本来就是单调递增的，则在上面的for-loop中没有删除任何字符，所有字符都加入到了队列中
        // 需要进行删除最后K个字符
        for(int i = 0; i < k; i++){
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true; //用来删除前导0
        while(!stack.isEmpty()){
            char ch = stack.pollLast();//每次取出stack队列的底端第一个字符
            if(leadingZero == true && ch == '0'){
                continue;
            }
            leadingZero = false;
            sb.append(ch);
        }
        //如果 k == String num 的长度，则所有字符都要删除，输出0
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
