/* Leetcode 532. K-diff Pairs in an Array

Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

  a. 0 <= i, j < nums.length
  b. i != j
  c. nums[i] - nums[j] == k
  
Notice that |val| denotes the absolute value of val.

Example 1:

Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:

Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:

Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
 

Constraints:

1 <= nums.length <= 104
-107 <= nums[i] <= 107
0 <= k <= 107
*/

/*

思路： 使用HashMap 把 nums[]中的所有数字和出现的次数 储存在一起

然后因为要找到差值为K的绝对值的数对

再遍历所有数字，通过HashMap 找到每个数字 加上K之后的值，和减去K之后的值 

int addK = key + k;
int minusK = key - k;

然后统计这个总数，因为每个数组对只用保留一次(unique）： （3，1） （1，3）是一样的

所以res需要除2 ==》 得到最后对应的数对

*/
//Time: O(n^2)  Space: O(n)
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        //hashmap<key, value>
        HashMap<Integer, Integer> map = new HashMap<>();
        
        //add all the number element with their frequency into Hashmap
        for(int num : nums){
            //key is the num element, value is the frequency of number
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //find all the pairs from nums[] array
        for(int key : map.keySet()){
            //beacuse |val| denotes the absolute value of val.
            //so every number element need consider two solution: add k or minus k
            int addK = key + k;
            int minusK = key - k;

            //there is special case: k == 0, it need find the same value number element
            if(k == 0){
                if(map.get(key) >= 2){ //there is some number frequency large than 2
                    res += 2;
                }
            } else{
                if(map.containsKey(addK)){
                    res++;
                }

                if(map.containsKey(minusK)){
                    res++;
                }
            }
        } 

        return res / 2;
    }
}
