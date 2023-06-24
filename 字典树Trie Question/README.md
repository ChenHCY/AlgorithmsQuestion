# 字典树 Trie 的基础知识 
Trie树也称字典树，因为其效率很高，所以在在字符串查找、前缀匹配等中应用很广泛，其高效率是以空间为代价的。

利用String字符串构建一个字典树，这个字典树保存了String字符串的公共前缀信息，因此可以降低查询操作的复杂度。

定义结点Node里包含一个isWord(表示这个结点是否是一个单词的结尾)和一个大小为26的next。

==> 基础模板： 可以包含插入、查找和查找前缀三个方法。
```Java
private class Node{
    // isWord表示这个结点是否为一个单词的结尾
    // next[]表示这个结点的下一个26个字母结点
    public boolean isWord;  
    public Node[] next; 
        
    public Node() {
        this.isWord = false;
        this.next = new Node[26];
    }
}
```

1. 定义一个根结点root作为整棵树的查找起点。 `private Node root;`
```Java
private Node root
public Trie() {
    root = new Node(); 
}
```

2. 比如插入一个单词apple：

我们从root开始，依次往下查找a,p,p,l,e：

==》如果在结点n的下一个字母里没有找到我们想要的字母ch，我们就增加新结点到结点n的next[]，式子 `next[ch-'a']=new Node()`

```Java
//插入一个单词
    public void insert(String word) {
        Node now = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            // 依次寻找每个字符
            // 如果所有下一个结点中没有当前字符，则增加新结点到下一个next[pos]
            int pos = word.charAt(i) - 'a';
            if (now.next[pos] == null) {
                now.next[pos] = new Node();
            }
            now = now.next[pos];
        }
        now.isWord = true;
    }
```

3. 查找一个单词app

我们从root开始，依次往下查找a,p,p：如果在结点n的下一个字母里没有找到我们想要的字母ch，

==》如果没有，那么说明不存在，返回False;

==》如果有，那么继续往下找，直到查找的单词app找到最后一位，
  
==> 这时候我们判断一下这个位置的isWord是否为True，=> 如果为True, 说明这是一个完整单词 |  => 否则只是部分，返回False。

```Java
  // 查找单词
    public boolean search(String word) {
        int n = word.length();
        Node now = root;
        for (int i = 0; i < n; i++) {
            int ch = word.charAt(i) - 'a';
            // 如果有下一个对应字符的结点，那么继续查找下一个结点
            // 如果没有下一个对应字符的结点，那么说明查找单词失败
            if (now.next[ch] != null) {
                now = now.next[ch];
            }
            else {
                return false;
            }
        }
        // 如果每个字符都有且是完整单词，才说明查找单词成功
        return now.isWord;
    }

```

4. 查找一个前缀

查找一个前缀和查找一个单词的区别就是，当我们想要查找的前缀找到最后一位，不需要判断是否是完整的单词，直接返回True即可。如果不能全部找到则返回False。

```Java
// 查找前缀
   public boolean startsWith(String prefix) {
        int n = prefix.length();
        Node now = root;
        for (int i = 0; i < n; i++) {
            int ch = prefix.charAt(i) - 'a';
            // 如果有下一个对应字符的结点，那么继续查找下一个结点
            // 如果没有下一个对应字符的结点，那么说明查找前缀失败
            if (now.next[ch] != null) {
                now = now.next[ch];
            }
            else {
                return false;
            }
        }
        return true;
    }
```
# 时间复杂度(Time) && 空间复杂度(Space)
1. 时间复杂度(Time)：
   
==> 假设所有插入字符串长度之和为n，构建字典树的时间复杂度为 `O(n)`；

==> 假设要查找的所有字符串长度之和为k，查找的时间复杂度为`O(k)`。

==> 因此时间复杂度为`O(n+k)`

2. 空间复杂度(Space):

==> 字典树每个节点都需要用一个大小为26的数组来存储子节点的指针，所以空间复杂度较高。
