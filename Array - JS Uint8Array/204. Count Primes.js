/* 204. Count Primes

Given an integer n, return the number of prime numbers that are strictly less than n.

Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10; they are 2, 3, 5, 7.

Example 2:

Input: n = 0
Output: 0

Example 3:

Input: n = 1
Output: 0
 

Constraints:

0 <= n <= 5 * 10^6
*/

/**
 * @param {number} n
 * @return {number}
 */
var countPrimes = function(n) {
    let seen = new Uint8Array(n);  // 如果是大量连续读写的数组，使用 Uint8Array 代替 array，可以减少内存消耗，和额外的fill(0)
    let ans = 0;
    // 1不是prime number，所以从2开始
    for(let num = 2; num < n; num++){
        if(seen[num]){
            continue; // 如果seen里面已经有了，表示这个数可以被其他数整除，所以不是prime number
        }
        ans += 1; // 如果没有，则是prime number
        for(let next = num * num; next < n; next += num){
            // console.log(next); => 4 6 8 9
            seen[next] = 1;
        }
    }

    return ans;
};
