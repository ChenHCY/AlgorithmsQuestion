/* 451. Sort Characters By Frequency

Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of 
a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 
Constraints:
1 <= s.length <= 5 * 10^5
s consists of uppercase and lowercase English letters and digits.
*/
//Time: O(3*n): 3个for-loop, 每一个都是遍历每个element  
//Space: O(2*n): 开了一个hashmap 一个treemap
class Solution {
    // 使用TreeMap + HashMap来记录每个字母出现的次数
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        TreeMap<Integer, List<Character>> treemap = new TreeMap<>(Collections.reverseOrder()); //按照key值的大小递减排列
        
        //计算每个字母出现的频率
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            // 如果之前没有这个频率的字母，开一个新空间
            if(!treemap.containsKey(entry.getValue())){
                treemap.put(entry.getValue(), new ArrayList<>());
            }
            treemap.get(entry.getValue()).add(entry.getKey()); //把字母加入对应频率list中
        }

        StringBuilder sb = new StringBuilder(); //组成新的字符串
        for(Map.Entry<Integer, List<Character>> entry : treemap.entrySet()){
            int times = entry.getKey(); //出现的次数
            List<Character> charList = entry.getValue(); //当前频率的字母集合
            for(int i = 0; i < charList.size(); i++){
                sb.append(String.valueOf(charList.get(i)).repeat(times));//把当前集合中的每个字母都加入到新的机会
            }
        }

        return sb.toString();
    }
}
