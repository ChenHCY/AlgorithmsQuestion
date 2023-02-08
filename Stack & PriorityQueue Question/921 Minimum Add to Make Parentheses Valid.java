/* Leetcode 921. Minimum Add to Make Parentheses Valid

A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.


Example 1:

Input: s = "())"
Output: 1


Example 2:

Input: s = "((("
Output: 3
 

Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
*/

/*

使用stack来检查括号能否相互抵消

if: 当遇见右括号的时候，查找stack的第一个元素是否是左括号

==》 如果是的，删除stack里面的左括号，代表可以抵消

==》 如果不是，表示不能抵消

else: 全部加入stack中

最好无法抵消的都存在于stack中，stack.size()也就是需要加多少个的括号

*/

//Time: O(n)  Space: O(n)
class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++){
            if(stack.size() != 0 && s.charAt(i) == ')' && stack.peek() == '('){
                stack.pop(); //remove Valid Parentheses
            } else{
                stack.push(s.charAt(i));
            }
        }

        return stack.size();
    }
}
