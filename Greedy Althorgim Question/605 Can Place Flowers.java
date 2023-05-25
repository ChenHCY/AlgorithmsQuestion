/* Leetcode 605. Can Place Flowers

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, 
return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true

Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 

Constraints:

1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length
*/

/* 思路：检查在一个花坛 flowerbed中，能否再种 n 朵新花 ==> 条件：相邻的格子不能连续种花

    从左往右进行遍历，贪心算法的思想，我们只需要找到在满足条件下，最多能种多少花的
    ==》然后和n进行比较，如果大于n 输出true || 小于n, 输出false
           
   因为是从左往右进行遍历查找，相邻格子不能连续种花，所以我们只需要考虑当前index右边的情况
           1. 当前index为0：没有种花
                ==> 1. index + 1 = 0, 下一个格子也没有种花，这种情况index的位置是可以种新的花
                ==> 2. index + 1 = 1, 下一个格子种了花，相邻格子不能连续种花，所以index, index + 2都不能种新的花
           2. 当index为1：这个格子种了花
                ==> 1. index + 1 = 0, 因为相邻格子不能连续种花，所以index + 1也不能种新的花
                ==> 2. index + 1 = 1, 因为相邻格子不能连续种花 ==> 不存在这种情况
 */

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int index = 0;
        while(index < len){
            //当前index为0：没有种花
            if(flowerbed[index] == 0){
                //如果到达这个flowerbed的顶端，则直接种
                if(index == len - 1){
                    n -= 1;
                    break; //到达顶端后，终止循环，完成遍历
                }
                //下一个格子也没有种花，这种情况index的位置是可以种新的花
                else if(flowerbed[index + 1] == 0){
                    n -= 1; //种了一朵新的花
                    index += 2; //移动index 继续检查
                } else if(flowerbed[index + 1] == 1){
                    //下一个格子种了花，相邻格子不能连续种花
                    index += 3; //index, index + 2都不能种新的花
                }
            } else if(flowerbed[index] == 1){ //当index为1：这个格子种了花
                //这种情况下, index + 1都不可能再种新的花
                index += 2;
            }
        }

        if(n > 0){ //如果还有剩余的花没有种完，输出false
            return false;
        }

        return true;
    }
}
