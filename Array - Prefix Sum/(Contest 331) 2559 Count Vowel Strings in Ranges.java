/* (Contest 331) 2559. Count Vowel Strings in Ranges

You are given a 0-indexed array of strings words and a 2D array of integers queries.

Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.

Return an array ans of size queries.length, where ans[i] is the answer to the ith query.

Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

Example 1:

Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].


Example 2:

Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].
 

Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 40
words[i] consists only of lowercase English letters.
sum(words[i].length) <= 3 * 105
1 <= queries.length <= 105
0 <= li <= ri < words.length
*/

/*
 思路： 正常一层一层找 会超出时间
 
 所有要使用前缀和的思路：==》 统计和储存每个words[]中string字符串 前面有多少个元音字符串
 
 然后在按照int[][] queries 进行查找： ==》 queries是一个左闭右闭范围，start和end点的字符串都需要算进去
 
 第一种情况，queries的开始点为0：那么这个范围的元音字符串就是prefix[end];
 
 第二种情况，queries的开始点不为0： 那么这个范围的元音字符串就是prefix[end] - prefix[start - 1];

*/

class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int times = 0;
        int[] prefix = new int[words.length];

        //prefix Sum 前缀和 ==> 计算和储存每个元素之前有多少的元音字母
        for(int i = 0; i < words.length; i++){
            if(isVowel(words[i])){
                times += 1;
            }
            prefix[i] = times; // 计算和储存每个元素之前有多少的元音字母
        }

        int index = 0;
        int[] res = new int[queries.length];

        //then travser all the array from queries and check vowels
        for(int[] arr : queries){
            int start = arr[0];
            int end = arr[1];
            if(start == 0){
                res[index] = prefix[end];
            } else{
                res[index] = prefix[end] - prefix[start - 1];
            }
            index++;
        }

        return res;
    }

    //small function to check whether the string words is vowels
    public boolean isVowel(String str){
        String vowel = "aeiou";
        if(vowel.contains(str.charAt(0) + "") && vowel.contains(str.charAt(str.length() - 1) + "")){
            return true;
        }
        return false;
    }
} 
