/* Leetcode 1081. Smallest Subsequence of Distinct Characters

Given a string s, return the lexicographically smallest
 
subsequence of s that contains all the distinct characters of s exactly once.

Example 1:

Input: s = "bcabc"
Output: "abc"

Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/
//Time: O(n)   Space: O(n)
class Solution {
    //改写成字典序最小的子字符串
    public String smallestSubsequence(String s) {
        int len = s.length();
        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < len; i++){
            char curr = s.charAt(i);
            //如果当前字母在队列已经存在，则直接跳过后续步骤 移动到下一个字母，因为每个字母只能出现一次
            if(stack.contains(curr)){
                continue;
            }

            //如果当前字母的字典序比队列顶端的字母小，且队列顶端的字母在后面还会继续出现，则提取删除stack() 队列顶端的字母
            // s.indexOf(stack.peek(), i) != -1 => 判断字符串里面是否存在每一个character 或者 字符串， i表示字符串检索开始的位置
            while(!stack.isEmpty() && curr < stack.peek() && s.indexOf(stack.peek(), i) != -1){
                stack.pop();
            }
            stack.push(curr); //每次把新的字母加入到队列中
        }

        //把stack队列中的所有element 添加在一起 改写成String 字符串
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            char c = stack.peekLast(); //每次取出stack队列的订单第一个字母
            sb.append(c);
            stack.removeLast(); //保持字典序的单调递增
        }
        return sb.toString();
    }
}
