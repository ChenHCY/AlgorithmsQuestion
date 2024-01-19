/* 2217. Find Palindrome With Fixed Length

Given an integer array queries and a positive integer intLength, return an array answer where answer[i] 
is either the queries[i]th smallest positive palindrome of length intLength or -1 if no such palindrome exists.

A palindrome is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.

Example 1:
Input: queries = [1,2,3,4,5,90], intLength = 3
Output: [101,111,121,131,141,999]
Explanation:
The first few palindromes of length 3 are:
101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
The 90th palindrome of length 3 is 999.

Example 2:
Input: queries = [2,4,6], intLength = 4
Output: [1111,1331,1551]
Explanation:
The first six palindromes of length 4 are:
1001, 1111, 1221, 1331, 1441, and 1551.
 
Constraints:
1 <= queries.length <= 5 * 10^4
1 <= queries[i] <= 10^9
1 <= intLength <= 15
*/
/**
 * @param {number[]} queries
 * @param {number} intLength
 * @return {number[]}
 */
var kthPalindrome = function(queries, intLength) {
    const mid = Math.ceil(intLength / 2); // 3 
    const base = Math.pow(10, mid - 1); // 10
    const res = new Array(queries.length).fill(-1); //答案数组长度为queries的长度，初始值为-1；

    for(let i = 0; i < queries.length; i++){
        const index = queries[i] - 1;
        const preHalf = base + index + ''; //回文的前半部分
        let secondHalf = '';

        //两种情况，一种是intLength回文长度为单数 另一种是intLength回文长度为双数
        if(intLength % 2 == 1){ //单数，中间位不用反转
            secondHalf = preHalf.slice(0,-1).split('').reverse().join('');;
        } else{ //双数 前半部分全部反转，
            secondHalf = preHalf.slice().split('').reverse().join('');
        }
        //组合回文
        const palidromic = preHalf + secondHalf;
        if(palidromic.length === intLength){
            res[i] = +(palidromic);
        }
    }
    return res;
};
