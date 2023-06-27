/*Leetcode 370 Range Addition && Lintcode 903 903 · Range Addition

Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:
Given: length = 5,
updates = 
[
[1,  3,  2],
[2,  4,  3],
[0,  2, -2]
]
Output: return [-2, 0, 3, 5, 3]

Explanation:
Initial state:
[ 0, 0, 0, 0, 0 ]
After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]
After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]
After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]
*/
// Time complexity: O(n + k), where n is the length, and k is the number of `updates[][]` operations.
// Space complexity: O(n)
public class Solution {
    /**
     * @param length: the length of the array
     * @param updates: update operations
     * @return: the modified array after all k operations were executed
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here
        int[] res = new int[length]; // the resulet array
        int[] diff = new int[length]; // the diff array 差分数组
        
        for(int[] change : updates){
            int start = change[0]; //第一个数是起始位置
            int end = change[1]; //第二个数是结束位置
            int addNum = change[2]; //第三个数是这个区域需要加上的值

            //差分数组的定义：diff[i] 就是 nums[i] 和 nums[i-1] 之差：
            //diff[i] += addNum 意味着给 nums[i, ..] 所有的元素都加了 addNum
            //diff[j + 1] -= addNum 意味着给 nums[j+1, ..] 所有的元素都减去了 addNum
            //结合起来， 就是对于 nums[i..j] 中的所有元素都加 addNum 了
            diff[start] += addNum;
            if(end + 1 < length){
                diff[end + 1] -= addNum;
            }
        }

        //差分数组是前缀和数组的逆运算，所以对于一个差分数组求前缀和，就可以还原数组
        res[0] = diff[0];
        for(int i = 1; i < diff.length; i++){
            res[i] = diff[i] + res[i - 1];
        }

        return res;
    }
}
