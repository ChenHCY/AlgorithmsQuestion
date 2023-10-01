/*Leetcode 648. Replace Words

In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call 
this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the 
successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it 
with the root that has the shortest length.

Return the sentence after the replacement.

Example 1:

Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Example 2:

Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"
 

Constraints:
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 106
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Every two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.
*/

/******************** 使用dictionary中的string 替换 sentence里面的words ******************************/
class Solution {
    //建立字典树，储存 所有的 dictionary String字符串
    //然后查找sentence里面的words的前缀，进行替换
    class TrieNode{
        boolean isWord;  //表示这个结点是否为一个单词的结尾
        TrieNode[] children = new TrieNode[26]; // children[]表示每个字典树节点的26个子字母节点
    }

    // 定义一个根结点root作为整棵树的查找起点
    TrieNode root = new TrieNode();

    //add function: 添加string 进入字典树
    public void add(String s){
        TrieNode curr = root; // 提取当前的字典树
        int n = s.length();
        for(int i = 0; i < n; i++){
            // 依次添加每个字母 => 找到每个字母的index位置
            // 如果当前字母的index位置 之前没有使用过, 把当前字母的index位置, 创建一个新的字典树节点
            int currIndex = s.charAt(i) - 'a';

            // 如果当前字母的index位置 之前没有使用过
            if(curr.children[currIndex] == null){
                //把当前字母的index位置, 创建一个新的字典树节点
                curr.children[currIndex] = new TrieNode();
                //所以当前节点也会有字典树节点的空间和26个子节点
            }
            curr = curr.children[currIndex]; //移动当前节点指针到下一层
        }
        curr.isWord = true; //完成加入，标记形成String
    }

    // Search function: 搜索sentence里面words 是否有存在于 字典树的前缀
    // 并且进行替换
    public String search(String s){
        StringBuilder sb = new StringBuilder();
        TrieNode curr = root; // 提取当前的字典树
        int n = s.length();
        for(int i = 0; i < n; i++){
            //找到当前字母的对应[0-26]中的index位置
            int currIndex = s.charAt(i) - 'a';

            //如果已经走到字典树最后一个节点
            //表示这个dictionary中的stirng是当前words的前缀
            if(curr.isWord){
                return sb.toString(); //输出这个dictionary string
            }

            //如果找不到当前字母的index, 表示不是前缀，终止遍历
            if(curr.children[currIndex] == null){
                break;
            }

            //如果存在于字典树中，则加入到stringbuilder中，组成前缀，在后面进行替换
            sb.append(s.charAt(i));
            curr = curr.children[currIndex]; //移动当前节点指针到下一层 
        }

        return s; //如果找不到符合的前缀，则不进行替换
    }
    

    //遍历sentence里面的所有words 然后在dictionary形成的字典树中找到一样的前缀，进行替换
    public String replaceWords(List<String> dictionary, String sentence) {
        //1. 首先把dictionary中的所有string加入到字典树
        for(String str : dictionary){
            add(str);
        }

        //2. 查找sentence里面每个word单词的前缀，进行替换
        StringBuilder sb = new StringBuilder();
        String[] strArr = sentence.split(" "); // 把sentences按空格划分单词组成一个string array

        //遍历每一个words
        for(String word : strArr){
            //然后在字典是找到符合要求的前缀进行替换
            //如果找不到，则加入原words到stringbuilder
            sb.append(search(word));
            sb.append(" "); //因为最后是一句话，要把原来的空格补回去
        }

        return sb.toString().trim(); //消除前后多余的空格
    }
}
