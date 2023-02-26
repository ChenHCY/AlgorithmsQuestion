/* Contest 334 2575. Find the Divisibility Array of a String

You are given a 0-indexed string word of length n consisting of digits, and a positive integer m.

The divisibility array div of word is an integer array of length n such that:

div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or
div[i] = 0 otherwise.
Return the divisibility array of word.

Example 1:

Input: word = "998244353", m = 3
Output: [1,1,0,0,0,1,1,0,0]
Explanation: There are only 4 prefixes that are divisible by 3: "9", "99", "998244", and "9982443".

Example 2:

Input: word = "1010", m = 10
Output: [0,1,0,1]
Explanation: There are only 2 prefixes that are divisible by 10: "10", and "1010".
 

Constraints:

1 <= n <= 105
word.length == n
word consists of digits from 0 to 9
1 <= m <= 109
*/

/* 思路：

这题是计算从0开始的字符串数，有哪些能被m整除

所以重要的问题是，如果我们尝试从每个数字都从0-index开始来组成单词数字，

那么 long 一定溢出并导致形成错误的数字。因此，我们将跟踪最后一个数字的余数，而不是每个数字都从0-index开始来组成新的数字。

Example:

word = "342" 和 m = 3，

我们遍历word 然后找到数字并进行%，然后记录余数，move到下一个digit

第一步：3 % 3 = 0 ==》 可以被m整除 【1】
现在余数：0；

第二步：0 X 10 + 4 = 4   ==》 4 % 3 = 1 ==》 不能被m整除 【1，0】
现在我们有余数 1

第三步：1 X 10 + 2 = 12  ==》 12 % 3 = 0 ==》 可以被m整除 【1， 0， 1】

所以通过这个方法我们可以得到相同的结果， 【1， 0， 1】

所以我们将使用这种方法，每次只传递当前除以m后得到的余数

而不是每次都从0-index开始创建新数，==》避免了溢出long的范围 从而形成错误
*/

//Time: O(n)  Space: O(n)
class Solution {
    public int[] divisibilityArray(String word, int m) {
        int[] res = new int[word.length()];
        long num = 0;
        
        for(int i = 0; i < word.length(); i++){
            int numDigit = word.charAt(i) - '0'; //get every character number digit from string word
            num = (num * 10 + numDigit) % m; // 每次只得到最后一个数字的余数，避免long类型的溢出
            //检查能否被m整除
            if(num == 0){ //可以 返回 1
                res[i] = 1;
            } else{ //不行 返回 0
                res[i] = 0;
            }
        }
        
        return res;
    }
}
