/* Leetcode 1048. Longest String Chain
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

        For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".

A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
*/

//Time: O(m * n * (logn + m)）  ==> n 表示字符串数组的长度, m 表示每个字符串的平均长度
//==> 对字符串数组进行排序，需要的时间为 O(n × m × logn)
//==> 然后遍历每个字符串，并对每个字符串都生成其「前身」字符串，需要 O(n × m^2)
// 因此总的时间复杂度为： O(m * n * (logn + m)）

//Space: O(n * m) ==> n 表示字符串数组的长度, m 表示每个字符串的平均长度
class Solution {
    public int longestStrChain(String[] words) {
        int res = 0;
        //HashMap里面储存的是不同字符串string, 能形成的单词链的最长长度
        //然后通过检查每个string能形成的previous string, 计算单词链的最长长度
        HashMap<String, Integer> dp = new HashMap<>();
      
        //根据words[]所以string字符串的长度进行升序排序 ==》 O(n × m × logn)
        //sort the word string from words[] based on the length increasing order
        Arrays.sort(words, (a, b) -> {
           return a.length() - b.length();
        });

        //travser all the string from words[] array ==》 O(n × m^2)
        for(String word : words){
            //一个单词通常是 k == 1 的 单词链 。
            dp.put(word, 1); //首先把每个string都存入hashmap中，同时放入基础的长度
            
            //然后每次删除一个字母，得到这个string的previous前身string
            for(int i = 0; i < word.length(); i++){
                String previous = word.substring(0, i) + word.substring(i + 1);
                //然后检查这个前身string在hashmap中已经存在的单词链长度
                //再与当前string的单词链长度比较，更新存入最长的长度
                if(dp.containsKey(previous)){
                    dp.put(word, Math.max(dp.get(word), dp.get(previous) + 1));
                }
            }
            res = Math.max(res, dp.get(word)); //因为要找到最长单词链的长度，所以每次更新
        }
        return res;
    }
}
