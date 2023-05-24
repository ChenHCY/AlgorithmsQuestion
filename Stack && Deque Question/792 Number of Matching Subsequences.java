/* Leetcode 792. Number of Matching Subsequences

Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 
Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".

Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2
 

Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
*/

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        int res = 0;

        //分桶，然后根据words[]中所有string的首字母进行分类
        Deque<String>[] stack = new Deque[26];
        for(int i = 0; i < 26; i++){
            stack[i] = new ArrayDeque<>(); //首先创建26个空的队列
        }
        for(String str : words){
            stack[str.charAt(0) - 'a'].add(str); //根据words[]中所有string的首字母进行分类
        }

        //然后遍历String s中的所有字母，每次拿出一个字母检查和取出对应字母的桶中所有的单词
        //删除掉这个桶中的首字母，如果长度为1，表示当前string是string s的子字符串
        //如果长度不为1，删除掉首字母之后，再次根据新的首字母放入对应的桶中
        for(char c : s.toCharArray()){
            // 在JAVA 10中 引入了 “var” key words，可以自动判断数据类型
            // 所以在这里也可以写成 var pq = stack[c - 'a']; 也是取出对应队列的string
            Deque<String> pq = stack[c - 'a']; //根据首字母 取出对应桶中所有的string
            
         for(int i = pq.size(); i > 0; i--){
                //拿出桶中的第一个string
                String tempStr = pq.pollFirst();
                //如果这个string只剩下一个字母，一定是string s的substring
                if(tempStr.length() == 1){
                    res += 1;
                } else{ //如果长度大于1，则删除首字母之后，根据新的首字母再次放入对应的桶中
                   stack[tempStr.charAt(1) - 'a'].add(tempStr.substring(1));
                }
            }
        }

        return res;
    }
}
