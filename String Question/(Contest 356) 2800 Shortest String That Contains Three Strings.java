/* Leetcode (Contest 356) 2800. Shortest String That Contains Three Strings

Given three strings a, b, and c, your task is to find a string that has the minimum length and contains all three strings as substrings.
If there are multiple such strings, return the lexicographically smallest one.

Return a string denoting the answer to the problem.

Notes

  · A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has 
    a letter that appears earlier in the alphabet than the corresponding letter in b.
    
  · A substring is a contiguous sequence of characters within a string.
 

Example 1:

Input: a = "abc", b = "bca", c = "aaa"
Output: "aaabca"
Explanation:  We show that "aaabca" contains all the given strings: a = ans[2...4], b = ans[3..5], c = ans[0..2]. It can be shown that the length of the resulting string would be at least 6 and "aaabca" is the lexicographically smallest one.

Example 2:

Input: a = "ab", b = "ba", c = "aba"
Output: "aba"
Explanation: We show that the string "aba" contains all the given strings: a = ans[0..1], b = ans[1..2], c = ans[0..2]. Since the length of c is 3, the length of the resulting string would be at least 3. It can be shown that "aba" is the lexicographically smallest one.
 

Constraints:

1 <= a.length, b.length, c.length <= 100
a, b, c consist only of lowercase English letters.

*/

//Time: O(n^2)   Space: O(n)
class Solution {
    public String minimumString(String a, String b, String c) {
        //三个字符串 6种不同顺序的拼接情况
        List<String> list = new ArrayList<>();
        list.add(OpStr(a, b, c));
        list.add(OpStr(a, c, b));
        list.add(OpStr(b, a, c));
        list.add(OpStr(b, c, a));
        list.add(OpStr(c, a, b));
        list.add(OpStr(c, b, a));

        //然后排序，找到新字符串中 长度最短，字典序最小的  lexicographically smallest one.
        Collections.sort(list, (x, y) -> {
            int xL = x.length();
            int yL = y.length();
            //判断长度是否一样，不一样，按长度的升序排列，一样的，按照字典序小的排序
            return xL != yL ? x.length() - y.length() : x.compareTo(y);
        });

        return list.get(0);        
    }

    //拼接三个 string 字符串
    private String OpStr(String a, String b, String c){
        return OpStr2(OpStr2(a, b), c);
    }

    //拼接两个 string 字符串
    private String OpStr2(String a, String b){
        //从String b的尾部开始枚举，不断尝试尽可能少的使用字符串b中的字符
        for(int i = 0; i <= b.length(); i++){ //=> 因为是从后往前（b.length() - i), 并且要能取到 0-index 的字母
            //substring() 得到 一个字符串的子字符串，左闭右开的区间
            //这样可以从b的尾部开始枚举子字符串的情况，来拼接到String a的后面，形成一个 字符串 a b拼接的效果
            //最后找到既包括a又包括b的新字符串 的最短 长度
            String currStr = a + b.substring(b.length() - i, b.length());
            
            //然后每次检查是否找到了既包括a又包括b的新字符串
            if(currStr.indexOf(b) != -1){ //如果找到了，直接输出
                return currStr;
            }
        }
        return ""; // 程序不会走到这，因为上面枚举到最后时t = a+b 一定既包括a又包括b。
    }
}
