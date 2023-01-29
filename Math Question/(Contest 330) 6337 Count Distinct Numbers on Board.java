/* Leetcode (Contest 330) 6337 Count Distinct Numbers on Board

You are given a positive integer n, that is initially placed on a board. Every day, for 109 days, you perform the following procedure:

For each number x present on the board, find all numbers 1 <= i <= n such that x % i == 1.
Then, place those numbers on the board.
Return the number of distinct integers present on the board after 109 days have elapsed.

Note:

Once a number is placed on the board, it will remain on it until the end.
% stands for the modulo operation. For example, 14 % 3 is 2.


Example 1:

Input: n = 5
Output: 4
Explanation: Initially, 5 is present on the board. 
The next day, 2 and 4 will be added since 5 % 2 == 1 and 5 % 4 == 1. 
After that day, 3 will be added to the board because 4 % 3 == 1. 
At the end of a billion days, the distinct numbers on the board will be 2, 3, 4, and 5. 


Example 2:

Input: n = 3
Output: 2
Explanation: 
Since 3 % 2 == 1, 2 will be added to the board. 
After a billion days, the only two distinct numbers on the board are 2 and 3. 


Constraints:

1 <= n <= 100

*/

//My Solution:

/* 思路

HashSet可以去重，把题目的规则用for-loop和if条件 进行计算

然后加入到hashset中，最后返回hashset的大小 即使满足题意的board中不同数字的数量


*/

class Solution {
    public int distinctIntegers(int n) {
        //used HashSet to remove the duplicate number
        Set<Integer> set = new HashSet<>();
        set.add(n); //add the start number
        
        for(int i = 0; i < 109; i++){
            Set<Integer> nextElement = new HashSet<>();
            for(int num : set){ //check every numver in the board
                for(int j = 1; j <= num; j++){
                    if(num % j == 1){ // find how many number between 1 - n than can % i == 1
                        nextElement.add(j); //add into the board
                    }
                }
            }
            set.addAll(nextElement); //add into the board
        }
        
        return set.size();
        
    }
}


//The Best Solution: Time: O(1) Space: O(1)  
/*

“10^9 天”，这是相当长的时间。并且n比天数小得多。

持续时间足够长，所有其实可以生成所有卡片。

假设n在棋盘上，
n % (n - 1) == 1
如果n > 1，
那么n - 1将在棋盘上，
那么n - 2将在棋盘上，
同样的n - 3, n - 4.... 3, 2。都会在棋盘上

所以对于任何人n > 1，
2,3,4...n最终都会在棋盘上，
所有对于n>1的情况，只需要返回n - 1

因为n = 1的情况，什么都不会发生，
我们只用返回 1就好。

*/

public int distinctIntegers(int n) {
        return Math.max(n - 1, 1);
}
