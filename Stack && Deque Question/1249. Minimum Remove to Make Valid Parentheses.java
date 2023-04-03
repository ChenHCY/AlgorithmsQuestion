/*Leetcode 1249. Minimum Remove to Make Valid Parentheses

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so 
that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

  It is the empty string, contains only lowercase characters, or
  It can be written as AB (A concatenated with B), where A and B are valid strings, or
  It can be written as (A), where A is a valid string.
  
Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
 

Constraints:

1 <= s.length <= 10^5
s[i] is either'(' , ')', or lowercase English letter.
*/

/* 解题思路： 此题是删除最少的括号，是所有括号能配对成功

1. 使用stack记录所有左括号的位置（index）

2. 使用hashset来记录多余的括号位置 ==》也是需要删除的index

然后开始从前往后遍历 String s:

遇到左括号就放入stack中，

遇到右括号，就开始检查stack里面是否为空：==》也就是前面存不存在左括号能够和当前的右括号配对

==》如果不为空，表示前面有左括号能配对，从stack中弹出pop()左括号

==》如果为空，表示前面没有左括号，把当前这个右括号的位置（index）记录到hashset中 ==》表示这个右括号是要删除的

3. 遍历结束后， 检查stack中是否为空 ==》里面是否还有未配对的左括号。

如果有， 全部记录到hashset中 ==》表示这些左括号都是要删除的

4. 最后使用stringbuilder，来拼接成一个新的string result ==> if(!deleteSet.contains(j))

*/

//Time: O(3n) = O(n) Space: O(n)
class Solution {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new ArrayDeque<>(); 
        HashSet<Integer> deleteSet = new HashSet<>(); //save the index that need remove
        StringBuilder sb = new StringBuilder(); //the final result

        //travser all the character from string s
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i); //记录左括号的位置，等待配对
            } else if (s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    deleteSet.add(i); //前面没有左括号，这就是需要删除的
                } else{
                    stack.pop(); //括号配对成功
                }
            }
        }

        while(!stack.isEmpty()){ //有存在左括号剩余的情况
            deleteSet.add(stack.pop()); //全部记录到hastset中
        }

        //把string s中除开删除的括号剩余的字母组合成新的string res
        for(int j = 0; j < s.length(); j++){
            if(!deleteSet.contains(j)){ 
                sb.append(s.charAt(j));
            }
        }

        return sb.toString();
    }
}
