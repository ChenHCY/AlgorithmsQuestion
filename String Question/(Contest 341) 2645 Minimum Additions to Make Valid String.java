/* Leetcode (Contest 341) 2645 Minimum Additions to Make Valid String

Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times, 

return the minimum number of letters that must be inserted so that word becomes valid.

A string is called valid if it can be formed by concatenating the string "abc" several times.

Example 1:

Input: word = "b"
Output: 2
Explanation: Insert the letter "a" right before "b", and the letter "c" right next to "a" to obtain the valid string "abc".

Example 2:

Input: word = "aaa"
Output: 6
Explanation: Insert letters "b" and "c" next to each "a" to obtain the valid string "abcabcabc".

Example 3:

Input: word = "abc"
Output: 0
Explanation: word is already valid. No modifications are needed. 
 

Constraints:

1 <= word.length <= 50
word consists of letters "a", "b" and "c" only. 
*/

/*思路： 因为题目是统计 在一个string中需要插入至少多少的字符 可以形成 有效字符

==》所以word中字母的位置是不能更改的，==》不能单纯通过统计a b c数量的办法来进行计算

==》必须按照string word中 a b c的情况来进行分析， 因为有效字符的顺序是确定的
所以：
  ==》1. 当遇到a的时候，只需要检查当前位置的后面两个字符是不是b 和 c, or 只有b or 只有c
  ==》2. 当遇到b的时候, 当前字符前面一定要补一个a
      ==>只需要检查当前位置的后面一个字符是不是c
  ==》3. 当遇到c的时候, 当前字符前面一定要补一个a 和 一个b
*/

//Time: O(n)  Space: O(1)
class Solution {
    public int addMinimum(String word) {
        int count = 0; //需要插入的字符数量
        int i = 0; //字符在word string中的位置
        
        while(i < word.length()){
            //1. 当遇到a的时候，只需要检查当前位置的后面两个字符是不是 b 和 c, or 只有b or 只有c or 是a
            if(word.charAt(i) == 'a'){
                if(i + 2 < word.length() && word.charAt(i + 1) == 'b' && word.charAt(i + 2) == 'c'){ //后面两个字符是b 和 c
                    i += 3; // 这种情况是已经形成有效字符，不需要再添加
                } else if (i + 1 < word.length() && word.charAt(i + 1) == 'b'){  // 当前字符后面是b 
                    count += 1; //后面要再补一个c  ==> 所以 count + 1  ==> ab + "c"
                    i += 2;
                } else if (i + 1 < word.length() && word.charAt(i + 1) == 'c'){ //当前字符后面是c
                    count += 1;//后面要再补一个b ==> 所以 count + 1 ==> a + "b" + c
                    i += 2;
                } else{ //当前字符后面是a
                    count += 2; //后面需要补一个 b 一个 c ==> 所以 count + 2 ==>  a + "bc" + a 
                    i++;
                }
            } 
          
          //2. 当遇到b的时候, 当前字符前面一定要补一个a
          else if (word.charAt(i) == 'b'){ //==>只需要检查当前位置的后面一个字符是不是c
                if(i + 1 < word.length() && word.charAt(i + 1) == 'c'){  //当前字符后面是c
                    count += 1; //只需要在前面补一个a ==》 所以 count + 1  ==> "a" + bc  
                    i += 2;
                } else { //当前字符后面不是c
                    count += 2; //需要前面补一个a 后面补一个c ==> 所以 count + 2 ==> "a" + b + "c" 
                    i += 1;
                } 
            } else{ // 3. 当遇到c的时候, 当前字符前面一定要补一个a 和 一个b
                count += 2; //所以不用再检查后面，==》 所以 count + 2   ==> "ab" + c 
                i++;
            }
        }
        
        return count;
    }
}
