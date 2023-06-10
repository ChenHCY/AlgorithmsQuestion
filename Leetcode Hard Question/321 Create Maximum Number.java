/* Leetcode 321. Create Maximum Number

You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the digits of two numbers. You are also given an integer k.

Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits from the same array must be preserved.

Return an array of the k digits representing the answer.

Example 1:

Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
Output: [9,8,6,5,3]

Example 2:

Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
Output: [6,7,6,0,4]

Example 3:

Input: nums1 = [3,9], nums2 = [8,9], k = 3
Output: [9,8,9]
 

Constraints:

m == nums1.length
n == nums2.length
1 <= m, n <= 500
0 <= nums1[i], nums2[i] <= 9
1 <= k <= m + n
*/

/*  此题是要计算两个数组 nums1[] 和 nums2[]，能组成的最大值数组
    1. 计算每个数组，取不同个数下 能组成的最大值， ==》k1(在nums1中取的个数) + k2(在nums2中取的个数) = k
    2. 然后合并在 nums1[] 和 nums2[] 取的两个子数组，得到 一个长度为k的数组，同时按照最大值进行排序
    3. 比较生成的每个长度为K的合并数组值，得到最大的数值，和输出最大值的合并数组 
    
    /* For Example: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
       合并数组的组合情况有 0 + 5、1 + 4、2 + 3、3 + 2、4 + 1五种情况,就是从此五种情况取出组合最大的一种;
        Math.max(0, k - n)： 表示如果nums2[]的个数 >= k, 则nums1[]数组可以从0个数开始取，
                                如果nums2[]的个数 < k, 则nums1[]数组至少要取 k - n(nums2的长度) 个数字。
    */
*/
//时间复杂度：O(k(m + n + k^2) m 和 n 分别是数组 nums1 和 nums2 的长度， 空间复杂度： O(K）
// ==> k 是每次合并数组的长度
//得到两个最大子序列的时间复杂度为线性，即 O(m+n)。
// 合并两个最大子序列，需要进行 k 次合并，每次合并需要进行比较，最坏情况下，比较的时间复杂度为 O(k)，因此合并操作的时间复杂度为 O(k^2)。
class Solution { 
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int m = nums1.length;
        int n = nums2.length;
        for(int i = Math.max(0, k - n); i <= k && i <= m; i++){
            int[] curr = merge(countMax(nums1, i), countMax(nums2, k - i), k);
            //比较，找到最大值的数组
            if(compareTwoArr(curr, 0, res, 0)){
                res = curr;
            }
        }
        return res;
    }

    ///1. 计算每个数组，取不同个数下 能组成的最大值
    // n1: 在这个数组中要求取的数字数量
    // arrLen - i : 当前数组中, 当前下标到结尾还有多少个元素;
    // j: 当前数组 index i之前，已经有多少个数加入到了结果的最大值组合中
    // arrLen - i + j > k: arr[]里面还剩余的数字 + 已经加入的数字 > 要求的取的数字数量（k2）==> 需要继续弹出替换
    // if j < k : 先将最大组合填满，或删除，再进行比较替换操作
    public int[] countMax(int[] arr, int k1){
        int arrLen = arr.length;
        int[] ans = new int[k1];
        
        for(int i = 0, j = 0; i < arrLen; i++){
            //如果已经有数字加入到结果队列中，并且总的数量是大于k1（要求的数量), 然后顶端数字小于当前数字时
            // ==> 需要进行替换和弹出
            while(j > 0 && arrLen - i + j > k1 && arr[i] > ans[j - 1]){
                j--; //缩小j的大小，表示删除之前的顶端数字
            }
            //完成弹出之后，在j的位置重新加入新的数值
            if(j < k1){
                ans[j] = arr[i];
                j++; 
            }
        }

        return ans;
    }

    //2. Merge Sort: 然后合并在 nums1[] 和 nums2[] 取的两个子数组，得到 一个长度为k的数组，同时按照最大值进行排序
    public int[] merge(int[] nums1, int[] nums2, int k){
        int[] totalArr = new int[k]; //合并的数组
       
        //遍历nums1[] 和 nums2[], 比较每一个数字，把数值大的数字放在前面
        for(int i = 0, j = 0, index = 0; index < k; index++){
            if(compareTwoArr(nums1, i, nums2, j)){
                totalArr[index] = nums1[i];
                i++;
            } else{
                totalArr[index] = nums2[j];
                j++;
            }
        }
        return totalArr;
    }

    //因为要找到能组成的最大值
    //3.  比较生成的每个长度为K的合并数组值，得到最大的数值，和输出最大值的合并数组 
    public boolean compareTwoArr(int[] curr, int i, int[] prev, int j){
        while(i < curr.length && j < prev.length && curr[i] == prev[j]){
            i++;
            j++;
        }
        if(i == curr.length){  //前面都相等 位数少的小
            return false; 
        } else if(j == prev.length){ //前面都相等 位数少的小
            return true;
        } else if(curr[i] > prev[j]){ //前面都相等  当前数字大于 则大
            return true;
        }
        return false; //前面都相等  当前数字小于 则小
    }
}
