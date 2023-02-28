/* Leetcode 875. Koko Eating Bananas

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. 

The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. 

If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23
 

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
*/

/* 题意是找到在规定时间 h 内, koko能吃完香蕉（piles[]）的最小速度 k

这也是一个二分法的问题，我们可以找到f(x), x, 和 H

koko吃香蕉的速度 speed k 就是自变量 x，吃完所有香蕉所需的时间就是单调函数 f(x)，target 就是吃香蕉的时间限制 h。

设定左指针是speed的小区间，右指针是speed的大区间

然后进行二分查找，mid = left + (right - left) / 2;

每次得到的mid值，也就是koko的速度，然后通过一个function 计算这个速度需要的时间

==》 再和要求的时间h进行比较

===》小于这个时间，//所用的时间比要求时间少，代表speed是足够大的，缩小右指针
===》大于这个时间 /所用的时间多于要求时间，表示这个速度在规定时间是吃不完香蕉的，缩小左指针

==》最后结束循环， 就是 在要求时间能够吃完香蕉的最小speed

*/
//Time: O(nlongm) m is the length of piles
//Space: O(1)
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //int k = 0; //koko吃香蕉的speed
        int left = 1; //速度的小区间
        int right = 0;  //速度的大区间

        for(int max : piles){ //koko吃香蕉的最大speed
            right = Math.max(right, max);
        }

        while(left < right){
            int mid = left + (right - left) / 2;
            int usedTime = countHours(piles, mid); //得到这个速度吃完香蕉需要的时间

            //然后和要求的时间进行比较，找到在要求时间能够吃完香蕉的最小speed
            if(usedTime <= h){ //所用的时间比要求时间少，代表speed是足够大的，缩小右指针
                right = mid;
            } else{ //所用的时间多余要求时间，表示这个速度在规定时间是吃不完香蕉的，缩小左指针
                left = mid + 1;
            }
        }

        return left;
    }

    //Function used to chech whethereating speed of k such that she can eat all the bananas within h hours.
    public static int countHours(int[] piles, int k){
        int hours = 0;
        for(int i = 0; i < piles.length; i++){
            if(piles[i] % k == 0){
                hours += piles[i] / k;
            } else{
                hours += (piles[i] / k) + 1;
            }
        }
        return hours;
    }
}
