/*Leetcode 390. Elimination Game

You have a list arr of all integers in the range [1, n] sorted in a strictly increasing order. Apply the following algorithm on arr:

Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
Repeat the previous step again, but this time from right to left, remove the rightmost number and every other number from the remaining numbers.
Keep repeating the steps again, alternating left to right and right to left, until a single number remains.
Given the integer n, return the last number that remains in arr.

Example 1:

Input: n = 9
Output: 6
Explanation:
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
arr = [2, 4, 6, 8]
arr = [2, 6]
arr = [6]

Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 10^9
*/

class Solution {
    /* Solution: 1 2 3 4 5 6 7 8 9 => head 1, step 1, remian 9(n)
                 , 2 , 4 , 6 , 8 , =>   2       2        4
                 , 2 , , , 6 , , , =>   2       4        2
                 , , , , , 6 , , , =>   6       6        1

    1. 从左往右进行删除时，head的变化 = head + step
    2. 从右往左进行删除时，剩余的数量remain奇数：head的变化：=> head + step
                         剩余的数量remain偶数：head的变化：=> head不变
    3. 每一次的删除，数字之间的距离都会增加一倍，因此step每次的变化都乘以2
    */
    public int lastRemaining(int n) {
        int head = 1; //开头的第一个数字
        int step = 1; //数字之前的距离
        int remain = n; //剩余数字的数量

        boolean isLeft = true; // 标记是从左 还是 从右

        //main travser
        while(remain != 1){
          
          //如果从左往右删 || 从右往左，剩余数字的个数为奇数
            if(isLeft || (!isLeft && remain % 2 != 0)){
                head = head + step; //第一个数字会发生变化
            } //从右往左，剩余数字的个数为偶数时，head不发生改变
          
            step *= 2;
            remain /= 2; //都是间隔一个删除，所以当前数量的一半数字被删除
            isLeft = !isLeft; //从左 从右交替开始删除
        }

        return head; //剩余的一个数字时，也就是head
    }
}
