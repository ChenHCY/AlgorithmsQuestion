/* Leetcode 394. Decode String

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 

Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. 

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, 
there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.


Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
*/

/* 思路： 此题是更具字符串，左右括号，和数字来解析字符串

所以使用两个stack来进行储存，

==》 一个number stack储存数字，

==》一个 dict stack 储存字母（中间以"[" 为挡板，来确定字符串range）
  
    ==> 解析成功的需要重新加入回dict stack中，因为是存在嵌套的情况的

==》 因为stack是先进后出的情况，所以在解析字符串的时候，要颠倒一下先后顺序
*/

class Solution {
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<Integer>(); //储存数字
        Deque<String> dictStack = new ArrayDeque<String>();//储存字母

         // 一共有四种情况: number, dict, [, ]  
        //把数字和字母分别加入到stack中，然后从后往前遍历确定区域(遇到左隔板 "["）
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)){ //当遇到数字的时候
                int num = ch - '0';
                while(Character.isDigit(s.charAt(i + 1))){ //把整个数字存入 number stack中
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                numStack.push(num);
            } else if(Character.isLetter(ch)){ //当遇到字母的时候
                dictStack.push(ch + ""); //因为charAt()是char类型，所以要改成string类型
            } else if(ch == '['){
                dictStack.push("["); //设置左隔板
            } else if(ch == ']'){
              
                String temp = ""; //形成需要解析的字符串
                while(dictStack.peek() != "["){ //从stack中pop()出字母，直到遇到左隔板
                    temp = dictStack.pop() + temp; //  因为stack是先进后出的情况， 所以要颠倒一下
                }
              
                dictStack.pop(); //删除左隔板

                int time = numStack.pop(); //确认当前字符串需要重复的次数

                String decodeStr = ""; //形成新的字符串
                for(int j = 0; j < time; j++){
                    decodeStr += temp; //把当前的字符串加入到StringBuilder中去形成最后的结果字符串
                }

                dictStack.push(decodeStr); //重新加回到dict stack中
            }
        }

        String res = ""; //把stack中的字符串转换形成为整个的答案
        while(!dictStack.isEmpty()){
            res = dictStack.pop() + res;
        }

        return res;
    }
}
