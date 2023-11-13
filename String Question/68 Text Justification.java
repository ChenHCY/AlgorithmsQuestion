/* Leetcode 68. Text Justification

Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters 
and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when 
necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty 
slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:
A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 

Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
*/

// 根据maxWidth来排版words里面的单词String, 把单词加入到StringBuilder里面，每个单词必须间隔一个空格
// 使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
// 1. 计算每一行不超过maxWidth的情况下，能放入最多几个words
// 2. 然后根据剩余的空格，构建每一行的String, 平均分配，从左往右
// 3. 特殊情况1：当前行只有一个单词：该单词左对齐，在行末填充剩余空格
// 4. 特殊情况2：当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
  
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        int right = 0; // 双指针，确认每一行内放入的word range

        while(true){
            int left = right; //当前行内可以放入的第一个单词在words中的index位置
            int sumLen = 0; // 统计当前行的单词长度之后

            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            // words[right].length(): right pointer 对应的单词长度
            // right - left: 两个单词中间至少有一个空格
            while(right < n && sumLen + words[right].length() + right - left <= maxWidth){
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if(right == n){
                StringBuilder sb = addStr(words, left, n, " "); // 且单词之间应只有一个空格
                sb.append(addSpaces(maxWidth - sb.length())); // 在行末填充剩余空格
                res.add(sb.toString()); //加入到最后res
                return res; //输出答案
            }

            int numWords = right - left; //计算每一行单词的数量
            int numSpaces = maxWidth - sumLen; // 当前行 可以放置空格的数量

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuilder sb = new StringBuilder(words[left]);
                sb.append(addSpaces(numSpaces));
                res.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词，需要平均分配空格数量
            int avgSpaces = numSpaces / (numWords - 1); // 平均空格
            int extraSpaces = numSpaces % (numWords - 1); //除开平均空格之后，多余的空格数量

            //使用StringBuilder组建每一行的字符串
            StringBuilder sb = new StringBuilder();
            sb.append(addStr(words, left, left + extraSpaces + 1, addSpaces(avgSpaces + 1)));  // 拼接，需要额外加一个空格的单词部分
            sb.append(addSpaces(avgSpaces));
            sb.append(addStr(words, left + extraSpaces + 1, right, addSpaces(avgSpaces))); // 拼接其余单词
            res.add(sb.toString()); //把组建好的字符串加入到最后的答案中
        }
    }

    // 返回长度为 n 的由空格组成的字符串
    public String addSpaces(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuilder addStr(String[] words, int left, int right, String spaceStr) {
        StringBuilder sb = new StringBuilder(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(spaceStr); //单词之间的空格
            sb.append(words[i]);
        }
        return sb;
    }
}
