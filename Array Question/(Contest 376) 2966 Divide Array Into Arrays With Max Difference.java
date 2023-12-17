/*Leetcode 2966. Divide Array Into Arrays With Max Difference

You are given an integer array nums of size n and a positive integer k.

Divide the array into one or more arrays of size 3 satisfying the following conditions:

    · Each element of nums should be in exactly one array.
    · The difference between any two elements in one array is less than or equal to k.

Return a 2D array containing all the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return any of them.

Example 1:
Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
Output: [[1,1,3],[3,4,5],[7,8,9]]
Explanation: We can divide the array into the following arrays: [1,1,3], [3,4,5] and [7,8,9].
The difference between any two elements in each array is less than or equal to 2.
Note that the order of elements is not important.

Example 2:
Input: nums = [1,3,3,2,7,3], k = 3
Output: []
Explanation: It is not possible to divide the array satisfying all the conditions.
 

Constraints:
n == nums.length
1 <= n <= 10^5
n is a multiple of 3.
1 <= nums[i] <= 10^5
1 <= k <= 10^5
*/
//Time: O(n * logn)  Space: O(1)
class Solution {
    // 把nums分成若干个长度为3的子数组，保证每个子数组任意两个数字的差不超过k
    // 因为对于顺序没有要求，所以我们可以直接进行排序，然后把最接近的三个数分在一个子数组
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // Time: O(n * logn)  
        int[][] res = new int[n / 3][3]; // 因为所有子数组都是长度为3，所以n/3表示有多少个长度为3的子数组

        //遍历整个nums[], 从左往右的每个长度为3的子数组，检查差值是否都小于k
        for(int i = 2; i < n; i+=3){
            if(nums[i] - nums[i - 2] > k){
                return new int[][]{}; //如果存在任意两个数差值大于k, 表示无法满足要求，输出空集
            }
            //i / 3 可以得到这是第几个子数组
            res[i / 3] = new int[]{nums[i - 2], nums[i - 1], nums[i]};
        }

        return res;
    }
}
