/* Leetcode 208. Implement Trie (Prefix Tree)

A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. 

There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

    · Trie() Initializes the trie object.
    · void insert(String word) Inserts the string word into the trie.
    · boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
    · boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

Example 1:
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
 

Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
*/

class Trie {
    class TrieNode{
        boolean isWord; //表示这个结点是否为一个单词的结尾
        TrieNode[] next = new TrieNode[26]; // next[]表示这个结点的下一个26个字母节点
    }

    TrieNode root; //根节点
    public Trie() {
        root = new TrieNode(); //开始构建字典树，每一个节点 有26个子节点
    }

    //把一个字符串 加入 到字典树 Trie
    public void insert(String word) {
        TrieNode curr = root; // 提取当前的字典树
        int n = word.length();
        for(int i = 0; i < n; i++){
            // 依次添加每个字母 => 找到每个字母的index位置
            // 如果当前字母的index位置 之前没有使用过, 把当前字母的index位置, 创建一个新的字典树节点
            int currIndex = word.charAt(i) - 'a'; //找到当前字母的对应[0-26]中的index位置

            //如果当前字母的index位置 之前没有使用过
            if(curr.next[currIndex] == null){ 
                curr.next[currIndex] = new TrieNode(); //把当前字母的index位置, 创建一个新的字典树节点
                //所以当前节点也会有字典树节点的空间和26个子节点
            }
            curr = curr.next[currIndex]; //移动当前节点指针到下一层
        }
        curr.isWord = true; //完成加入，标记是否形成String
    }

    //查找是否存在一个完整的字符串
    public boolean search(String word) {
        TrieNode curr = root; //提取当前的字典树信息
        int n = word.length();
        for(int i = 0; i < n; i++){
            int currIndex = word.charAt(i) - 'a'; //找到当前字母的对应[0-26]中的index位置

            //如果不存在当前字母对应的index节点
            if(curr.next[currIndex] == null){ 
                return false;
            }
            curr = curr.next[currIndex]; //移动当前节点指针到下一层
        }
        return curr.isWord; //最后判断找到完整的 Word String
    }

    //查找是否存在一个前缀字符串
    public boolean startsWith(String prefix) {
        TrieNode curr = root; //提取当前的字典树信息
        int n = prefix.length();
        for(int i = 0; i < n; i++){
            int currIndex = prefix.charAt(i) - 'a'; //找到当前字母的对应[0-26]中的index位置

            //如果不存在当前字母对应的index节点
            if(curr.next[currIndex] == null){ 
                return false;
            }
            curr = curr.next[currIndex]; //移动当前节点指针到下一层
        }

        //因为这里是一个前缀的部分String，所以最后不用判断是否形成了一个完整的word String
        return true; //如果存在所有字符串 字母的index节点，输出true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
