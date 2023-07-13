/*Leetcode 140. Word Break II

Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 10^5.
*/

class TrieNode{
    public boolean isWord = false; //表示这个结点是否为一个单词的结尾
    public TrieNode[] next = new TrieNode[26];  // next[]表示这个结点的下一个26个字母节点对应是数字
}

class Solution {
    List<String> res = new ArrayList<>(); //输出的答案
    TrieNode root = new TrieNode(); //字典树根节点，开始创建字典树

    public List<String> wordBreak(String s, List<String> wordDict) {
        buildTrie(wordDict);  //把wordDict加入形成一个整体的字典树
        StringBuilder sb = new StringBuilder(); //储存wordDict形成的句子
        dfs(s, 0, sb); //call the dfs function
        return res;
    }

    //DFS function: 深度遍历回溯，找到每一种可能的划分句子
    public void dfs(String str, int start, StringBuilder sb){
        //exit condition => success case
        if(start == str.length()){ //遍历到了String s的最后一个字母，成功使用wordDict的单词组成String s
            sb.deleteCharAt(sb.length() - 1); //删除最后一个空格
            res.add(sb.toString()); //把划分的例子 加入到 结果
            return; //结束循环
        }

        //开始遍历查找
        TrieNode curr = root;
        for(int i = start; i < str.length(); i++){
            int currIndex = str.charAt(i) - 'a';
            if(curr.next[currIndex] == null){  //字典树中不存在该后续节点 直接退出本次循环
                return;
            }
            //如果找到wordDict当中的单词，则加入到stringbuilder
            if(curr.next[currIndex].isWord == true){
                StringBuilder strNext = new StringBuilder(sb);
                strNext.append(str.substring(start, i + 1) + " ");// StringBuilder 左闭右开区间
                //继续查找遍历String s后面的部分
                dfs(str, i + 1, strNext);
            }
            curr = curr.next[currIndex]; //移动字典树的当前节点到下一个层级
        }
    }

    //Small funtion: build the Trie tree (prefix tree)
    //把wordDict中的所有字符串加入到字典树
    private void buildTrie(List<String> wordDict){
        for(String word : wordDict){
            char[] arr = word.toCharArray(); //拿到wordDict中所有字符串的character array
            TrieNode curr = root; //当前节点

            for(char c : arr){
                // 依次添加每个字母 => 找到每个字母的index位置
                // 如果当前字母的index位置 之前没有使用过, 把当前字母的index位置, 创建一个新的字典树节点
                int currIndex = c - 'a';
                if(curr.next[currIndex] == null){ //如果当前字母的index位置 之前没有使用过
                    curr.next[currIndex] = new TrieNode(); //把当前字母的index位置, 创建一个新的字典树节点
                }
                curr = curr.next[currIndex]; //移动当前节点指针到下一层
            }
            curr.isWord = true; //结束，标记正确的形成了一个字符串
        }
    }
}
