/*Leetcode 798. Smallest Rotation with Highest Score

You are given an array nums. You can rotate it by a non-negative integer k so that the array becomes [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]]. 
Afterward, any entries that are less than or equal to their index are worth one point.

For example, if we have nums = [2,4,1,3,0], and we rotate by k = 2, it becomes [1,3,0,2,4]. This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].
Return the rotation index k that corresponds to the highest score we can achieve if we rotated nums by it. If there are multiple answers, return the smallest such index k.

Example 1:

Input: nums = [2,3,1,4,0]
Output: 3
Explanation: Scores for each k are listed below: 
k = 0,  nums = [2,3,1,4,0],    score 2
k = 1,  nums = [3,1,4,0,2],    score 3
k = 2,  nums = [1,4,0,2,3],    score 3
k = 3,  nums = [4,0,2,3,1],    score 4
k = 4,  nums = [0,2,3,1,4],    score 3
So we should choose k = 3, which has the highest score.

Example 2:

Input: nums = [1,3,0,2,4]
Output: 0
Explanation: nums will always have 3 points no matter how it shifts.
So we will choose the smallest k, which is 0.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] < nums.length
*/

/*思路：

此题是根据 nums[index] <= index 规则，轮转 0 - n-1 次，可以得到的最大积分是多少
  
  Example: nums = [2,3,1,4,0] => 得分的前提是下标不小于值，  所以 当nums[index] = 2的时候
  
  1. 轮转最大的步数情况 来得分的前提： 是它的 index 要移动到 2 的位置及以上
    =》 对于任意元素值来说，它移动 到nums[index]代表的index位置处的通用公式 为 (当前位置 - 目标位置 + n) % n，
  
  2. 同时因为， 0 <= nums[i] < nums.length， 所有的元素值都是小于 n 的，
    =》所以，移动到 (n-1) 的位置就是它可能移动的最少的次数，同时我们要防止越界，移动 i + 1次就是可能的最小步数
    =》 轮转最小的步数情侣来得分的前提是：i + 1次
   
   ==> 我们就可以求得步数的范围：（index - nums[index] + n） % n <= k(step) <= (index + 1) % n

 3. 我们再把每个数字对应的 k 的范围都求出来， 把它们重合的次数最多的最小的 k 返回就是答案啦
  */
//Time: O(n)   Space: O(n)
class Solution {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        //构造差分数组
        int[] diff = new int[n];
       
        //main for-loop 遍历所有数字，找到每个数字可能的存在的最大轮转步数 和 最小的轮转步数
        for(int i = 0; i < n; i++){
            //可能的存在的最大的轮转步数： (当前位置 - 目标位置 + n) % n
            int max = (i - nums[i] + n) % n;
            //可能的存在的最小的轮转步数：（i + 1）% n => 不能越界 0 <= nums[i] < nums.length， 
            int min = (i + 1) % n;
            //统计差分数组的值
            diff[min] += 1;
            //如果max不是最后一个数值，则需要减去，取保只在【min, max】区间进行了加值
            if(max + 1 < n){
                diff[max + 1] -= 1;
            }

            //可能存在min > max的情况，类似于 3~1 这种循环分成了两段，一段是3~4，一段是0~1，把0的位置补上一个1即可
            if(min > max){
                diff[0] += 1;
            }
        }

        int maxScore = diff[0];
        int currScore = diff[0];
        int res = 0;
        for(int i = 1; i < diff.length; i++){
            currScore += diff[i];
            if(currScore > maxScore){ //比较是否获得新的更大积分
                maxScore = currScore;
                res = i; //这里的index，就是轮转的index
            }
        }

        return res;
    }
}
