/* Leetcode 1726. Tuple with Same Product

Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.

Example 1:

Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)

Example 2:

Input: nums = [1,2,4,5,10]
Output: 16
Explanation: There are 16 valid tuples:
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 10^4
All elements in nums are distinct.
*/
//Time: O(n)  Space: O(K) k is hashmap size()
class Solution {
    //  a * b = c * d where a != b != c != d.
    public int tupleSameProduct(int[] nums) {
        int res = 0;
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        // 使用HashMao储存任意两个数字乘积 和 对应的出现次数
        // nums[]中的数字都是不一样的
        // 因为要求是 a * b = c * d and a != b != c != d. 所以得到这个乘积的四个数都是不一样的
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                int key = nums[i] * nums[j]; // 检查当前对数的乘积
                Integer count = map.get(key); // 检查之前有几个相等乘积的对数
                
                //每次找到相等乘积的对数时，都可以任意组成满足要求的四元数组
                if(count != null){
                    res += count; 
                } 

                //  使用HashMao储存任意两个数字乘积 和 对应的出现次数
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        // 因为题目对于a b c d的index顺序没有要求，
        // 所以 2 个两不同的数对一定可以构成 8 个不同的同积元组，答案最后要 * 8
        return res * 8;
    }
}
