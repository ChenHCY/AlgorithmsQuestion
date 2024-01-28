/*Leetcode 187. Repeated DNA Sequences

The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

· For example, "ACGAATTCCG" is a DNA sequence.
· When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.


Example 1:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]

Example 2:
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
 

Constraints:
1 <= s.length <= 10^5
s[i] is either 'A', 'C', 'G', or 'T'.
*/
// 时间复杂度：每次检查以 s[i] 为结尾的子串，需要构造出新的且长度为 10 的字符串。令 C=10，复杂度为 O(n∗C)
// 空间复杂度：长度固定的子串数量不会超过 n 个。复杂度为 O(n) 
class Solution {
    // 滑动窗口 + HashMap
    // 从左往右遍历，记录每一个长度为10的字符串 出现的次数
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i + 10 <= s.length(); i++){
            String window = s.substring(i, i + 10);
            map.put(window, map.getOrDefault(window, 0) + 1);
            //判断出现的次数是否超过一次
            if(map.get(window) > 1){
               set.add(window);
            }
        }

        return new ArrayList(set);
    }
}
