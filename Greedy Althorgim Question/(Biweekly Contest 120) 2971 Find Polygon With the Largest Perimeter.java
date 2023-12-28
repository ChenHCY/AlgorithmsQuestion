/* 2971. Find Polygon With the Largest Perimeter

You are given an array of positive integers nums of length n.

A polygon is a closed plane figure that has at least 3 sides. The longest side of a polygon is smaller than the sum of its other sides.

Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak, then there always exists a polygon with k sides whose lengths are a1, a2, a3, ..., ak.

The perimeter of a polygon is the sum of lengths of its sides.

Return the largest possible perimeter of a polygon whose sides can be formed from nums, or -1 if it is not possible to create a polygon.


Example 1:
Input: nums = [5,5,5]
Output: 15
Explanation: The only possible polygon that can be made from nums has 3 sides: 5, 5, and 5. The perimeter is 5 + 5 + 5 = 15.

Example 2:
Input: nums = [1,12,1,2,5,50,3]
Output: 12
Explanation: The polygon with the largest perimeter which can be made from nums has 5 sides: 1, 1, 2, 3, and 5. The perimeter is 1 + 1 + 2 + 3 + 5 = 12.
We cannot have a polygon with either 12 or 50 as the longest side because it is not possible to include 2 or more smaller sides that have a greater sum than either of them.
It can be shown that the largest possible perimeter is 12.

Example 3:
Input: nums = [5,5,50]
Output: -1
Explanation: There is no possible way to form a polygon from nums, as a polygon has at least 3 sides and 50 > 5 + 5.
 

Constraints:
3 <= n <= 10^5
1 <= nums[i] <= 10^9
*/
//Time: O(nlogn)
//Space: O(1)
class Solution {
    // 贪心算法: 两边之和大于第三边
    // 所以首先排序之后，从右往左遍历，枚举每一个最长边，找到前缀和最大的一种情况
    // 这种也就是能组成的最长周长的多边形
    public long largestPerimeter(int[] nums) {
        //首先进行排序
        Arrays.sort(nums);
        long sum = 0;
        // 因为我们从后往前遍历，且前缀和越打，周长越大
        // 所以我们直接计算nums的总和，再从后往前枚举
        for(int n : nums){
            sum += n; //得到nums的总和
        }

        //从后往前遍历
        for(int i = nums.length - 1; i >= 0; i--){
            int curr = nums[i];
            //如果遇到的第一个 当前值的前缀和，大于当前值的情况 ==> 即是我们要的最长周长
            if(sum > curr * 2){
                return sum; //sum是[0, i]的总和
            }
            sum -= curr; //每次减去当前值
        }
        return -1; //如果找不到满足要求的多边形，输出-1
    }
}
