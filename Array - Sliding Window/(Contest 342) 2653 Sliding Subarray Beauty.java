/* Leetcode (Contest 342) 2653. Sliding Subarray Beauty
Given an integer array nums containing n integers, find the beauty of each subarray of size k.

The beauty of a subarray is the xth smallest integer in the subarray if it is negative, or 0 if there are fewer than x negative integers.

Return an integer array containing n - k + 1 integers, which denote the beauty of the subarrays in order from the first index in the array.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,-1,-3,-2,3], k = 3, x = 2
Output: [-1,-2,-2]
Explanation: There are 3 subarrays with size k = 3. 
The first subarray is [1, -1, -3] and the 2nd smallest negative integer is -1. 
The second subarray is [-1, -3, -2] and the 2nd smallest negative integer is -2. 
The third subarray is [-3, -2, 3] and the 2nd smallest negative integer is -2.

Example 2:

Input: nums = [-1,-2,-3,-4,-5], k = 2, x = 2
Output: [-1,-2,-3,-4]
Explanation: There are 4 subarrays with size k = 2.
For [-1, -2], the 2nd smallest negative integer is -1.
For [-2, -3], the 2nd smallest negative integer is -2.
For [-3, -4], the 2nd smallest negative integer is -3.
For [-4, -5], the 2nd smallest negative integer is -4. 

Example 3:

Input: nums = [-3,1,2,-3,0,-3], k = 2, x = 1
Output: [-3,0,-3,-3,-3]
Explanation: There are 5 subarrays with size k = 2.
For [-3, 1], the 1st smallest negative integer is -3.
For [1, 2], there is no negative integer so the beauty is 0.
For [2, -3], the 1st smallest negative integer is -3.
For [-3, 0], the 1st smallest negative integer is -3.
For [0, -3], the 1st smallest negative integer is -3.
 

Constraints:

n == nums.length 
1 <= n <= 105
1 <= k <= n
1 <= x <= k 
-50 <= nums[i] <= 50 
*/

/* 此题是统计 nums[] array 每个长度为 k的 子数组中 第 x个小的负整数的值

如果使用蛮力方法，滑动窗口然后排序每个子数组，则一定超过时间复杂度

所以我们使用 Bucket Sort frequence来统计每个number的数量，并且顺便进行排序

1. 数据范围 -50 <= nums[i] <= 50，所以可以直接建一个 101长度的计数数组arr，因为 1~50，0，-1~-50，即101个元素。

2. 然后我们可以使用这个bucket sort arr[] 来统计每个number对应的数量

==> 0代表-50，1是-49，2是-48 ... 49是1 50是0 51是1 52是2 ... 62是12 ... 是 92是42 99是49 100的是50。

3. 然后遍历第一个长度为 k窗口，就能求出当前这个窗口 第 x个小 的负整数的值

4. 继续滑动这个窗口，加入nums[]中下一个数(右边的第一个数），删除nums[]中最左边的数

5. 每次有的新的窗口，就查找一次第X个小的负整数 ==》遍历完成 就能得到要求的数组结果

*/
// Time:  O(50 * n)   Space: O(n)
class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        //Bucket Sort 的方法 来对于nums进行排序 和 优化时间复杂度
        //此题是统计 nums[] array 每个长度为 k的 子数组中 第 x个小的负整数的值
        int n = nums.length;
        int[] res = new int[n - k + 1];

        // 1. 数据范围 -50 <= nums[i] <= 50，所以可以直接建一个 101长度的计数数组arr，因为 1~50，0，-1~-50，即101个元素。
        //bucketSort 进行排序，和统计每个number的数量
        int[] bucketSort = new int[101]; 
      
        //3. 然后遍历第一个长度为 k窗口，就能求出当前这个窗口 第 x个小 的负整数的值
        //首先是第一档区间的subarray [0, k - 1]
        for(int i = 0; i < k; i++){
            bucketSort[nums[i] + 50]++; //因为数据范围 -50 <= nums[i] <= 50，所以全部加50 来进行排序
            //0 ~ 49 对应的就是 -1 ~ -50
        }
        res[0] = countBeauty(bucketSort, x); //统计第一个区间subarray的第x个小的负整数

        // 4. 继续滑动这个窗口，加入nums[]中下一个数(右边的第一个数），删除nums[]中最左边的数
        //然后滑动窗口往后遍历，继续统计每个长度为k的subarray中第x小的负整数
        for(int j = k; j < n; j++){
            //根据nums[]中值对应的位置，加入一个新数，就删除一个旧数
            //从而达到滑动窗口移动的效果
            bucketSort[nums[j] + 50]++;  //窗口进一个新数
            bucketSort[nums[j - k] + 50]--; //删除窗口中旧的一个数
            res[j - k + 1] = countBeauty(bucketSort, x); //每次都使用子程序进行查找当前子数组中第x小的负整数
        }

        return res;
    }
    
    //子程序： 查找输出 这个 array 里面对应的 第 x 小的 负整数， 这里的arr 就是 main function中的bucket sort frequency[]
   //因为数据范围 -50 <= nums[i] <= 50，所以全部加50 来进行排序 ==》 0 ~ 49 对应的就是 -1 ~ -50
    public static int countBeauty(int[] arr, int x){
        int count = 0;
        for(int i = 0; i < 50; i++){
            count += arr[i]; //统计每个负整数的数量
            if(count >= x){
                //因为数据范围 -50 <= nums[i] <= 50，所以全部加50 来进行排序
                //所以返回得到nums[]中的值，需要减去 50 ==》 0 ~ 49 对应的就是 -1 ~ -50
                return i - 50; //得到这个数在nums[]中的值
            }
        }
        return 0;
    }
}
