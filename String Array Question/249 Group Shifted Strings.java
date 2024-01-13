/* 249. Group Shifted Strings

We can shift a string by shifting each of its letters to its successive letter.

    · For example, "abc" can be shifted to be "bcd".

We can keep shifting the string to form a sequence.

    · For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
    
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

Example 1:
Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

Example 2:
Input: strings = ["a"]
Output: [["a"]]
 
Constraints:
1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.
*/

// 提取所有字符串的每个字母相对于字符串首字母距离进行%计算，得到每一个字符串最基础的数字基础
// ==> 然后将其作为hashmap的key值进行储存，和所有具有相同的基础的List<String>列表

/* 比如："#shift0#shift1#shift2...shiftN"，
  ==> 其中shiftN表示的是对某个给定的字符串，其第N位上的字符与第0位上字符的距离
  ==> For example: 字符串"abc"，"bcd" 序列化后的键值都是 "#0#1#2"
*/
// Time: O(m * n) m is the length of strings[], n is the length of strings[i]
// Space: O(n)
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();

        // 遍历每一个strings[] 的字符串
        for(String str : strings){
            StringBuilder sb = new StringBuilder();
            for(char c : str.toCharArray()){
                sb.append("#");
                int shift = (c - str.charAt(0) + 26) % 26; //对于每个字母相对于首字母的位置进行序列化%计算
                sb.append(shift);
            }
            String keyStr = sb.toString(); //组成key值的字符串
            //储存进hashmap, 如果之前没有相同的序列化基础，创建一个新的空间
            if(!map.containsKey(keyStr)){
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(str); //如果存在相同的序列化基底，把当前字符串储存进对应的位置
        }

        return new ArrayList<>(map.values()); //最后把组成的所有List<String>, 组合成一个总的
    }
}
