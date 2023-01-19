/* Leetcode 974. Subarray Sums Divisible by K

Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:

Input: nums = [5], k = 9
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
*/

 // 如果任意两个数字除以K得到的余数相同，那么两数之差一定可以被K整除。
// 对于数组任意一个连续子数组的和sum[i1, i2](下标i1到i2之和)应该等于：
// ==》 sum[i1, i2] = sum[0, i2] - sum[0, i1]; (前缀和的方式)
// ==》所以nums数组中任意一个子数组的和，都可以由上面相应的2个sum之差来取得
//==》同时根据余数定理，所有sum分别除以K，余数相同的sum之差是可以被K整除的
// ==》这题的形式就换成了：寻找余数相同的个数 ==》 用HashMAp来存储每个数字对应的余数

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
     
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0; // used for count the remainder of every element in nums[] array
        int count = 0; // count the how many subarrays sums divisibe by k

        map.put(0, 1); //if the remainder is 0, it means the subarrays that have a sum divisible by k

        for(int num : nums){
            sum = (sum + num) % k; // get the remainder(余数) of every element in nums[] array

            if(sum < 0){ //if sum is less than 0
                sum += k; //change the negative to be positive
            }

            //同时根据余数定理，所有sum分别除以K，余数相同的sum 都是可以被K整除的
            count += map.getOrDefault(sum, 0); //check and find how many numbers of the value with same remainder
            //renew the hashmap numbers
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
