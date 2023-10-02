/* 677. Map Sum Pairs

Design a map that allows you to do the following:

  · Maps a string key to a given value.
  · Returns the sum of the values that have a key with a prefix equal to a given string.

Implement the MapSum class:

  · MapSum() Initializes the MapSum object.
  · void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
  · int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 
Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
*/

class MapSum {
    //建立字典树，储存所有string字符串和对应的value
    class TrieNode{
        TrieNode[] children = new TrieNode[26]; // children[]表示每个字典树节点的26个子字母节点
        int val = 0; //每个string字符串对应的value
    }

    // 定义一个根结点root作为整棵树的查找起点
    TrieNode root = new TrieNode();

    //MapSum() Initializes the MapSum object.
    public MapSum() {

    }
    
    //添加key string和 val进入字符串
    public void insert(String key, int val) {
        TrieNode currNode = root;
        int n = key.length();

        for(int i = 0; i < n; i++){
            // 依次添加每个字母 => 找到每个字母的index位置
            // 如果当前字母的index位置 之前没有使用过, 把当前字母的index位置, 创建一个新的字典树节点
            int currIndex = key.charAt(i) - 'a';
            
            // 如果当前字母的index位置 之前没有使用过
            if(currNode.children[currIndex] == null){
                //把当前字母的index位置, 创建一个新的字典树节点
                currNode.children[currIndex] = new TrieNode();
                //所以当前节点也会有字典树节点的空间和26个子节点
            }
            currNode = currNode.children[currIndex]; //移动当前节点指针到下一层
        }
        currNode.val = val; //在这个String key字符串的结尾的字母节点赋上val的值
    }
    
    //查找当前string是哪些字符串的前缀，然后统计这些字符串的value总和
    public int sum(String prefix) {
        TrieNode currNode = root;
        int n = prefix.length();

        for(int i = 0; i < n; i++){
            //找到当前字母的对应[0-26]中的index位置
            int currIndex = prefix.charAt(i) - 'a';

            //如果不存在当前字母对应的index节点, 表示当前string不是这个的前缀
            if(currNode.children[currIndex] == null){ 
                return 0;
            }
            currNode = currNode.children[currIndex]; //移动当前节点指针到下一层
        }

        //for-loop结束之后，表示已经找到当前string的最后一个节点，前缀已经搜索完成
        return countSum(currNode); //然后在字典树中从这个节点开始，使用DFS深度优先遍历 统计所有value的总和
    }

    //DFS function: 当前我们已经找到前缀位于字典树的最后一个节点，然后我们深度搜索开始 查找和计算 prefix前缀 在字典树中所有映射 value的总和
    public int countSum(TrieNode node){
        //到达字典树的最后一个节点，或者不存在这个节点，返回0
        if(node == null){
            return 0;
        }

        int sum = 0; //value总和
        if(node.val > 0){ //当前节点存在val值
            sum = node.val;
        }

        //继续DFS深度遍历, 添加这个root的所有val总和
        for(TrieNode childNode : node.children){
            sum += countSum(childNode);
        }

        return sum;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
