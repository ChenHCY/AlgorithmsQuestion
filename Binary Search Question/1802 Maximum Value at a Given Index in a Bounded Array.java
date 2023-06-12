/* Leetcode 1802. Maximum Value at a Given Index in a Bounded Array

You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

· nums.length == n
· nums[i] is a positive integer where 0 <= i < n.
· abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
· The sum of all the elements of nums does not exceed maxSum.
· nums[index] is maximized.
· Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.

Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].

Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3
 

Constraints:

1 <= n <= maxSum <= 10^9
0 <= index < n
*/

/* 思路： 此题是找到nums[index]可能的最大值，来满足这些要求

因为每个相邻的数的差都是+1或者-1，所以如果想让nums[index]的值最大，nums[]一定是一个山峰的走势，
nums[index] 是最大值，然后向两边每次减1的缩小，直到值为1，不在改变

根据等差数列求和公式：元素和是 (首项(max） + 末项(min) ) * 项数 / 2

所以我们可以看成两个等差数列进行求和，然后根据这个总和进行判断是否超过maxSum

左指针和右指针表示的是nums[index]可能的区间，通过二分法进行缩小
*/
//Time: O(log M) M = maxSum,  Space: O(1)
class Solution {
    // 根据题意，需要构造一个长度为 n 的数组 nums，所有元素均为正整数，
    // 数组总和不超过maxSum，相邻元素之差不超过 1，且需要最大化 nums[index]。
    public int maxValue(int n, int index, int maxSum) {
        //Binary Search: left: 1 / rigt: maxSum 
        int left = 1;
        int right = maxSum;

        while(left < right){
          //当遇到二分法求最大化值或者最小化值的时候，
          //因为二分法是向下取整的，所以如果是left = mid 的情况，时left + (right - left + 1) / 2;
          //                         如果是left = mid + 1 的情况，时left + (right - left) / 2;
            int mid = left + (right - left + 1) / 2;
            
            // used the mid be nums[index] value
            // and count the curr value of nums[]
            long totalSum = arrSum(mid, index, n);
            
            if(totalSum <= maxSum){
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
    //这里的number表示里面数字的个数， maxValue是这段array里面的最大值， n表示整个array的数量
    private long arrSum(int maxValue, int number, int n){
        long res = sum(maxValue - 1, number) + sum(maxValue, n - number);
        System.out.println(res);
        return res;
    }

    //计算nums[index]单侧(左侧 or 右侧)的总和z, axValue是这段array里面的最大值， 这里的number表示里面数字的个数
    private long sum(long maxValue, int number){
        //因为每个数字减去1，所以两侧都是以nums[index]为最大值的等差数列
        //根据等差数列推导公式，一个等差数列的总和 = （max + min）* 数量 / 2
        
        //但也存在有多个1的情况，所以要在这里判断 maxValue 是否 大于 number(数量中的数量)
        if(maxValue < number){ //存在多个1的情况
            //先算出递减到 1 的元素和为 (1 + x) * x / 2, 其余余元素个数为 （number - maxValue）* 1(每个元素值) 
            return ((maxValue + 1) * maxValue) / 2 + (number - maxValue);
        }
        return ((maxValue + (maxValue - number + 1)) * number) / 2;
    }
}

