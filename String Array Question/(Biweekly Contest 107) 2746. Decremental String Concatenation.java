/*Leetcode (Biweekly Contest 107) 2746. Decremental String Concatenation

Let's define a join operation join(x, y) between two strings x and y as concatenating them into xy. However, if the last character of x is equal to the first character of y, one of them is deleted.

For example join("ab", "ba") = "aba" and join("ab", "cde") = "abcde".

You are to perform n - 1 join operations. Let str0 = words[0]. Starting from i = 1 up to i = n - 1, for the ith operation, you can do one of the following:

    · Make stri = join(stri - 1, words[i])
    · Make stri = join(words[i], stri - 1)

Your task is to minimize the length of strn - 1.

Return an integer denoting the minimum possible length of strn - 1.

Example 1:

Input: words = ["aa","ab","bc"]
Output: 4
Explanation: In this example, we can perform join operations in the following order to minimize the length of str2: 
str0 = "aa"
str1 = join(str0, "ab") = "aab"
str2 = join(str1, "bc") = "aabc" 
It can be shown that the minimum possible length of str2 is 4.

Example 2:

Input: words = ["ab","b"]
Output: 2
Explanation: In this example, str0 = "ab", there are two ways to get str1: 
join(str0, "b") = "ab" or join("b", str0) = "bab". 
The first string, "ab", has the minimum length. Hence, the answer is 2.

Example 3:

Input: words = ["aaa","c","aba"]
Output: 6
Explanation: In this example, we can perform join operations in the following order to minimize the length of str2: 
str0 = "aaa"
str1 = join(str0, "c") = "aaac"
str2 = join("aba", str1) = "abaaac"
It can be shown that the minimum possible length of str2 is 6.
 
Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 50
Each character in words[i] is an English lowercase letter
*/

class Solution {
    // 此题是对于一个长度为n的字符串数组，执行n - 1 次合并, 如果合并的连接处有相同的字母则消除
    // 最后找到能组成的最短字符串长度
    HashMap<String, Integer> map = new HashMap<>();

    //dfs(i, first, last)的定义为，在words列表中，从第i+1个字符串到最后一个字符串 的字符串列表 
    // 与「起始字符为first，结束字符为last」字符 join 时，能够贡献多少的最小join长度。

    public int minimizeConcatenatedLength(String[] words) {
        String word = words[0];
        return dfs(words, 0,  word.charAt(0), word.charAt(word.length() - 1)) + word.length();
    }

    // ["cb","ac","a","b","a","abb","a","caa","aa"]
    //dfs(i, first, last)  first是当前字符串的开头字母, last是当前字符串的最后一个字母 
    public int dfs(String[] words, int index, char first, char last){
        //exit condition
        if(index == words.length - 1){
            return 0; // 如果运行到了words[]数组的最后一个字符串
        }
        // 用index, first字符串, last字符串, 创建一个新的字符串 
        String key = index + "," + first + "," + last;
        if(map.containsKey(key)){
            return map.get(key); //返回之前已经储存在hashmap中从first字符串到last字符串能组成的最小字符串长度
        }

        //开始进行合并
        char[] nexts = words[index + 1].toCharArray(); //把第index+1个String字符串的字母组成array
        int minLen = Integer.MAX_VALUE;

        //如果当前字符串的最后一个字母 和 下一个字符串的第一个字母一样，且 拼接在一起时，则需要消除
        //例子："ac"(index) + "cb""(index + 1) 拼接，拼接处字符相同时，贡献长度 就是 words[index + 1] "ac" 的长度减1
        if(last == nexts[0]){ //每次传递到下一层时，要renew新的开头字母和结尾字母
            minLen = Math.min(minLen, dfs(words, index + 1, first, nexts[nexts.length - 1]) + nexts.length - 1);
        } else{
        //如果当前字符串的最后一个字母 和 下一个字符串的第一个字母一样，但“没有”拼接在一起时，则不用消除
        //例子："ac"(index) + "cb"(index + 1) 拼接，拼接处字符不相同时，贡献长度 就是 words[index + 1] "ac" 的长度
            minLen = Math.min(minLen, dfs(words, index + 1, first, nexts[nexts.length - 1]) + nexts.length);
            //每次传递到下一层时，要renew新的开头字母和结尾字母
        }

        //如果当前字符串的第一个字母 和 下一个字符串的最后一个字母一样，且拼接在一起时，则需要消除
         //例子： "ac" + "cb" 拼接，拼接处字符相同时，贡献长度就是 words[index + 1]  "cb"的长度减1
        if(first == nexts[nexts.length - 1]){
            minLen = Math.min(minLen, dfs(words, index + 1, nexts[0], last) + nexts.length - 1);
        } else{
          //如果当前字符串的第一个字母 和 下一个字符串的最后一个字母一样，但“没有”拼接在一起时，则需要消除
          //例子： "ac" + "cb" 拼接，拼接处字符不相同时，贡献长度就是 words[index + 1]  "cb"的长度
            minLen = Math.min(minLen, dfs(words, index + 1, nexts[0], last) + nexts.length);
        }
        
        //然后把得到的长度和对应的字符串 存入到HashMap中 方便后续查找
        map.put(key, minLen);

        return minLen; //输出最小的字符串长度
    }
}
