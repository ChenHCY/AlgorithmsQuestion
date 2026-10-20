/* 318. Maximum Product of Word Lengths

Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.


Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.


*/

/**
 * @param {string[]} words
 * @return {number}
 */

// 根据不同的string 使用 ASCII 和 <<  转换成 一个 32位的mask  
// 1 << c 表示把数字 1 向左移动 c 位。例如，如果 c = 2（对应字母 'c'），1 << 2 的二进制就是 0000 0100。
// |= 是按位或赋值操作。它把字母对应的二进制位设置为 1。
// 最终效果：一个 32 位的整数 mask 就变成了一个“布尔数组/集合”。从右往左的第 c 位如果是 1，就代表这个单词里包含第 c 个字母；如果是 0，则不包含。
var maxProduct = function(words) {
    const masks  = words.map((word) => {
        let mask = 0;
        
        for(let i = 0; i < word.length; i++){
            const currASC = word.charCodeAt(i) - 97; //  97 ~ 122：字符 'a' ~ 'z'（对应十进制编码 97 ~ 122）
            mask |= (1 << currASC); // 把数字 1 向左移动 c 位
        }

        return mask;
    })

    let ans = 0;

    for(let i = 0; i < words.length - 1; i++){
        for(let j = i + 1; j < words.length; j++){
            if(masks[i] & masks[j]){ // Bitwise AND (&) "都为1 的时候结果才是1 => 有重复的字母
                continue;
            }

            const currLength = words[i].length * words[j].length;
            ans =  Math.max(ans, currLength);
        }
    }
    return ans;
};
