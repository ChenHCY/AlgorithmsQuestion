/* Leetcode 844. Backspace String Compare

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
 

Constraints:
1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.
*/

// Time: O(n)  Space: O(1)
class Solution {
    // #删除前一个字母，检查剩余的字母 string s 和 string t是否一样
    // 所以我们逆顺序遍历，从右往左，检查两个string的 每个字母是否一样
    // ==> 当遇见#号的时候，跳过下一个字母
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int sBackspace = 0; // 记录String s 里面有几个#
        int tBackspace = 0;  // 记录String t 里面有几个#

        // main travser, 
        while(i >= 0 || j >= 0){
            //检查string s中剩余的下一个字母
            while(i >= 0){
                //当遇到#的时候
                if(s.charAt(i) == '#'){
                    sBackspace += 1; //计数#
                    i--; //移动i指针
                } else if(sBackspace > 0){ //当遍历到字母，且是需要删除的时候
                    sBackspace--;
                    i--;
                } else{ //当遍历到字母，且不需要删除的时候，跳出当前循环
                    break;
                }
            }

            //检查string t 中剩余的下一个字母
            while(j >= 0){
                //当遇到#的时候
                if(t.charAt(j) == '#'){
                    tBackspace += 1; //计数#
                    j--; //移动i指针
                } else if(tBackspace > 0){ //当遍历到字母，且是需要删除的时候
                    tBackspace--;
                    j--;
                } else{ //当遍历到字母，且不需要删除的时候，跳出当前循环
                    break;
                }
            }

            // 在这里我们已经找到了String t 和 String s剩余的字母，然后我们进行比较
            // 如果两个string都还没到达结尾，且不相等，输出false
            if(i >= 0 && j >= 0){
                if(s.charAt(i) != t.charAt(j)){
                    return false;
                }
            } else { //其中有一个string遍历结束了，另外一个还有剩余字母
                if(i >= 0 || j >= 0){
                    return false;
                }
            }

            //如果当前字母一样，且遍历还没有结束，移动 i j指针
            i--;
            j--;
        }

        //循环结束 代表string s 和 string t 剩余字母一样
        return true;
    }
}
