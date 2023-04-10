/*Leetcode (Contest 340) 2616 Minimize the Maximum Difference of Pairs

You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum 
difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.

Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| 
represents the absolute value of x.

Return the minimum maximum difference among all p pairs.

Example 1:

Input: nums = [10,1,2,7,1,3], p = 2
Output: 1
Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:

Input: nums = [4,2,1,2], p = 1
Output: 0
Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= p <= (nums.length)/2
*/

/* 看到「最大化最小值」或者「最小化最大值」就要想到二分答案，这是一个固定的套路。

==> 一般来说，二分的值越大，越能/不能满足要求；二分的值越小，越不能/能满足要求，有单调性，就可以使用二分法。

首先确认左间隔，也就是左小的差异，和右间隔 最大差异

然后二分法统计，每个差异的mid值，中符合对数 paris数量 是否大于p 或者 小于 p

移动左右指针进行缩小，最后循环结束得到的就是符合题意p对数，的最小差异值
*/

class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0]; //这里是开区间

        while(left < right){ //这里是开区间
            int mid = left + (right - left) / 2;
          
            int count = 0; //count there is how many pairs Difference is less than mid value
            for(int i = 0; i < n - 1; i++){
                if(nums[i + 1] - nums[i] <= mid){
                    count++;
                    i++;
                }
            }
          
            //然后二分法统计，每个差异的mid值，中符合对数 paris数量 是否大于p 或者 小于 p
            if(count >= p){
                right = mid;
            } else{
                left = mid + 1;
            }
        }

        return left;
    }
}
