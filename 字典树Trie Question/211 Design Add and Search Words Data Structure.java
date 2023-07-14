/*Leetcode 211. Design Add and Search Words Data Structure

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

  · WordDictionary() Initializes the object.
  · void addWord(word) Adds word to the data structure, it can be matched later.
  · bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 
Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.
*/
//Time: 时间复杂度 addWord 操作的复杂度为 O(L)；search 操作的复杂度为 O(C^L)，其中 C 为字符集大小，固定为 26
//Space: 空间复杂度：令 n 为插入字符串数量，L 为字符串的最大长度，复杂度为 O(n∗L)
class WordDictionary {
    class TrieNode{
        boolean isWord = false; //标记是否是完整的字符串
        TrieNode[] next = new TrieNode[26]; // next[]表示这个结点的下一个26个字母节点对应是数字
    }

    TrieNode root;
    public WordDictionary() {
        root = new TrieNode(); //创建字典树的根节点
    }

    //把字符串String 加入形成 字典树
    public void addWord(String word) {
        int n = word.length();
        TrieNode curr = root; //提取根节点
        for(int i = 0; i < n; i++){
            int currIndex = word.charAt(i) - 'a'; //拿到当前字母对应的index位置
            if(curr.next[currIndex] == null){  //如果当前字母的index位置 之前没有使用过
                curr.next[currIndex] = new TrieNode(); //把当前字母的index位置, 创建一个新的字典树节点
            }
            curr = curr.next[currIndex]; //移动当前节点指针到下一层
        }
        curr.isWord = true; //结束，标记正确的形成了一个字符串
    }

    //检查是否能找在字典树找到 要求的 word String
    public boolean search(String word) {
        return dfs(word, root, 0); //call dfs funtion
    }

    //DFS深度搜索遍历：检查是否能找在字典树找到 要求的 word String
    //注意 '.' 可以替代匹配任何字符 
    public boolean dfs(String str, TrieNode curr, int index){
        int n = str.length();
        //exit condition, success case
        if(index == n){
            return curr.isWord;
        }
        char c = str.charAt(index); //提取当前的字母

        if(c == '.'){
            //'.' 可以替代匹配任何字符, 所以检查当前节点 是否 存在 字母
            for(int j = 0; j < 26; j++){  
                //移动到下一层继续遍历，如果其他部分字母都能找到，return true
                if(curr.next[j] != null && dfs(str, curr.next[j], index + 1)){
                    return true;
                }
            }
        } else{ //如果字符不是'.'
            int currIndex = c - 'a'; //提取当前字母对应的index位置
            if(curr.next[currIndex] == null){ //如果当前节点 不存在这个 字母
                return false;
            }
            return dfs(str, curr.next[currIndex], index + 1); //移动下一个层级 继续遍历
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
