/* Leetcode 2948. Make Lexicographically Smallest Array by Swapping Elements

You are given a 0-indexed array of positive integers nums and a positive integer limit.

In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.

Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than 
the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.


Example 1:

Input: nums = [1,5,3,9,8], limit = 2
Output: [1,3,5,8,9]
Explanation: Apply the operation 2 times:
- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
We cannot obtain a lexicographically smaller array by applying any more operations.
Note that it may be possible to get the same result by doing different operations.

Example 2:

Input: nums = [1,7,6,18,2,1], limit = 3
Output: [1,6,7,18,1,2]
Explanation: Apply the operation 3 times:
- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
We cannot obtain a lexicographically smaller array by applying any more operations.

Example 3:

Input: nums = [1,7,28,19,10], limit = 3
Output: [1,7,28,19,10]
Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain because we cannot apply the operation on any two indices.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= limit <= 10^9
*/

// 时间复杂度：O(nlog⁡n) 其中 n  为 nums 的长度。
// 空间复杂度：O(n) 

class Solution {
    // 在limit的限制下，交换得到字典序最小的nums[] array
    // 也就是尽量把小的数值往前放
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] indexArr = new Integer[n]; //储存nums中的下标idex,使其可以和nums中的值对应
        for (int i = 0; i < n; i++){
           indexArr[i] = i;
        }

        // 排序indexArr, 按照每个index对应的nums[i]值的升序 
        Arrays.sort(indexArr, (a, b) -> nums[a] - nums[b]); 

        //主遍历：首先根据limit的范围进行切割，保证每个子数组内的可以随意交换，
        // 然后 arrays.sort() 直接可以得到子数组的最小字典序情况，
        // 然后根据对应的index，把对应的nums[index]存入最后的结果数组
        int[] res = new int[n];

        for(int i = 0; i < n;){
            int startRange = i;

            //根据limit进行划分，保证每个区域的子数组内可以随意交换
            for (i++; i < n && nums[indexArr[i]] - nums[indexArr[i - 1]] <= limit; i++) ;

            // 复制划分好子数组，然后进行排序，这样可以保证是按照nums中的相对顺序进行加入
            Integer[] subArr = Arrays.copyOfRange(indexArr, startRange, i);
            Arrays.sort(subArr); 
        
            //最后根据对应的index, 储存对应的nums[index]值 到res结果中
            for(int j = 0; j < subArr.length; j++){
                // 按照numsArr的相对顺序进行添加，从而得到limit限制交换下，最小的字典序
                res[subArr[j]] = nums[indexArr[startRange + j]];
            }
        }

        return res;
    }
}
