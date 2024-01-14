/* 1657. Determine if Two Strings Are Close

Two strings are considered close if you can attain one from the other using the following operations:

  · Operation 1: Swap any two existing characters.
    · For example, abcde -> aecdb

  · Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
    · For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)

You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

Example 1:
Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"

Example 2:
Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.

Example 3:
Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"
 

Constraints:
1 <= word1.length, word2.length <= 10^5
word1 and word2 contain only lowercase English letters.
*/

// 思路：因为两类操作都不会凭空产生或删除字符，而仅仅是对字符进行互换。
// 并且由于操作 1 和 2 都不限次数
// 所以我们只需要判断word1和word2：「字符种类是否相同」和「字母的频次集合是否相等」

//Time: O(n + m) => O(2N)  Space: O(N + M) => O(2N) : n is the length of word1 and word2
class Solution {
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()){
            return false;
        }

        if(word1.equals(word2)){
            return true;
        }

        int[] w1 = new int[26]; //统计word1的字母频率
        for(int i = 0; i < word1.length(); i++){
            w1[word1.charAt(i) - 'a']++;
        }

        int[] w2 = new int[26]; //统计word2的字母频率
        for(int i = 0; i < word2.length(); i++){
            w2[word2.charAt(i) - 'a']++;
        }

        //首先检查字母种类是否都一样
        for(int i = 0; i < w1.length; i++){
            if(w1[i] == 0 && w2[i] == 0){
                continue;
            }

            if(w1[i] == 0 || w2[i] == 0){
                return false;
            }
        }

        //检查所有字母的频率是否一样
        Arrays.sort(w1);
        Arrays.sort(w2);

        for(int i = 0; i < w1.length; i++){
            if(w1[i] != w2[i]){
                return false;
            }
        }

        return true;
    }
}
