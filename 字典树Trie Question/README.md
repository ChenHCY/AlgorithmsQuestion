# 字典树 Trie 的基础知识 
Trie树也称字典树，因为其效率很高，所以在在字符串查找、前缀匹配等中应用很广泛，其高效率是以空间为代价的。

利用String字符串构建一个字典树，这个字典树保存了String字符串的公共前缀信息，因此可以降低查询操作的复杂度。

定义结点Node里包含一个isWord(表示这个结点是否是一个单词的结尾)和一个大小为26的next。

![Trie](https://github.com/ChenHCY/AlgorithmsQuestion/assets/60770401/5a07c857-7e11-46dc-a599-f71fcdd7b8fd)

==> 基础模板： 可以包含插入、查找和查找前缀三个方法。
```Java
private class TrieNode{
    public boolean isWord; // isWord表示这个结点是否为一个单词的结尾，是否形成一个Word String  
    public Node[] next;  // next[]表示当前字母节点的下一个26个字母节点
        
    public TrieNode() {
        this.isWord = false;
        this.next = new Node[26];
    }
}
```

1. 定义一个根结点root作为整棵树的查找起点。 `private Node root;`
```Java
    TrieNode root; //根节点
    public Trie() {
        root = new TrieNode(); //开始构建字典树，每一个节点 有26个子节点
    }
```

2. 比如插入一个单词apple：

我们从root开始，依次往下查找a,p,p,l,e：

==》如果在结点n的下一个字母里没有找到我们想要的字母ch，我们就增加新结点到结点n的next[]，式子 `next[ch-'a']=new Node()`

```Java
    //插入一个单词: 把一个字符串 加入 到字典树 Trie
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
```

3. 查找一个单词app

我们从root开始，依次往下查找a,p,p：如果在结点n的下一个字母里没有找到我们想要的字母ch，

==》如果没有，那么说明不存在，返回False;

==》如果有，那么继续往下找，直到查找的单词app找到最后一位，
  
==> 这时候我们判断一下这个位置的isWord是否为True，=> 如果为True, 说明这是一个完整单词 |  => 否则只是部分，返回False。

```Java
  //查找单词: 查找是否存在一个完整的字符串
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
```

4. 查找一个前缀

查找一个前缀和查找一个单词的区别就是，当我们想要查找的前缀找到最后一位，不需要判断是否是完整的单词，直接返回True即可。如果不能全部找到则返回False。

```Java
   //查找前缀: 查找是否存在一个前缀字符串
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
```
# 时间复杂度(Time) && 空间复杂度(Space)
1. 时间复杂度(Time)：
   
==> 假设所有插入字符串长度之和为n，构建字典树的时间复杂度为 `O(n)`；

==> 假设要查找的所有字符串长度之和为k，查找的时间复杂度为`O(k)`。

==> 因此时间复杂度为`O(n+k)`

2. 空间复杂度(Space):

==> 字典树每个节点都需要用一个大小为26的数组来存储子节点的指针，所以空间复杂度较高。
