/* Leetcode (Contest 331) 2560 House Robber IV

There are several consecutive houses along a street, each of which has some money inside. 
There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.

The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.

You are given an integer array nums representing how much money is stashed in each house. 
More formally, the ith house from the left has nums[i] dollars.

You are also given an integer k, representing the minimum number of houses the robber will steal from. 
It is always possible to steal at least k houses.

Return the minimum capability of the robber out of all the possible ways to steal at least k houses.

 
Example 1:

Input: nums = [2,3,5,9], k = 2
Output: 5
Explanation: 
There are three ways to rob at least 2 houses:
- Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
- Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
- Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
Therefore, we return min(5, 9, 9) = 5.


Example 2:

Input: nums = [2,7,9,3,1], k = 2
Output: 2
Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= (nums.length + 1)/2
*/

/*
思路：

这题其实不是DP问题，属于是 maximum - minimum 问题

题意是小偷可以偷K个房间，但不能偷相邻的房间，每次已抢劫其中一个房间的最大值为结果

然后查找返回所有不同抢劫方式结果中的 “最小值” ==》 输出

==》通常 maximum - minimum 问题 都是Binary Serach的思路

观察题目 我们可以发现，这个题就是找到一个最小的数值 ==》其中有K个房间（不相邻）的房屋值是小于它的

第一种解法（solution 1): Binary Serach + Greedy Algorithm

首先找到nums[]当中的最大值和最小值，让后Binary Search进行遍历

每次找到一个mid值，使用一个boolen function (Greedy Algorithm)进行判断，其中是不是有K个房间（不相邻）的房屋值是小于它的

==> 如果有K个，说明mid值是足够大的，移动右指针再次二分查找

==> 如果没有K个，说明mid值不够大，移动左指针再次二分查找

直到left指针遇见right指针，二分结束 ==》 最终找到一个mid值，正好有K个房间的值是小于它的。==》符合题意
*/

//Solution 1: Binary Search + Greedy Algorithm
class Solution {
    //Binary Search + Greedy Algorithm
    public int minCapability(int[] nums, int k) {
        //maximum-minimum 问题
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num : nums){
            //find the max and min value of nums[] array
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        //the two ponter of Binary Serach Method
        int left = min;
        int right = max;

        //Binary Serach Method ==> travser all number element from nums[]
        while(left < right){
            //the mid value of [left, right]
            int mid = left + (right - left) / 2;
            //然后统计和检查是否有K个房间的数值是小于mid的,
            if(isPossible(nums, mid, k)){ //如果有K个，说明mid值是足够大的，移动右指针再次二分查找
                right = mid;
            } else{ //如果没有K个，说明mid值不够大，移动左指针再次二分查找
                left = mid + 1;
            }
        }
        return left; //最终找到一个mid值，正好有K个房间的值是小于它的。==》符合题意
    }

    //boolean function => used to check whether have k houses that Robber steal money
    public static boolean isPossible(int[] nums, int maxPossible, int k){
        //The Greedy Algorithm
        int lastStolenIndex = -2; // 小偷不愿意去领家偷
        for(int i = 0; i < nums.length; i++){
            //refuses to steal from adjacent homes. 小偷不愿意去领家偷
            if(nums[i] <= maxPossible && lastStolenIndex + 1 < i){
                k -= 1;
                lastStolenIndex = i; 
                if(k == 0){
                    return true; // 如果有K个，说明mid值是足够大的 返回正确
                }
            }
        }
        return false; //如果没有K个，说明mid值不够大 返回false
    }
}


/*

第二种解法（solution 2): Only Binary Search

设定左指针为1， 右指针为10……9

然后开始Binary Search运算， 每次求出一个mid值，统计有多少个房间的值是小于这个mid的

==> 如果有K个，说明mid值是足够大的，移动右指针再次二分查找

==> 如果没有K个，说明mid值不够大，移动左指针再次二分查找

直到left指针遇见right指针，二分结束 ==》 最终找到一个mid值，正好有K个房间的值是小于它的。==》符合题意

*/

//Solution 2: Only Binary Search  ************************************************************
//Time: O(nlogn)  Space: O(1)
class Solution {
    //Binary Search
    public int minCapability(int[] nums, int k) {
        int left = 1;
        int right = (int)1e9;
    
        while(left < right){
            int mid = left + (right - left) / 2;
            int count = 0; //统计有多少个房子的值是小于mid的
            for(int i = 0; i < nums.length; i++){
                if(nums[i] <= mid){
                    count += 1;
                    i++; //  refuses to steal from adjacent homes. 小偷不能偷邻家的
                }
            }
            if(count >= k){ // 如果有K个，说明mid值是足够大的，移动右指针再次二分查找
                right = mid;
            } else{
                left = mid + 1; //如果没有K个，说明mid值不够大，移动左指针再次二分查找
            }
        }

        return left;//最终找到一个mid值，正好有K个房间的值是小于它的。==》符合题意
    }
}
