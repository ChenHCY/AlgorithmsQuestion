/* 139. Word Break
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

class Solution {
    class TrieNode{
        boolean isWord = false; //表示这个结点是否为一个单词的结尾
        TrieNode[] next = new TrieNode[26];  // next[]表示这个结点的下一个26个字母节点对应是数字
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();;
        TrieNode root = buildTrie(wordDict); //把wordDict加入形成一个整体的字典树

        //dp[i] 代表第i个字符到结尾组成的字符串能否被拆分为字典中的单词
        boolean[] dp = new boolean[n + 1]; //记录String s里面组成的子字符串，能否在wordDict中找到
        dp[n] = true;

        for(int i = n - 1; i >= 0; i--){
            TrieNode curr = root; //提取当前的字典树根节点
            for(int j = i; j < n; j++){
                int currIndex = s.charAt(j) - 'a';
                //如果当前层级不存在这个这个字母的节点
                if(curr.next[currIndex] == null){ //字典树中不存在该后续节点 直接退出本次循环
                    break; //跳出循环
                }
                if(curr.next[currIndex].isWord == true){
                    // dp[i] 为true 表示i后面的字符串可以拆分成字典中的单词 直接退出本次循环
                    dp[i] = dp[j + 1];
                    if(dp[i] == true){
                        break;
                    }
                }
                curr = curr.next[currIndex]; //移动的下一个层级
            }
        }
        return dp[0];
    }

    //funtion: build the Trie tree (prefix tree)
    //把wordDict中的所有字符串加入到字典树
    public TrieNode buildTrie(List<String> wordDict){
        TrieNode root = new TrieNode(); //根节点

        for(int i = 0; i < wordDict.size(); i++){
            char[] arr = wordDict.get(i).toCharArray(); //拿到wordDict中所有字符串的character array
            TrieNode curr = root; //当前节点

            for(char c : arr){
                // 依次添加每个字母 => 找到每个字母的index位置
                // 如果当前字母的index位置 之前没有使用过, 把当前字母的index位置, 创建一个新的字典树节点
                if(curr.next[c - 'a'] == null){ //如果当前字母的index位置 之前没有使用过
                    curr.next[c - 'a'] = new TrieNode(); //把当前字母的index位置, 创建一个新的字典树节点
                }
                curr = curr.next[c - 'a']; //移动当前节点指针到下一层
            }
            curr.isWord = true; //结束，标记正确的形成了一个字符串
        }
        return root; //输出最后得到的字典树
    }
}
